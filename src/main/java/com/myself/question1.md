### 1.事务四大特性(原子、一致、隔离、持久)，事务隔离级别，Spring事务传播级别
https://www.cnblogs.com/eunice-sun/p/11024584.html
1) required（默认属性）
如果存在一个事务，则支持当前事务。如果没有事务则开启一个新的事务。
被设置成这个级别时，会为每一个被调用的方法创建一个逻辑事务域。如果前面的方法已经创建了事务，那么后面的方法支持当前的事务，如果当前没有事务会重新建立事务。
2) mandatory
支持当前事务，如果当前没有事务，就抛出异常。
3) never
以非事务方式执行，如果当前存在事务，则抛出异常。
4) not_supported
以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
5) requires_new
新建事务，如果当前存在事务，把当前事务挂起。
6) supports
支持当前事务，如果当前没有事务，就以非事务方式执行。
7) nested
支持当前事务，新增Savepoint点，与当前事务同步提交或回滚。
嵌套事务一个非常重要的概念就是内层事务依赖于外层事务。外层事务失败时，会回滚内层事务所做的动作。而内层事务操作失败并不会引起外层事务的回滚。

### 2.Redis的(Hash、List、Set、ZSET)数据类型，缓存雪崩、缓存穿透、缓存预热、缓存更新、热key、缓存降级，集群分片，RDB系统快照、AOF系统日志
基本类型 string、linkedlist、hashtable(字典)、skiplist(跳跃表)、intset(整数集合)、ziplist(压缩列表)
1) 字符串对象string：int整数、embstr编码的简单动态字符串、raw简单动态字符串
2) 列表对象list：ziplist、linkedlist
3) 哈希对象hash：ziplist、hashtable
4) 集合对象set：intset、hashtable
5) 有序集合对象zset：ziplist、skiplist
Redis的过期策略：惰性删除(访问时检查是否过期)、定期删除(定期随机检查) 如果都不行就用内存淘汰机制
Redis的高可用：主从模式、哨兵模式
Redis事务机制：通过MULTI、EXEC、WATCH等命令来实现事务机制

抢购超卖：队列、分布式锁、Redis的事务或Lua脚本

### 3.LRU算法(缓存淘汰算法)
https://zhuanlan.zhihu.com/p/34989978

### 4.BigDecimal怎么实现的

### 5.索引失效
1、like 以%开头，索引无效；当like前缀没有%，后缀有%时，索引有效。
2、or语句前后没有同时使用索引。当or左右查询字段只有一个是索引，该索引失效，只有当or左右查询字段均为索引时，才会生效
3、组合索引，不是使用第一列索引，索引失效。
4、数据类型出现隐式转化。如varchar不加单引号的话可能会自动转换为int型，使索引无效，产生全表扫描。
5、在索引字段上使用not，<>，!=。不等于操作符是永远不会用到索引的，因此对它的处理只会产生全表扫描。 优化方法： key<>0 改为 key>0 or key<0。
6、对索引字段进行计算操作、字段上使用函数。（索引为 emp(ename,empno,sal)）
7、当全表扫描速度比索引速度快时，mysql会使用全表扫描，此时索引失效。
https://www.cnblogs.com/liehen2046/p/11052666.html
https://www.cnblogs.com/wdss/p/11186411.html

### 6.保证最终一致性(分布式事务)一致性(Consistency)/可用性(Availability)/分区容忍性(Partition Tolerance)
2pc、3pc、TCC、半消息/最终一致性
https://blog.csdn.net/oldshaui/article/details/88743085
https://www.cnblogs.com/qdhxhz/p/11167025.html

### 7.索引类型(单列索引、组合索引、全文索引、唯一索引、主键索引)、常见mysql面试题
索引结构来说：B+树(还可分为聚簇索引和非聚簇索引)、Hash索引、空间数据索引(R-Tree)、全文索引
重要: https://zhuanlan.zhihu.com/p/214295381

### 8.偏向锁、轻量级锁、锁粗化、锁消除
锁粗化：代码分析后判断部分锁可以合并成一个锁
锁消除：根据代码逃逸技术，如果判断到一段代码中，堆上的数据不会逃逸出当前线程，那么可以认为这段代码是线程安全的，不必要加锁。
https://www.cnblogs.com/paddix/p/5405678.html
https://www.cnblogs.com/linghu-java/p/8944784.html
https://blog.csdn.net/qq_26222859/article/details/80546917 锁消除和锁粗化

### 9.读写锁 锁升级 锁降级
读锁不可以升级为写锁 且 写锁是可以降级为读锁的 因为写锁是排他锁
https://www.jianshu.com/p/9cd5212c8841

### 10.AQS源码讲解
https://www.cnblogs.com/fsmly/p/11274572.html

### 11.Mysql 三大日志文件 binlog(statement/row/statement+row)、redo log、undo log
binlog
binlog用于记录数据库执行的写入性操作(不包括查询)信息，以二进制的形式保存在磁盘中。binlog是mysql的逻辑日志，并且由Server层进行记录，
使用任何存储引擎的mysql数据库都会记录binlog日志。
(1)逻辑日志：可以简单理解为记录的就是sql语句。
(2)物理日志：因为mysql数据最终是保存在数据页中的，物理日志记录的就是数据页变更。
binlog是通过追加的方式进行写入的，可以通过max_binlog_size参数设置每个binlog文件的大小，当文件大小达到给定值之后，会生成新的文件来保存日志。

binlog的存储模式：
(1)、STATMENT：基于SQL语句的复制(statement-based replication, SBR)，每一条会修改数据的sql语句会记录到binlog中。
优点：不需要记录每一行的变化，减少了binlog日志量，节约了IO, 从而提高了性能；
缺点：在某些情况下会导致主从数据不一致，比如执行sysdate()、slepp()等。
(2)、ROW：基于行的复制(row-based replication, RBR)，不记录每条sql语句的上下文信息，仅需记录哪条数据被修改了。
优点：不会出现某些特定情况下的存储过程、或function、或trigger的调用和触发无法被正确复制的问题；
缺点：会产生大量的日志，尤其是alter table的时候会让日志暴涨
(3)、MIXED：基于STATMENT和ROW两种模式的混合复制(mixed-based replication, MBR)，一般的复制使用STATEMENT模式保存binlog，
对于STATEMENT模式无法复制的操作使用ROW模式保存binlog

redo log：当做数据修改的时候，不仅在内存中操作，还会在redo log中记录这次操作。当事务提交的时候，会将redo log日志进行刷盘
(redo log一部分在内存中，一部分在磁盘上)。当数据库宕机重启的时候，会将redo log中的内容恢复到数据库中，再根据undo log和
binlog内容决定回滚数据还是提交数据。
其实好处就是将redo log进行刷盘比对数据页刷盘效率高，具体表现如下
(1)redo log体积小，毕竟只记录了哪一页修改了啥，因此体积小，刷盘快。
(2)redo log是一直往末尾进行追加，属于顺序IO。效率显然比随机IO来的快。

undo log：名为回滚日志，是实现原子性的关键，当事务回滚时能够撤销所有已经成功执行的sql语句，他需要记录你要回滚的相应日志信息。
例如:
(1)当你delete一条数据的时候，就需要记录这条数据的信息，回滚的时候，insert这条旧数据
(2)当你update一条数据的时候，就需要记录之前的旧值，回滚的时候，根据旧值执行update操作
(3)当年insert一条数据的时候，就需要这条记录的主键，回滚的时候，根据主键执行delete操作
undo log记录了这些回滚需要的信息，当事务执行失败或调用了rollback，导致事务需要回滚，便可以利用undo log中的信息将数据回滚到修改之前的样子。

Mysql怎么保证原子性的：undo log
Mysql怎么保证持久性的：redo log
Mysql怎么保证隔离性的：利用的是锁和MVCC多版本并发控制机制
Mysql怎么保证一致性的：数据库通过原子性、隔离性、持久性来保证一致性，剩下的是编码层面保证一致性。

https://zhuanlan.zhihu.com/p/190886874
https://www.jianshu.com/p/d13b3c98ce30

### 12.dobbo面试题
https://blog.csdn.net/yy339452689/article/details/105865511

### 13.数据库三范式
第一范式:数据库表中的字段都是单一属性的，不可再分。
第二范式:数据库表中不存在非关键字段对任一候选关键字段的部分函数依赖，即符合第二范式
第三范式:在第二范式的基础上，数据表中如果不存在非关键字段对任一候选关键字段的传递函数依赖则符合3NF。

第一范式:列不可再分
第二范式:行可以唯一区分，主键约束 
第三范式:表的非主属性不能依赖与其他表的非主属性 外键约束
https://blog.csdn.net/h330531987/article/details/71194540

1NF:字段不可分;
2NF:有主键，非主键字段依赖主键;
3NF:非主键字段不能相互依赖;
解释:
1NF:原子性 字段不可再分,否则就不是关系数据库;
2NF:唯一性 一个表只说明一个事物;
3NF:每列都与主键有直接关系，不存在传递依赖;

### 14.垃圾回收器
新生代收集器：Serial [ˈsɪriəl]、ParNew、Parallel Scavenge [ˈpærəlel] [ˈskævɪndʒ]
老年代收集器：CMS、Serial Old、Parallel Old
整堆收集器： G1

### 15.Mysql如何保证ACID，原子性(undo)+持久性(redo)+隔离性(读写锁+mvcc)(来保证)一致性
https://www.cnblogs.com/rjzheng/p/10841031.html

### 16.秒杀系统设计
https://www.bilibili.com/video/BV1DV411B7Jq?utm_source=wechat_session&utm_medium=social&utm_oi=764769649148583936

### 17.服务熔断Hystrix
https://blog.csdn.net/loushuiyifan/article/details/82702522

### 18.对象和数组并不是都在堆上分配内存的。
解答：不一定，随着JIT编译器的发展，在编译期间，如果JIT经过逃逸分析，发现有些对象没有逃逸出方法，
那么有可能堆内存分配会被优化成栈内存分配。但是这也并不是绝对的。就像我们前面看到的一样，在开启逃
逸分析之后，也并不是所有User对象都没有在堆上分配。
http://www.hollischuang.com/archives/2398

### 19.倒排索引
https://www.cnblogs.com/cjsblog/p/10327673.html

### 18.线程的状态，new、runnable、blocked、waiting、timed_waiting、terminated

### 19.ReetrantReadWriteLock的读写锁机制
1).Java并发库中ReetrantReadWriteLock实现了ReadWriteLock接口并添加了可重入的特性
2).ReetrantReadWriteLock读写锁的效率明显高于synchronized关键字
3).ReetrantReadWriteLock读写锁的实现中，读锁使用共享模式；写锁使用独占模式，换句话说，读锁可以在没有写锁的时候被多个线程同时持有，写锁是独占的
4).ReetrantReadWriteLock读写锁的实现中，需要注意的，当有读锁时，写锁就不能获得；而当有写锁时，除了获得写锁的这个线程可以获得读锁外，其他线程不能获得读锁

### 20.boolean：1个字节 int：4个字节 float：4个字节 double：8个字节 char：2个字节 byte：1个字节 short：2个字节 long：8个字节

### 21.select、poll、epoll(linux)/kqueue(bsd)的区别都是多路复用IO且都是同步非阻塞IO(需要阻塞读取数据)
https://www.cnblogs.com/aspirant/p/9166944.html

### 22.高并发三大利器 缓存、降级、限流
常见的限流算法有：令牌桶、漏桶
服务熔断和服务降级：1.超时一定次数后不再请求某个服务  2.一个请求只返回主要业务信息(比如双十一关闭退款)
https://blog.csdn.net/qq686867/article/details/88978503

### 23.java对象new的过程
1） 当虚拟机遇到一条new指令时候，首先去检查这个指令的参数是否能 在常量池中能否定位到一个类的符号引用 （即类的带路径全名），并且检查这个符号引用代表的类是否已被加载、解析和初始化过，即验证是否是第一次使用该类。如果没有（不是第一次使用），那必须先执行相应的类加载过程（class.forname()）。
2） 在类加载检查通过后，接下来虚拟机将 为新生的对象分配内存 。对象所需的内存的大小在类加载完成后便可以完全确定，为对象分配空间的任务等同于把一块确定大小的内存从Java堆中划分出来，目前常用的有两种方式，根据使用的垃圾收集器的不同使用不同的分配机制：
　　2.1. 指针碰撞（Bump the Pointer）：假设Java堆的内存是绝对规整的，所有用过的内存都放一边，空闲的内存放在另一边，中间放着一个指针作为分界点的指示器，那所分配内存就仅仅把那个指针向空闲空间那边挪动一段与对象大小相等的距离。
　　2.2. 空闲列表（Free List）：如果Java堆中的内存并不是规整的，已使用的内存和空间的内存是相互交错的，虚拟机必须维护一个空闲列表，记录上哪些内存块是可用的，在分配时候从列表中找到一块足够大的空间划分给对象使用。
3）内存分配完后，虚拟机需要将分配到的内存空间中的数据类型都 初始化为零值（不包括对象头）；
4）虚拟机要 对对象头进行必要的设置 ，例如这个对象是哪个类的实例（即所属类）、如何才能找到类的元数据信息、对象的哈希码、对象的GC分代年龄等信息，这些信息都存放在对象的对象头中。
至此，从虚拟机视角来看，一个新的对象已经产生了。但是在Java程序视角来看，执行new操作后会接着执行如下步骤：
5）调用对象的init()方法 ,根据传入的属性值给对象属性赋值。
6）在线程 栈中新建对象引用 ，并指向堆中刚刚新建的对象实例。
https://www.cnblogs.com/gjmhome/p/11401397.html

### 24.为什么要有Survivor区，为什么新生代需要有两个Survivor区
A.如果没有Survivor，Eden区每进行一次Minor GC，存活的对象就会被送到老年代。老年代很快被填满，触发Major GC（也可以看做触发了Full GC）。老年代的内存空间远大于新生代，进行一次Full GC消耗的时间比Minor GC长得多。频发的Full GC消耗的时间是非常多的，这一点会影响大型程序的执行和响应速度。
B.设置两个Survivor区最大的好处就是解决了碎片化。进行Minor GC时，Eden和Survivor各有一些存活对象，把Eden区的存活对象放到Survivor区，Survivor区的过期对象移除，很明显操作之后Survivor区对象所占有的内存是不连续的，也就导致了内存碎片化。
https://blog.csdn.net/zero__007/article/details/85107746

### 25.出现紧急情况后事前事中事后
A.事前避免紧急情况 测试
B.事中版本回滚，服务降级，服务限流
C.事后补偿

### 26.MySQL的QPS和TPS计算方法
QPS算法

Questions = SHOW GLOBAL STATUS LIKE 'Questions';
Uptime = SHOW GLOBAL STATUS LIKE 'Uptime';
QPS=Questions/Uptime

TPS算法

Com_commit = SHOW GLOBAL STATUS LIKE 'Com_commit';
Com_rollback = SHOW GLOBAL STATUS LIKE 'Com_rollback';
Uptime = SHOW GLOBAL STATUS LIKE 'Uptime';
TPS=(Com_commit + Com_rollback)/Uptime

### 27.三次握手和四次挥手
（1）第一次握手：Client将标志位SYN置为1，随机产生一个值seq=J，并将该数据包发送给Server，Client进入SYN_SENT状态，等待Server确认。
（2）第二次握手：Server收到数据包后由标志位SYN=1知道Client请求建立连接，Server将标志位SYN和ACK都置为1，ack=J+1，随机产生一个值seq=K，并将该数据包发送给Client以确认连接请求，Server进入SYN_RCVD状态。
（3）第三次握手：Client收到确认后，检查ack是否为J+1，ACK是否为1，如果正确则将标志位ACK置为1，ack=K+1，并将该数据包发送给Server，Server检查ack是否为K+1，ACK是否为1，如果正确则连接建立成功，Client和Server进入ESTABLISHED状态，完成三次握手，随后Client与Server之间可以开始传输数据了。

（1）第一次挥手：Client发送一个FIN，用来关闭Client到Server的数据传送，Client进入FIN_WAIT_1状态。
（2）第二次挥手：Server收到FIN后，发送一个ACK给Client，确认序号为收到序号+1（与SYN相同，一个FIN占用一个序号），Server进入CLOSE_WAIT状态。
（3）第三次挥手：Server发送一个FIN，用来关闭Server到Client的数据传送，Server进入LAST_ACK状态。
（4）第四次挥手：Client收到FIN后，Client进入TIME_WAIT状态，接着发送一个ACK给Server，确认序号为收到序号+1，Server进入CLOSED状态，完成四次挥手。

### 28.一个空Object对象的占多大空间
32位系统 4(引用)+16=20 64位系统 8(引用)+16=24

移动端(多端)->聚合层->
贷前(用户申请) : 决策系统(风控) : 数据系统(申请额度，数据采集，用户征信...)
: 审批系统(人工审批或自动审批)
贷中：借款还款，催收系统，供应链系统
出现紧急情况后事前事中事后
灰度回滚


### 29.扩容机制中的capacity初始值为什么是16，可不可以自定义成15，为什么一定要是2的指数次方
hashmap初始化时会计算好将初始容量变为2的n次方，hashmap的散列公式 hash&(length-1)
如果不是2的n次方则hash碰撞严重(导致无法均匀分布数组填不满)
例如长度为9时候，3&(9-1)=0  2&(9-1)=0 ，都在0上，碰撞了；
例如长度为8时候，3&(8-1)=3  2&(8-1)=2 ，不同位置上，不碰撞；

### 30.扩容的过程，在什么情况下会扩容
当数组容量大于 容量(16)*负载因子(0.75) 时扩容容量的一倍

### 31.简单介绍一下hash算法，它的核心性能，或者说判断一个hash算法好坏的指标是什么，indexFor()函数的实现。
hash : (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)
indexFor : hash & (length-1)

### 32.ConcurrentHashMap可以支持多少并发线程
我觉得1.7之前支持segment的数量个，1.8后不限制

### 33.Netty原理介绍下。
异步非阻塞IO，多路复用IO，Reactor线程模型(Dispatcher模式)
Reactor 是反应堆的意思，Reactor 模型是指通过一个或多个输入同时传递给服务处理器的服务请求的事件驱动处理模式。
Reactor 模型中有 2 个关键组成：
Reactor，Reactor 在一个单独的线程中运行，负责监听和分发事件，分发给适当的处理程序来对 IO 事件做出反应。它就像公司的电话接线员，它接听来自客户的电话并将线路转移到适当的联系人。
Handlers，处理程序执行 I/O 事件要完成的实际事件，类似于客户想要与之交谈的公司中的实际官员。Reactor 通过调度适当的处理程序来响应 I/O 事件，处理程序执行非阻塞操作。

特点：
高并发：Netty 是一款基于 NIO（Nonblocking IO，非阻塞IO）开发的网络通信框架，对比于 BIO（Blocking I/O，阻塞IO），他的并发性能得到了很大提高。
传输快：Netty 的传输依赖于零拷贝特性，尽量减少不必要的内存拷贝，实现了更高效率的传输。（DirectByteBuffer）
封装好：Netty 封装了 NIO 操作的很多细节，提供了易于使用调用接口。

Netty 高性能表现在哪些方面？
IO线程模型：同步非阻塞，用最少的资源做更多的事。
内存零拷贝：尽量减少不必要的内存拷贝，实现了更高效率的传输。
内存池设计：申请的内存可以重用，主要指直接内存。内部实现是用一颗二叉查找树管理内存分配情况。
串形化处理读写：避免使用锁带来的性能开销。
高性能序列化协议：支持 protobuf 等高性能序列化协议。

https://blog.csdn.net/ThinkWon/article/details/104391081/
https://www.sohu.com/a/272879207_463994

### 34.什么样的对象能当根节点
虚拟机栈（栈帧中的本地变量表）中引用的对象
本地方法栈中JNI（即一般说的Native方法）引用的对象
方法区中类静态属性引用的对象
方法区中常量引用的对象

### 35.CMS和G1的优缺点？什么时候发生FULL GC？什么时候stop the world!
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

### 36.String，StringBuffer，StringBuilder的区别
String不可变，StringBuffer线程安全，共有父类AbstractStringBuilder

### 37.zookeeper分布式协调服务，避免单点故障
Zookeeper 是通过 Zab(原子广播协议) 协议来保证分布式事务的最终一致性

角色：Leader、Follower、Observer
Observer和Follower几乎一致唯一的区别是Observer不参与投票，客户端将消息发送到Follower、Observer后就被Follower、Observer转发到Leader
然后Leader在进行通知所有的Follower进行投票，当投票超过一半时即表示成功然后Leader在发送一个commit请求给Follower提交消息

Zab协议如何保证数据一致性，所以只要满足一下条件即可
Zab协议需要保证选举出来的Leader需要满足以下条件：
1）新选举出来的 Leader 不能包含未提交的 Proposal 。
即新选举的 Leader 必须都是已经提交了 Proposal 的 Follower 服务器节点。
2）新选举的 Leader 节点中含有最大的 zxid 。
这样做的好处是可以避免 Leader 服务器检查 Proposal 的提交和丢弃工作。
总结就是，Leader挂了那么就新选举一个Leader，且新的Leader的zxid必须是最大的且是已提交的


Zookeeper初始化时Leader选举投票：
　　（1）首先第一台服务器启动，投自己一票，然后发投票信息，由于其它机器还没有启动所以它收不到反馈信息，服务器1的状态一直属于Looking。
　　（2）服务器2启动，发现当前没有leader，投票给自己，同时与之前启动的服务器1交换结果，由于服务器2的编号大所以服务器2胜出，但此时投票数没有大于半数，所以两个服务器的状态依然是LOOKING。
　　（3）服务器3启动，发现没有Leader，给自己投票，同时与之前启动的服务器1,2交换信息，由于服务器3的编号最大所以服务器3胜出，此时投票数正好大于半数，所以服务器3成为领导者，服务器1,2成为小弟。
　　（4）服务器4启动，给自己投票，同时与之前启动的服务器1,2,3交换信息，尽管服务器4的编号大，但之前服务器3已经胜出，所以服务器4只能成为小弟。
　　（5）服务器5和服务器4逻辑相同。

Zookeeper如果Server3当选了Leader,他突然宕机了，那么就需要重新选Leader了。
　　（1）Leader宕机之后，剩下的机器会自动进入选举状态，重新选举。
   （2）选举的依据是：优先考虑数据的的版本号zxid，再考虑id。(因为zxid越大，代表该服务器的数据越新，越全）
　　（3）由于是运行期间，因此每个服务器上的ZXID可能不同我们假设Server1的ZXID为123，而Server2的ZXID为122，Server4的ZXID为143，Sever5的ZXID为120
　　（4）在第一轮投票中，Server1、Server2、Sever4、Server5都会投给自己，即分别产生投票(1,123),(2,122)，(4,143),(5,120)
　　（5）然后各自将这个投票发给集群中所有机器。
　　（6）对于投票的处理，在这个情境下Server4的ZXID为143,是最大的，显然Server4会成为Leader，其他服务器成为跟随者。

https://www.jianshu.com/p/2bceacd60b8a


### 38.tomcat优化
1.内存调优
2.并发调优
3.IO优化（APR，BIO，NIO，AIO）
4.开启线程池
https://blog.csdn.net/qq_28109171/article/details/84256783


### 39.mysql索引生效规则
有联合索引 (a,b,c)
where a=3 and b=45 and c=5 .... 这种三个索引顺序使用中间没有断点，全部发挥作用；
where a=3 and c=5... 这种情况下b就是断点，a发挥了效果，c没有效果
where b=3 and c=4... 这种情况下a就是断点，在a后面的索引都没有发挥作用，这种写法联合索引没有发挥任何效果；
where b=45 and a=3 and c=5 .... 这个跟第一个一样，全部发挥作用，abc只要用上了就行，跟写的顺序无关

(0)    select * from mytable where a=3 and b=5 and c=4;
abc三个索引都在where条件里面用到了，而且都发挥了作用
(1)    select * from mytable where  c=4 and b=6 and a=3;
这条语句列出来只想说明 mysql没有那么笨，where里面的条件顺序在查询之前会被mysql自动优化，效果跟上一句一样
(2)    select * from mytable where a=3 and c=7;
a用到索引，b没有用，所以c是没有用到索引效果的
(3)    select * from mytable where a=3 and b>7 and c=3;
a用到了，b也用到了，c没有用到，这个地方b是范围值，也算断点，只不过自身用到了索引
(4)    select * from mytable where b=3 and c=4;
因为a索引没有使用，所以这里 bc都没有用上索引效果
(5)    select * from mytable where a>4 and b=7 and c=9;
a用到了  b没有使用，c没有使用
(6)    select * from mytable where a=3 order by b;
a用到了索引，b在结果排序中也用到了索引的效果，前面说了，a下面任意一段的b是排好序的
(7)    select * from mytable where a=3 order by c;
a用到了索引，但是这个地方c没有发挥排序效果，因为中间断点了，使用 explain 可以看到 filesort
(8)    select * from mytable where b=3 order by a;
b没有用到索引，排序中a也没有发挥索引效果


### 40.线程池的五种状态
1、RUNNING
(1) 状态说明：线程池处在RUNNING状态时，能够接收新任务，以及对已添加的任务进行处理。 
(2) 状态切换：线程池的初始化状态是RUNNING。换句话说，线程池被一旦被创建，就处于RUNNING状态，并且线程池中的任务数为0！
2、 SHUTDOWN
(1) 状态说明：线程池处在SHUTDOWN状态时，不接收新任务，但能处理已添加的任务。 
(2) 状态切换：调用线程池的shutdown()接口时，线程池由RUNNING -> SHUTDOWN。
3、STOP
(1) 状态说明：线程池处在STOP状态时，不接收新任务，不处理已添加的任务，并且会中断正在处理的任务。 
(2) 状态切换：调用线程池的shutdownNow()接口时，线程池由(RUNNING or SHUTDOWN ) -> STOP。
4、TIDYING
(1) 状态说明：当所有的任务已终止，ctl记录的”任务数量”为0，线程池会变为TIDYING状态。当线程池变为TIDYING状态时，会执行钩子函数terminated()。terminated()在ThreadPoolExecutor类中是空的，若用户想在线程池变为TIDYING时，进行相应的处理；可以通过重载terminated()函数来实现。 
(2) 状态切换：当线程池在SHUTDOWN状态下，阻塞队列为空并且线程池中执行的任务也为空时，就会由 SHUTDOWN -> TIDYING。 
当线程池在STOP状态下，线程池中执行的任务为空时，就会由STOP -> TIDYING。
5、 TERMINATED
(1) 状态说明：线程池彻底终止，就变成TERMINATED状态。 
(2) 状态切换：线程池处在TIDYING状态时，执行完terminated()之后，就会由 TIDYING -> TERMINATED。

### 41.怎么使用linux命令查询大文件日志
tail -f file
less file 
grep  `world` copy.log | less
tail -n +10000 | less // 从第 10000 开始,使用 less 查看。
tail  -n 10000 | less // 查看倒数第 1000 行到文件最后的数据。 

https://blog.csdn.net/stupid56862/article/details/93330203

### 42.explain的常用列说明
https://www.cnblogs.com/tufujie/p/9413852.html

### 43.分布式锁的几种实现
https://www.cnblogs.com/barrywxx/p/11644803.html

### 44.mysql的共享锁怎么加
for update 排他锁
lock in share mode 共享锁

### 45.redis的淘汰策略
noeviction: 不删除策略, 达到最大内存限制时, 如果需要更多内存, 直接返回错误信息。 大多数写命令都会导致占用更多的内存(有极少数会例外, 如 DEL )。
allkeys-lru: 所有key通用; 优先删除最近最少使用(less recently used ,LRU) 的 key。
volatile-lru: 只限于设置了 expire 的部分; 优先删除最近最少使用(less recently used ,LRU) 的 key。
allkeys-random: 所有key通用; 随机删除一部分 key。
volatile-random: 只限于设置了 expire 的部分; 随机删除一部分 key。
volatile-ttl: 只限于设置了 expire 的部分; 优先删除剩余时间(time to live,TTL) 短的key。

### 46.死锁的四个必要条件
（1） 互斥条件：一个资源每次只能被一个进程使用。
（2） 请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
（3） 不可剥夺条件:进程已获得的资源，在末使用完之前，不能强行剥夺。
（4） 循环等待条件:若干进程之间形成一种头尾相接的循环等待资源关系。

### 47.慢查询优化
https://blog.csdn.net/qq_35571554/article/details/82800463

### 48.Mysql主从同步过程
MYSQL主从同步原理：
1） MYSQL主从同步是异步复制的过程，整个同步需要开启3线程，master上开启bin-log日志（记录数据库增、删除、修改、更新操作）；
2） Slave开启I/O线程来请求master服务器，请求指定bin-log中position点之后的内容；
3） Master端收到请求，Master端I/O线程响应请求，bin-log、position之后内容返给salve；
4） Slave将收到的内容存入relay-log中继日志中，生成master.info（记录master ip、bin-log、position、用户名密码）；
5） Slave端SQL实时监测relay-log日志有更新，解析更新的sql内容，解析成sql语句，再salve库中执行；
6） 执行完毕之后，Slave端跟master端数据保持一致！

### 49.字符串常量存储在哪个内存区域
常量池1.6之前存储在方法区4M大小，1.7之后存储在堆区，new String()一直都在堆区

### 50.HashMap的put过程
hashmap并发问题，数据不一致和线程死锁
https://www.cnblogs.com/captainad/p/10905184.html

### 51.MySQL的幻读问题
什么是幻读：在一次事务里，多次查询出的结果集个数不一致
多出来的或者少的行叫做幻行
怎么解决幻读：1.多版本并发控制MVCC 2.next-key锁(当前读)
next-key锁原理：将当前数据行与上一条数据和下一条数据之间的间隙锁定，保证此范围内读取数据是一致的
next-key锁包含了什么：1.记录锁，加在索引上的锁  2.间隙锁，加在索引之间的锁

https://blog.csdn.net/weixin_30342639/article/details/107552255
MVCC只的读取已提交（Read Committed）和可重复读（Repeatable Read）两个事务级别下有效。其是通过Undo日志中的版本链和ReadView一
致性视图来实现的。MVCC就是在多个事务同时存在时，SELECT语句找寻到具体是版本链上的哪个版本，然后在找到的版本上返回其中所记录的数据的过程。
ReadView一致性视图主要是由两部分组成：所有未提交事务的ID数组和已经创建的最大事务ID组成（实际上ReadView还有其他的字段，但不影响这里对
MVCC的讲解）。比如：[100,200],300。事务100和200是当前未提交的事务，而事务300是当前创建的最大事务（已经提交了）。当执行SELECT语句的
时候会创建ReadView，但是在读取已提交和可重复读两个事务级别下，生成ReadView的策略是不一样的：读取已提交级别是每执行一次SELECT语句就会
重新生成一份ReadView，而可重复读级别是只会在第一次SELECT语句执行的时候会生成一份，后续的SELECT语句会沿用之前生成的ReadView（即使后
面有更新语句的话，也会继续沿用）。

https://blog.csdn.net/qq_33743572/article/details/107888862
ReadView中主要包含4个比较重要的内容：Read View中活跃就是指未提交的事务
1. m_ids：表示在生成ReadView时当前系统中活跃的读写事务的事务id列表(未提交事务)。
2. min_trx_id：表示在生成ReadView时当前系统中活跃的读写事务中最小的事务id，也就是m_ids中的最小值。
3. max_trx_id：表示生成ReadView时系统中应该分配给下一个事务的id值。
4. creator_trx_id：表示生成该ReadView的快照读操作产生的事务id。
注意max_trx_id并不是m_ids中的最大值，事务id是递增分配的。比方说现在有id为1， 2， 3这三个事务，之后id为3的事务提交了。那么一个新的
读事务在生成ReadView时， m_ids就包括1和2， min_trx_id的值就是1，max_trx_id的值就是4。
ReadView在生成时(读已提交每次select都会生成，可重复读第一次select生成)会记录当前数据行的最大事务id和最小事务id等等信息,在事务中读取
数据行的版本链(undo日志)时会对比当前的事务中的ReadView。

读未提交：默认什么都不做
读已提交：使用MVCC控制，使用版本链(undo日志)和ReadView一致性视图实现，每次查询时都会创建一个ReadView视图
可重复读：使用MVCC控制，使用版本链(undo日志)和ReadView一致性视图实现，只第一次查询时创建一个ReadView视图
序列化：使用表锁

### 52.MySQL的间隙锁导致的冲突问题
第一种间隙锁死锁原因（条件必须有索引，解决方法：数据不删除，或使用insert into on duplicate key update）：
有数据 1，2，7，8，9数据，然后在事务一select 5 for update时会产生间隙锁2-7，那么如果同时也有事务二select 5 for update，则双方都
拿到了2-7之间的间隙锁，这时如果同时插入[3,6]的数据则两个事务会相互等待产生死锁。
https://www.jianshu.com/p/2b258bfe00e5
第二种互相死锁等待（解决方法：一个事务中一张表加锁最好一次性加完使用in）：
例如两个用户同时投资，A用户金额随机分为2份，分给借款人(1，2)，B用户金额随机分为2份，分给借款人(2，1)，由于加锁的顺序不一样，死锁当然很
快就出现了。对于这个问题的改进很简单，直接把所有分配到的借款人直接一次锁住就行了。
Select * from xxx where id in (xx,xx,xx) for update
在in里面的列表值mysql是会自动从小到大排序，加锁也是一条条从小到大加的锁
第三种死锁原因：
session1:select * from t3 where id=9 for update
session2:select * from t3 where id<20 for update
session1:insert into t3 values(7,'ae','a',now())
Session2在等待Session1的id=9的锁，session2又持了1到8的锁（注意9到19的范围并没有被session2锁住），最后，session1在插入新行时又
得等待session2,故死锁发生了。
第四种死锁原因：
session1:select * from t1 where id=1 for update
session2:delete from t1 where id=5;
session1:update t1 set name='qqq' where id=5
session2:delete from t1 where id=1
删除id=5时会给id=5的记录加上排他锁，导致相互等待
第五种死锁原因：
表结构：T2(id primary key,name key,pubtime key,commit);
key(name)索引结构：(bbb->100),(hdc->1),(hdc->6),(hdc->8),(hdc->10),(yyy->4)
key(pubtime)索引结构：(1->10),(3->4),(5->8),(10->6),(20->100),(100->1)
session1:update t2 set comment='abc' where name='hdc';
session2:select * from t2 where pubtime>5 for update;
两个事务同时执行，加锁也是同时开始那么，session1加锁顺序是由索引(hdc->1),(hdc->6)开始的，而session2加锁顺序是由索引(10->6),(100->1)
开始的，导致加锁顺序颠倒造成死锁，事务一加锁1，事务而加锁6，事务一加锁6等待，事务二加锁1等待
第六种死锁原因：
表结构：表dltask 主键PRIMARY KEY (id) 唯一主键UNIQUE KEY uniq_a_b_c (a, b, c)
执行语句：delete from dltask where a=? and b=? and c=?
InnoDB上删除一条记录，并不是真正意义上的物理删除，而是将记录标识为删除状态。(注：这些标识为删除状态的记录，后续会由后台的Purge操作进行回收，
物理删除。但是，删除状态的记录会在索引中存放一段时间。) 在RR隔离级别下，唯一索引上满足查询条件，但是却是删除记录，如何加锁？InnoDB在此处的
处理策略与前两种策略均不相同，或者说是前两种策略的组合：对于满足条件的删除记录，InnoDB会在记录上加next key lock X(对记录本身加X锁，同时
锁住记录前的GAP，防止新的满足条件的记录插入。) Unique查询，三种情况，对应三种加锁策略，总结如下：
此处，我们看到了next key锁，是否很眼熟？对了，前面死锁中事务1，事务2处于等待状态的锁，均为next key锁。明白了这三个加锁策略，其实构造一定的
并发场景，死锁的原因已经呼之欲出。但是，还有一个前提策略需要介绍，那就是InnoDB内部采用的死锁预防策略。
1.找到满足条件的记录，并且记录有效，则对记录加X锁，No Gap锁(lock_mode X locks rec but not gap)；
2.找到满足条件的记录，但是记录无效(标识为删除的记录)，则对记录加next key锁(同时锁住记录本身，以及记录之前的Gap：lock_mode X);
3.未找到满足条件的记录，则对第一个不满足条件的记录加Gap锁，保证没有满足条件的记录插入(locks gap before rec)；


#### 53.Netty的EventLoop,重要
https://blog.csdn.net/qq_24313635/article/details/80989450

####  好多MySQL面试题 https://zhuanlan.zhihu.com/p/214295381
####  好多面试题 https://mp.weixin.qq.com/s/AI2dRngnVwL2OAEix6O2Ig
####  好多面试题 https://zhuanlan.zhihu.com/p/258777225
####  好多redis面试题 https://zhuanlan.zhihu.com/p/263028782
####  好多MQ面试题 https://zhuanlan.zhihu.com/p/260351128
重要
####  好多dubbo面试题 https://blog.csdn.net/moakun/article/details/82919804
