/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-13 11:13:33
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

-- ----------------------------
-- Records of t_answer_record
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_paper
-- ----------------------------
INSERT INTO `t_paper` VALUES ('1', '语文试题', '1');
INSERT INTO `t_paper` VALUES ('2', '数学试题', '2');
INSERT INTO `t_paper` VALUES ('3', '英语试题', '3');
INSERT INTO `t_paper` VALUES ('4', '政治试题', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES ('1', '2009年9月15日至18日，中国共产党第十七届中央委员会第四次全体会议在北京举行。全会审议通过了《中共中央关于加强和改进新形势下＿＿＿若干重大问题的决定》。A. 经济工作 B. 改革发展 C．党的建设 D. 科学发展', 'A', '0', '1');
INSERT INTO `t_question` VALUES ('2', '“新农保”中的基础养老金由国家财政全额支付，这项支出属于财政支出中的A. 债务支出 B. 经济建设支出C. 社会保障支出 D. 科学、教育、文化、卫生事业支出', 'A B', '1', '4');
INSERT INTO `t_question` VALUES ('3', 'changsha', '123', '2', '2');
INSERT INTO `t_question` VALUES ('4', '5', '23', '5', '1');
INSERT INTO `t_question` VALUES ('5', '2', '4', '2', '2');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_question_paper
-- ----------------------------
INSERT INTO `t_question_paper` VALUES ('2', '1', '1', '20');
INSERT INTO `t_question_paper` VALUES ('3', '1', '2', '20');
INSERT INTO `t_question_paper` VALUES ('4', '1', '3', '20');
INSERT INTO `t_question_paper` VALUES ('5', '1', '4', '20');
INSERT INTO `t_question_paper` VALUES ('6', '1', '5', '20');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES ('2', 'changsha', '22', 'changsha', 'changsha123');
INSERT INTO `t_student` VALUES ('6', 'Test add name', '18', 'testname', 'testAdd');

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES ('1', null, '语文', '1');
INSERT INTO `t_subject` VALUES ('2', null, '数学', '1');
INSERT INTO `t_subject` VALUES ('3', null, '英语', '1');
INSERT INTO `t_subject` VALUES ('4', null, '政治', '1');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_teacher
-- ----------------------------

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
  PRIMARY KEY (`id`),
  KEY `t_testdetail_ibfk_1` (`testRecord_id`),
  KEY `t_testdetail_ibfk_2` (`teacher_id`),
  KEY `t_testdetail_ibfk_3` (`student_id`),
  CONSTRAINT `t_testdetail_ibfk_1` FOREIGN KEY (`testRecord_id`) REFERENCES `t_testrecord` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `t_teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_testdetail_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `t_subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_testdetail
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_testrecord
-- ----------------------------
INSERT INTO `t_testrecord` VALUES ('1', 'test add testRecord', '2017-07-13 09:38:37', '2017-07-13 09:38:37', '1', '1');
INSERT INTO `t_testrecord` VALUES ('2', 'test add testRecord', '2017-07-13 09:52:55', '2017-07-13 09:52:55', '1', '1');
INSERT INTO `t_testrecord` VALUES ('4', '语文考试，1号卷', '2017-07-15 18:00:00', '2017-07-15 22:00:00', '1', '1');
