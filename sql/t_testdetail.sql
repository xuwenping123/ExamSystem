/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-11 15:50:13
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
  KEY `testRecord_id` (`testRecord_id`),
  KEY `teacher_id` (`teacher_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `t_testdetail_ibfk_1` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`),
  CONSTRAINT `t_testdetail_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`),
  CONSTRAINT `t_testdetail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `t_subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
