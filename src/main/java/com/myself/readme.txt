1.事务四大特性(原子、一致、隔离、持久)，事务隔离级别，Spring事务传播级别
https://www.cnblogs.com/eunice-sun/p/11024584.html
2.Redis的(Hash、List、Set、ZSET)数据类型，缓存雪崩、缓存穿透、缓存预热、缓存更新、缓存降级，集群分片，RDB系统快照、AOF系统日志
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
7.索引类型(单列索引、组合索引、全文索引、唯一索引、主键索引)
8.偏向锁
https://www.cnblogs.com/linghu-java/p/8944784.html
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

## 好多面试题 https://mp.weixin.qq.com/s/AI2dRngnVwL2OAEix6O2Ig
