/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-12 11:00:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_testdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_testdetail`;
CREATE TABLE `t_testdetail` (
  `id` bigint(20) NOT NULL,
  `testRecord_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `totalScore` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_testdetail_ibfk_1` (`testRecord_id`),
  KEY `t_testdetail_ibfk_2` (`teacher_id`),
  KEY `t_testdetail_ibfk_3` (`student_id`),
  CONSTRAINT `t_testdetail_ibfk_1` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `t_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
