package com.myself.iodemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 如果做以下两种动作
 * read(file, buf, len);     // 把文件读到缓冲区buf中
 * write(socket, buf, len);  // 把buf中的内容发送给用户
 * <p>
 * 那么步骤：
 * 1.把磁盘中文件拷贝到kernel buf
 * 2.把kernel buf拷贝到user buf
 * 3.把user buf拷贝到socket 中的kernel buf
 * 4.把socket buffer 拷贝到 网卡设备的buffer
 * 其实1,2是read，3,4是write
 * 所以说，一共拷贝4次，而且kernel mod和user mod的切换也是4次。
 * <p>
 * Linux 2.1内核开始引入了sendfile/splice函数。省去了将操作系统的read buffer拷贝到程序的buffer，
 * 以及从程序buffer拷贝到socket buffer的步骤而是直接将kernel buf 拷贝 到 socket buf。
 * 实际上是把2,3两部步骤整合了。
 * <p>
 * 这里把上下文的切换次数从4次减少到2次，同时也把数据copy的次数从4次降低到了3次。
 * Java NIO中的FileChannal.transferTo()方法就是这样的实现，这个实现是依赖于操作系统底层的sendFile()实现的
 * <p>
 * 但是，这还不是Zero-Copy。
 * <p>
 * 1.将文件拷贝到kernel buffer中
 * 2.向socket buf中写入当前要拷贝数据的位置和偏移量
 * 3.根据socket buf中的位置和偏移量，直接将kernel buf拷贝到网卡buffer中。
 */
public class ZeroCopyDemo {
    /**
     * 1.利用通道完成文件的复制（非直接缓冲区）
     **/
    public static void main1(String[] args) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        FileChannel inChannel = null;
        FileChannel outChannel = null;

        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");
            //1.获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //2.分配指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            //3.将通道中的数据缓冲区中
            while (inChannel.read(buffer) != -1) {

                buffer.flip();//切换成都数据模式

                //4.将缓冲区中的数据写入通道中
                outChannel.write(buffer);
                buffer.clear();//清空缓冲区
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 利用（直接缓冲区）通道完成文件的复制(内存映射文件的方式)
     * 将用户空间缓存和内核空间缓存做映射
     * 经历四次内核切换，三次内存拷贝
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inChannel2 = FileChannel.open(Paths.get("/Users/yangankang/Downloads/feature/4L_img@2x.png"), StandardOpenOption.READ);
        FileChannel outChannel2 = FileChannel.open(Paths.get("/Users/yangankang/Downloads/feature/4L_img@2x.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        //内存映射文件
        MappedByteBuffer inMappedBuf = inChannel2.map(FileChannel.MapMode.READ_ONLY, 0, inChannel2.size());
        MappedByteBuffer outMappedBuf = outChannel2.map(FileChannel.MapMode.READ_WRITE, 0, inChannel2.size());

        //直接对缓冲区进行数据读写操作
        byte[] dst = new byte[inMappedBuf.limit()];
        inMappedBuf.get(dst);
        outMappedBuf.put(dst);

        inChannel2.close();
        outChannel2.close();

        long end = System.currentTimeMillis();
        System.out.println("耗费的时间为：" + (end - start));
    }

    /**
     * 通道之间的数据传输（直接缓冲区）
     * 接近于零拷贝
     * 经历两次内核切换，三次内存拷贝
     *
     * @param args
     */
    public static void main3(String[] args) throws IOException {
        FileChannel inChannel3 = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        FileChannel outChannel3 = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        inChannel3.transferTo(0, inChannel3.size(), outChannel3);
        //等价于
//        outChannel3.transferFrom(inChannel3, 0, inChannel3.size());

        inChannel3.close();
        outChannel3.close();
    }
}
