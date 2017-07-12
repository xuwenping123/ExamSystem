/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-12 11:00:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_question_paper
-- ----------------------------
DROP TABLE IF EXISTS `t_question_paper`;
CREATE TABLE `t_question_paper` (
  `id` bigint(20) NOT NULL,
  `paper_id` bigint(20) NOT NULL,
  `question_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `t_question_paper_ibfk_1` (`paper_id`),
  KEY `t_question_paper_ibfk_2` (`question_id`),
  CONSTRAINT `t_question_paper_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_question_paper_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
