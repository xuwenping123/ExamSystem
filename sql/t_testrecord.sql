/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-11 13:43:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_testrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_testrecord`;
CREATE TABLE `t_testrecord` (
  `id` bigint(20) NOT NULL,
  `remark` varchar(50) DEFAULT NULL,
  `beginTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `endTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `status` int(5) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `paper_id` (`paper_id`),
  CONSTRAINT `t_testrecord_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
