/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : examsystem

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-07-11 15:49:49
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
  KEY `paper_id` (`paper_id`),
  KEY `question_id` (`question_id`),
  CONSTRAINT `t_question_paper_ibfk_1` FOREIGN KEY (`paper_id`) REFERENCES `t_paper` (`id`),
  CONSTRAINT `t_question_paper_ibfk_2` FOREIGN KEY (`question_id`) REFERENCES `t_question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
