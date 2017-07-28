/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-28 15:20:23
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_paper`;
CREATE TABLE `t_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(50) DEFAULT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_paper_ibfk_1` (`subject_id`),
  CONSTRAINT `t_paper_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL COMMENT '题目内容',
  `answer` varchar(255) NOT NULL COMMENT '答案',
  `type` int(5) NOT NULL,
  `subject_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `subject_id` (`subject_id`),
  CONSTRAINT `subject_id` FOREIGN KEY (`subject_id`) REFERENCES `t_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_question_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_question_paper`;
CREATE TABLE `t_question_paper` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paper_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  `question_score` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `t_question_paper_ibfk_1` (`paper_id`),
  KEY `t_question_paper_ibfk_2` (`question_id`),
  CONSTRAINT `t_question_paper_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_question_paper_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int(5) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  `title` varchar(50) NOT NULL,
  `status` int(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_teacher
-- ----------------------------
DROP TABLE IF EXISTS `t_teacher`;
CREATE TABLE `t_teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `age` int(5) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_testdetail
-- ----------------------------
DROP TABLE IF EXISTS `t_testdetail`;
CREATE TABLE `t_testdetail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `testRecord_id` bigint(20) NOT NULL,
  `teacher_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL,
  `totalScore` int(11) DEFAULT NULL,
  `istaked` int(5) NOT NULL,
  `isMarked` int(5) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_testdetail_ibfk_1` (`testRecord_id`),
  KEY `t_testdetail_ibfk_2` (`teacher_id`),
  KEY `t_testdetail_ibfk_3` (`student_id`),
  CONSTRAINT `t_testdetail_ibfk_1` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_testrecord
-- ----------------------------
DROP TABLE IF EXISTS `t_testrecord`;
CREATE TABLE `t_testrecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(50) DEFAULT NULL,
  `beginTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `endTime` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` int(5) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_testrecord_ibfk_1` (`paper_id`),
  CONSTRAINT `t_testrecord_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
