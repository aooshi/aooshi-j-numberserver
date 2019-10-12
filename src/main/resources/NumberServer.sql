
-- ----------------------------
-- Table structure for jnumberserver_store
-- ----------------------------
CREATE TABLE `jnumberserver_store` (
  `id` varchar(255) NOT NULL,
  `v` bigint(20) NOT NULL DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器数值存储表';

-- ----------------------------
-- Table structure for jnumberserver_user
-- ----------------------------
CREATE TABLE `jnumberserver_user` (
  `uid` int(11) NOT NULL,
  `pwd` char(32) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器访问授权';




INSERT INTO `jnumberserver_user` set uid=1,pwd='yAYX4yA2hERjmyt352XXzWafcmWpCWYS',state=0,createtime=now();
INSERT INTO `jnumberserver_store` SET id="1",v=111111,createtime=now();



