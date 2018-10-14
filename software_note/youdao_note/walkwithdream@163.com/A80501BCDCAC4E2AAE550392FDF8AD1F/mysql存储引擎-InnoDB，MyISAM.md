## mysql常用的存储引擎
- **InnoDB** 
- **MyISAM**
##
**InnoDB** 默认的storage engine，==支持事务==、==行锁设计==，提高并发性、==支持外键==，以确保数据完整性、独立的ibd文件存储表结构和数据、支持全文索引。
适用场合：强调可靠性和需要支持事务。

**MyISAM** ==不支持事务==、==表锁设计==、==不支持外键==、支持全文索引、使用数据压缩和索引优化。
特点：可靠性高、适用范围广、检索速度快。
适用场合：强调数据检索速度，读写并发不多的场景。

**mysql存储引擎**除了以上，还包括BDB、MEMORY、MERGE、EXAMPLE、NDB Cluster、ARCHIVE、CSV、BLACKHOLE、FEDERATED等，其中==InnoDB==和==BDB==提供事务安全表，其他存储引擎都是非事务安全表。