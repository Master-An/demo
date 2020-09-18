

- localhost:9090/swagger-ui.html
- 全局异常处理
- 4：49

## 09-18
- Jenkins Job 模版配置 传不同的参数
- 不同的代码仓库建不同的Job
- POST请求传不同的字段  所需要的key 不固定

### 表结构设计
- Case表
- Trigger表
Case ID
TriggerID
时间
结果

- test_jenkins
```mysql
CREATE TABLE `test_jenkins` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `test_command` varchar(100) DEFAULT NULL COMMENT '测试命令',
  `url` varchar(100) DEFAULT NULL COMMENT 'Jenkins的baseUrl',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `command_run_case_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '命令运行的测试用例类型  1 文本 2 文件',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `command_run_case_suffix` varchar(100) DEFAULT NULL COMMENT '测试用例后缀名 如果case为文件时，此处必填',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='Jenkins服务表';
```

- test_user
```mysql
CREATE TABLE `test_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `auto_create_case_job_name` varchar(50) DEFAULT NULL COMMENT '自动生成用例job名称 不为空时表示已经创建job',
  `start_test_job_name` varchar(50) DEFAULT NULL COMMENT '执行测试job名称 不为空时表示已经创建job',
  `default_jenkins_id` int(11) DEFAULT NULL COMMENT '默认Jenkins服务器',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';
```

- test_task
```mysql
CREATE TABLE `test_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `test_jenkins_id` int(11) NOT NULL COMMENT '运行测试的Jenkins服务器id',
  `build_url` varchar(100) DEFAULT NULL COMMENT 'Jenkins的构建url',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `case_count` int(11) DEFAULT NULL COMMENT '用例数量',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `test_command` text NOT NULL COMMENT 'Jenkins执行测试时的命令脚本',
  `task_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '任务类型 1 执行测试任务 2 一键执行测试的任务',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态 0 无效 1 新建 2 执行中 3 执行完成',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=162 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='测试任务表';
```

- test_case
```mysql
CREATE TABLE `test_case` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `case_data` longtext COMMENT '测试数据',
  `case_name` varchar(200) DEFAULT NULL COMMENT '用例名称',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT NULL COMMENT '删除标志 1 未删除 0 已删除',
  `create_user_id` int(11) NOT NULL COMMENT '创建人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=334 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='测试用例表';
```

- test_case_rel
```mysql
CREATE TABLE `test_task_case_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_user_id` int(11) DEFAULT NULL COMMENT '创建人id',
  `task_id` int(11) DEFAULT NULL COMMENT '任务id',
  `case_id` int(11) DEFAULT NULL COMMENT '用例id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='测试任务表';
```