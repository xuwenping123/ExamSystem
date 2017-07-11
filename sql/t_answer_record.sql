/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-11 15:49:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_answer_record
-- ----------------------------
DROP TABLE IF EXISTS `t_answer_record`;
CREATE TABLE `t_answer_record` (
  `id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `testRecord_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `result` varchar(50) DEFAULT NULL,
  `score` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `student_id` (`student_id`),
  KEY `paper_id` (`paper_id`),
  KEY `testRecord_id` (`testRecord_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `t_answer_record_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`),
  CONSTRAINT `t_answer_record_ibfk_2` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`),
  CONSTRAINT `t_answer_record_ibfk_3` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`),
  CONSTRAINT `t_answer_record_ibfk_4` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
