/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-13 11:11:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_answer_record
-- ----------------------------
DROP TABLE IF EXISTS `t_answer_record`;
CREATE TABLE `t_answer_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `testRecord_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `result` varchar(50) DEFAULT NULL,
  `score` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_answer_record_ibfk_1` (`student_id`),
  KEY `t_answer_record_ibfk_3` (`testRecord_id`),
  KEY `t_answer_record_ibfk_4` (`question_id`),
  KEY `t_answer_record_ibfk_2` (`paper_id`),
  CONSTRAINT `t_answer_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_answer_record_ibfk_2` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_answer_record_ibfk_3` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_answer_record_ibfk_4` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
