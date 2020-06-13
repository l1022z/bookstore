/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : bookstore

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-06-10 16:25:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `n_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`n_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('1', '1qeqew', '1驱蚊器翁', '2020-06-08 20:00:49');
INSERT INTO `notice` VALUES ('2', '轻微的群翁多', '2奥德赛阿萨德adad', '2020-06-08 20:01:04');
INSERT INTO `notice` VALUES ('3', 'test', 'test安达市大所大大奥德赛', '2020-06-08 20:01:17');
INSERT INTO `notice` VALUES ('5', 'test1', '阿达的萨达大所多阿萨德奥德赛阿萨德', '2020-06-08 20:01:28');
INSERT INTO `notice` VALUES ('15', 'test', '1爱仕达大所', '2020-06-08 20:01:39');
INSERT INTO `notice` VALUES ('16', '16', '16', '2020-06-08 18:40:47');
INSERT INTO `notice` VALUES ('17', '11qeqqqqqq', 'asdasd', '2020-06-08 18:35:30');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('0da28972-3ec8-48ea-b610-dc60940ef0b6', '1', '10');
INSERT INTO `orderitem` VALUES ('0da28972-3ec8-48ea-b610-dc60940ef0b6', '2', '10');
INSERT INTO `orderitem` VALUES ('0da28972-3ec8-48ea-b610-dc60940ef0b6', '3', '10');
INSERT INTO `orderitem` VALUES ('2da6a13b-c613-460b-892a-5fb2bef5bfa5', '4', '3');
INSERT INTO `orderitem` VALUES ('3a52925b-395c-468c-8d45-a1d7c66a651f', '3', '1');
INSERT INTO `orderitem` VALUES ('572a36dc-3893-4fe1-b4a3-2a7cd599dc79', '1', '1');
INSERT INTO `orderitem` VALUES ('5aba8b8d-2289-4a97-b2d0-fde4d59fadc6', '51c0fc6d-1b94-4791-b501-307f9fc9b148', '1');
INSERT INTO `orderitem` VALUES ('71dfbe6d-836b-427f-9f9e-e28a60f6a250', '1', '1');
INSERT INTO `orderitem` VALUES ('866787a2-7ae4-4aab-abb8-ccc0c30eb672', '51c0fc6d-1b94-4791-b501-307f9fc9b148', '1');
INSERT INTO `orderitem` VALUES ('d03284a8-d2df-475e-acb0-dde27a2d6c67', '2', '1');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverPhone` varchar(20) DEFAULT NULL,
  `paystate` int(11) DEFAULT '0',
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('0da28972-3ec8-48ea-b610-dc60940ef0b6', '935', '安阳师范学院', '软件', '1234567890', '1', '2020-06-08 11:32:14', '8');
INSERT INTO `orders` VALUES ('2da6a13b-c613-460b-892a-5fb2bef5bfa5', '114', '安阳师范学院', '文', '1234567890', '1', '2020-06-05 16:34:59', '24');
INSERT INTO `orders` VALUES ('71dfbe6d-836b-427f-9f9e-e28a60f6a250', '32', '安阳师范学院', '软件', '1234567890', '0', '2020-06-05 18:22:09', '24');
INSERT INTO `orders` VALUES ('d03284a8-d2df-475e-acb0-dde27a2d6c67', '34', '安阳师范学院', '软件', '1234567890', '0', '2020-06-10 14:54:53', '8');

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` varchar(100) NOT NULL DEFAULT '',
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', 'java web', '32', '计算机', '8', '/productImg/10/5/36ee63bc-c251-49ce-9b9a-b5e1e2e75ec0.jpg', 'test');
INSERT INTO `products` VALUES ('2', '时空穿行', '34', '科技', '9', '/productImg/11/4/d79dc124-de69-4b77-847e-bc461bfdb857.jpg', '222222222222222222222222222222222222222222222222222222');
INSERT INTO `products` VALUES ('3', '大勇和小花的欧洲日记', '27.5', '少儿', '9', '/productImg/12/1/986b5e98-ee73-4717-89fd-b6ac26a8dc2c.jpg', '大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记');
INSERT INTO `products` VALUES ('4', 'Java基础入门', '38', '计算机', '96', '/productImg/12/14/a1ace169-b53a-41c6-bdea-000e5946b2a5.png', 'Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', '19.5', '励志', '100', '/productImg/14/1/792116e7-6d83-4be4-b3e5-4dd11b0b4565.jpg', '别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜');
INSERT INTO `products` VALUES ('6', '中国国家地理', '23.8', '社科', '100', '/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '中国国家地理中国国家地理中国国家地理中国国家地理中国国家地理');
INSERT INTO `products` VALUES ('7', '学会宽容', '28', '励志', '100', '/productImg/6/5/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '学会宽容学会宽容学会宽容');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `PASSWORD` varchar(20) NOT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `introduce` varchar(100) DEFAULT NULL,
  `activeCode` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT '0',
  `role` varchar(10) DEFAULT '普通用户',
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('8', 'abc', '123456', '女', '123@123.com', '123456', '', '38ea92ab-26c2-4725-aa73-b5e899e9767a', '1', '超级管理员', '2020-06-04 17:41:41');
INSERT INTO `user` VALUES ('24', 'sdf', '123456', '男', '2452374774@qq.com', '111111111111', '1', '9f50d713-9405-42b8-acd1-c4dce2e76f37', '1', '普通用户', '2020-06-05 14:28:34');
