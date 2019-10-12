/*
Navicat MySQL Data Transfer

Source Server         : 11:3306
Source Server Version : 50629
Source Host           : 192.168.199.11:3306
Source Database       : aooshi

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2019-10-12 17:36:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jnumberserver_store
-- ----------------------------
DROP TABLE IF EXISTS `jnumberserver_store`;
CREATE TABLE `jnumberserver_store` (
  `id` varchar(255) NOT NULL,
  `v` bigint(20) NOT NULL DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器数值存储表';

-- ----------------------------
-- Table structure for jnumberserver_user
-- ----------------------------
DROP TABLE IF EXISTS `jnumberserver_user`;
CREATE TABLE `jnumberserver_user` (
  `uid` int(11) NOT NULL,
  `pwd` char(32) NOT NULL DEFAULT '0',
  `state` int(11) NOT NULL DEFAULT '0',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数值服务器访问授权';
