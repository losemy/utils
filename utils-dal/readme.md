

## 定义数据结构
```java

```
>> 按照一定的方式读取数据 并转换成对应的 sql语句加以执行

>> 结构体 如何定义


## 数据结构体定义

一个标准的建表语句
```sql
CREATE TABLE `XXL_JOB_QRTZ_TRIGGER_LOG` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `XXL_JOB_QRTZ_TRIGGER_LOG` COMMENT='这是表的注释';
其中还会包括建索引
```


## 设计列 根据excel sheet读取表名称 () 读取表comment

### 所有表都自动填充ID 自增字段 且不允许直接通过id查询 防止后续分库分表 基于id自增不支持

book(书籍).sheet
| name(名称) | type（类型(数据库字段类型，INDEX)） | comment(描述)  | allowNull(是否为空(Y/N))|indexColumns(索引列，逗号隔开)
| :-------- | --------:| :--: |


### 结合mybatis-plus使用 
#### 参考 crown的使用教程

