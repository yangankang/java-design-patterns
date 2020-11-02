1.事务四大特性(原子、一致、隔离、持久)，事务隔离级别，Spring事务传播级别
https://www.cnblogs.com/eunice-sun/p/11024584.html
1) REQUIRED（默认属性）
如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务。
被设置成这个级别时，会为每一个被调用的方法创建一个逻辑事务域。如果前面的方法已经创建了事务，那么后面的方法支持当前的事务，如果当前没有事务会重新建立事务。
2) MANDATORY
支持当前事务，如果当前没有事务，就抛出异常。
3) NEVER
以非事务方式执行，如果当前存在事务，则抛出异常。
4) NOT_SUPPORTED
以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
5) REQUIRES_NEW
新建事务，如果当前存在事务，把当前事务挂起。
6) SUPPORTS
支持当前事务，如果当前没有事务，就以非事务方式执行。
7) NESTED
支持当前事务，新增Savepoint点，与当前事务同步提交或回滚。
嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。

2.Redis的(Hash、List、Set、ZSET)数据类型，缓存雪崩、缓存穿透、缓存预热、缓存更新、热key、缓存降级，集群分片，RDB系统快照、AOF系统日志
基本类型 string、linkedlist、hashtable(字典)、skiplist(跳跃表)、intset(整数集合)、ziplist(压缩列表)
1) 字符串对象string：int整数、embstr编码的简单动态字符串、raw简单动态字符串
2) 列表对象list：ziplist、linkedlist
3) 哈希对象hash：ziplist、hashtable
4) 集合对象set：intset、hashtable
5) 有序集合对象zset：ziplist、skiplist
Redis的过期策略：惰性删除(访问时检查是否过期)、定期删除(定期随机检查) 如果都不行就用内存淘汰机制
Redis的高可用：主从模式、哨兵模式
Redis事务机制：通过MULTI、EXEC、WATCH等命令来实现事务机制

3.LRU算法(缓存淘汰算法)
https://zhuanlan.zhihu.com/p/34989978

4.BigDecimal怎么实现的

5.索引失效
    1、like 以%开头，索引无效；当like前缀没有%，后缀有%时，索引有效。
    2、or语句前后没有同时使用索引。当or左右查询字段只有一个是索引，该索引失效，只有当or左右查询字段均为索引时，才会生效
    3、组合索引，不是使用第一列索引，索引失效。
    4、数据类型出现隐式转化。如varchar不加单引号的话可能会自动转换为int型，使索引无效，产生全表扫描。
    5、在索引字段上使用not，<>，!=。不等于操作符是永远不会用到索引的，因此对它的处理只会产生全表扫描。 优化方法： key<>0 改为 key>0 or key<0。
    6、对索引字段进行计算操作、字段上使用函数。（索引为 emp(ename,empno,sal)）
    7、当全表扫描速度比索引速度快时，mysql会使用全表扫描，此时索引失效。
https://www.cnblogs.com/liehen2046/p/11052666.html
https://www.cnblogs.com/wdss/p/11186411.html

6.保证最终一致性(分布式事务)一致性(Consistency)/可用性(Availability)/分区容忍性(Partition Tolerance)
2pc、3pc、TCC、半消息/最终一致性
https://blog.csdn.net/oldshaui/article/details/88743085
https://www.cnblogs.com/qdhxhz/p/11167025.html

7.索引类型(单列索引、组合索引、全文索引、唯一索引、主键索引)、常见mysql面试题
索引结构来说：B+树、Hash索引、空间数据索引(R-Tree)、全文索引
重要: https://zhuanlan.zhihu.com/p/214295381

8.偏向锁、轻量级锁、锁粗化、锁消除
锁粗化：代码分析后判断部分锁可以合并成一个锁
锁消除：根据代码逃逸技术，如果判断到一段代码中，堆上的数据不会逃逸出当前线程，那么可以认为这段代码是线程安全的，不必要加锁。
https://www.cnblogs.com/paddix/p/5405678.html
https://www.cnblogs.com/linghu-java/p/8944784.html
https://blog.csdn.net/qq_26222859/article/details/80546917 锁消除和锁粗化

9.读写锁 锁升级 锁降级
https://www.jianshu.com/p/9cd5212c8841

10.AQS源码讲解
https://www.cnblogs.com/fsmly/p/11274572.html

11.Mysql 三大日志文件 binlog(statement/row/statement+row)、redo log、undo log
https://zhuanlan.zhihu.com/p/190886874
https://www.jianshu.com/p/d13b3c98ce30

12.dobbo面试题
https://blog.csdn.net/yy339452689/article/details/105865511

13.数据库三范式
第一范式:数据库表中的字段都是单一属性的，不可再分。
第二范式:数据库表中不存在非关键字段对任一候选关键字段的部分函数依赖，即符合第二范式
第三范式:在第二范式的基础上，数据表中如果不存在非关键字段对任一候选关键字段的传递函数依赖则符合3NF。
https://blog.csdn.net/h330531987/article/details/71194540

14.垃圾回收器
新生代收集器：Serial [ˈsɪriəl]、ParNew、Parallel Scavenge [ˈpærəlel] [ˈskævɪndʒ]
老年代收集器：CMS、Serial Old、Parallel Old
整堆收集器： G1

15.Mysql如何保证ACID，原子性(undo)+持久性(redo)+隔离性(读写锁+mvcc)(来保证)一致性
https://www.cnblogs.com/rjzheng/p/10841031.html

16.秒杀系统设计
https://www.bilibili.com/video/BV1DV411B7Jq?utm_source=wechat_session&utm_medium=social&utm_oi=764769649148583936

17.服务熔断Hystrix
https://blog.csdn.net/loushuiyifan/article/details/82702522

18.对象和数组并不是都在堆上分配内存的。
解答：不一定，随着JIT编译器的发展，在编译期间，如果JIT经过逃逸分析，发现有些对象没有逃逸出方法，
那么有可能堆内存分配会被优化成栈内存分配。但是这也并不是绝对的。就像我们前面看到的一样，在开启逃
逸分析之后，也并不是所有User对象都没有在堆上分配。
http://www.hollischuang.com/archives/2398

19.倒排索引
https://www.cnblogs.com/cjsblog/p/10327673.html

18.线程的状态，new、runnable、blocked、waiting、timed_waiting、terminated

19.ReetrantReadWriteLock的读写锁机制
    1).Java并发库中ReetrantReadWriteLock实现了ReadWriteLock接口并添加了可重入的特性
    2).ReetrantReadWriteLock读写锁的效率明显高于synchronized关键字
    3).ReetrantReadWriteLock读写锁的实现中，读锁使用共享模式；写锁使用独占模式，换句话说，读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的
    4).ReetrantReadWriteLock读写锁的实现中，需要注意的，当有读锁时，写锁就不能获得；而当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁

20.boolean：1个字节 int：4个字节 float：4个字节 double：8个字节 char：2个字节 byte：1个字节 short：2个字节 long：8个字节

21.select、poll、epoll(linux)/kqueue(bsd)的区别都是多路复用IO且都是同步非阻塞IO(需要阻塞读取数据)
https://www.cnblogs.com/aspirant/p/9166944.html

22.高并发三大利器 缓存、降级、限流
常见的限流算法有：令牌桶、漏桶
服务熔断和服务降级：1.超时一定次数后不再请求某个服务  2.一个请求只返回主要业务信息(比如双十一关闭退款)
https://blog.csdn.net/qq686867/article/details/88978503

23.java对象new的过程
https://www.cnblogs.com/gjmhome/p/11401397.html
出现紧急情况后事前事中事后
灰度回滚

移动端(多端)->聚合层->
贷前(用户申请) : 决策系统(风控) : 数据系统(申请额度，数据采集，用户征信...)
: 审批系统(人工审批或自动审批)
贷中：借款还款，催收系统，供应链系统


## 好多MySQL面试题 https://zhuanlan.zhihu.com/p/214295381
## 好多面试题 https://mp.weixin.qq.com/s/AI2dRngnVwL2OAEix6O2Ig
## 好多面试题 https://zhuanlan.zhihu.com/p/258777225
## 好多面试题 https://www.jianshu.com/p/90c3b9832796
## 好多MySQL面试题 https://www.jianshu.com/p/f5d8c891680c
## 好多redis面试题 https://zhuanlan.zhihu.com/p/263028782
## 好多MQ面试题 https://zhuanlan.zhihu.com/p/260351128



1.扩容机制中的capacity初始值为什么是16，可不可以自定义成15，为什么一定要是2的指数次方
hashmap初始化时会计算好将初始容量变为2的n次方，hashmap的散列公式 hash&(length-1)
如果不是2的n次方则hash碰撞严重(导致无法均匀分布数组填不满)
例如长度为9时候，3&(9-1)=0  2&(9-1)=0 ，都在0上，碰撞了；
例如长度为8时候，3&(8-1)=3  2&(8-1)=2 ，不同位置上，不碰撞；

2.扩容的过程，在什么情况下会扩容
当数组容量大于 容量(16)*负载因子(0.75) 时扩容容量的一倍

3.简单介绍一下hash算法，它的核心性能，或者说判断一个hash算法好坏的指标是什么，indexFor()函数的实现。
hash : (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
indexFor : hash & (length-1)

4.ConcurrentHashMap可以支持多少并发线程
我觉得1.7之前支持segment的数量个，1.8后不限制

5.Netty原理介绍下。
异步非阻塞IO，多路复用IO，Reactor线程模型(Dispatcher模式)
Reactor 是反应堆的意思，Reactor 模型是指通过一个或多个输入同时传递给服务处理器的服务请求的事件驱动处理模式。
Reactor 模型中有 2 个关键组成：
Reactor，Reactor 在一个单独的线程中运行，负责监听和分发事件，分发给适当的处理程序来对 IO 事件做出反应。它就像公司的电话接线员，它接听来自客户的电话并将线路转移到适当的联系人。
Handlers，处理程序执行 I/O 事件要完成的实际事件，类似于客户想要与之交谈的公司中的实际官员。Reactor 通过调度适当的处理程序来响应 I/O 事件，处理程序执行非阻塞操作。
https://www.sohu.com/a/272879207_463994

6.什么样的对象能当根节点
虚拟机栈（栈帧中的本地变量表）中引用的对象
本地方法栈中JNI（即一般说的Native方法）引用的对象
方法区中类静态属性引用的对象
方法区中常量引用的对象

7.CMS和G1的优缺点？什么时候发生FULL GC？什么时候stop the world!
1、CMS收集器:基于“标记-清除”算法实现局部上来看是基于“复制”算法 1）初始标记  2）并发标记 3）重新标记  4）并发清除
初始标记、从新标记这两个步骤仍然需要“stop the world”，初始标记仅仅只是标记一下GC Roots能直接关联到的对象，熟读很快，并发标记阶段就是进行GC Roots Tracing，
而重新标记阶段则是为了修正并发标记期间因用户程序继续运作而导致标记产生表动的那一部分对象的标记记录，这个阶段的停顿时间一般会比初始标记阶段稍长点，但远比并发标记的时间短。
优点：并发收集、低停顿。
缺点：
1）CMS收集器对CPU资源非常敏感。在并发阶段，它虽然不会导致用户线程停顿，但是会因为占用了一部分线程而导致应用程序变慢，总吞吐量会降低。
2）CMS收集器无法处理浮动垃圾，可能会出现“Concurrent Mode Failure（并发模式故障）”失败而导致Full GC产生。
3）CMS是一款“标记--清除”算法实现的收集器，容易出现大量空间碎片。当空间碎片过多，将会给大对象分配带来很大的麻烦，往往会出现老年代还有很大空间剩余，但是无法找到足够大的连续空间来分配当前对象，不得不提前触发一次Full GC。
2、G1收集器:基于“标记-整理”算法 1）初始标记 2）并发标记 3）最终标记 4）筛选回收
1）并行于并发：G1能充分利用CPU、多核环境下的硬件优势，使用多个CPU（CPU或者CPU核心）来缩短stop-The-World停顿时间。部分其他收集器原本需要停顿Java线程执行的GC动作，G1收集器仍然可以通过并发的方式让java程序继续执行。
2）分代收集：虽然G1可以不需要其他收集器配合就能独立管理整个GC堆，但是还是保留了分代的概念。它能够采用不同的方式去处理新创建的对象和已经存活了一段时间，熬过多次GC的旧对象以获取更好的收集效果。
3）空间整合：与CMS的“标记--清理”算法不同，G1从整体来看是基于“标记整理”算法实现的收集器；从局部上来看是基于“复制”算法实现的。
4）可预测的停顿：这是G1相对于CMS的另一个大优势，降低停顿时间是G1和ＣＭＳ共同的关注点，但Ｇ１除了追求低停顿外，还能建立可预测的停顿时间模型，能让使用者明确指定在一个长度为M毫秒的时间片段内，
https://www.jianshu.com/p/9c6be3b92dc6

8.String，Stringbuffer，StringBuilder的区别
String不可变，StringBuffer线程安全，共有父类AbstractStringBuilder
