/*
Navicat MySQL Data Transfer

Source Server         : Mysql-localhost
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2020-02-01 10:02:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_address
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `id` varchar(100) NOT NULL COMMENT '主键',
  `receiver` varchar(30) DEFAULT NULL COMMENT '收货人',
  `tel` char(11) DEFAULT NULL COMMENT '手机号码',
  `province` varchar(20) DEFAULT NULL COMMENT '省',
  `city` varchar(30) DEFAULT NULL COMMENT '市',
  `area` varchar(50) DEFAULT NULL COMMENT '区/县',
  `detail` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户ID',
  `state` varchar(30) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO `tb_address` VALUES ('ea8a1801-388a-4bed-a0c8-4c470daf7f12', '倪畅c', '17853533689', '北京市', '北京市', '西城区', '6', 'obk_M4voW5z6dAbloPGGdriurP7s', '1');
INSERT INTO `tb_address` VALUES ('f767dcef-c30b-47e4-a341-3e2b0ecc56eb', 'aa', 'aaaaa', '北京市', '北京市', '东城区', '3', 'obk_M4voW5z6dAbloPGGdriurP7s', '0');

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `id` varchar(100) NOT NULL COMMENT '管理员id',
  `login_code` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT '' COMMENT '管理员密码',
  `username` varchar(100) DEFAULT '' COMMENT '管理员账号',
  `email` varchar(100) DEFAULT '' COMMENT '管理员邮箱',
  `role` int(4) DEFAULT '0' COMMENT '角色 1-超级管理员 2-普通管理员',
  `state` int(4) DEFAULT '1' COMMENT '正常为1，删除则为0',
  `created` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `logined` datetime DEFAULT NULL COMMENT '最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('57f1cb29-fb92-4f3e-93db-ba10fe1067ba', '201558501235', '11fa895c09dc6afb227dad550472d6c5', 'zy', 'zy@163.com', '2', '1', '2019-04-23 10:39:13', '2019-04-29 20:24:43');
INSERT INTO `tb_admin` VALUES ('621f28ce-d356-4cda-9636-2b43bfa4b9ee', '201558501236', 'b96bb97abed9ce43bd309d19ccfe1251', '倪畅', 'imnic@163.com', '1', '1', '2019-04-22 20:44:34', '2019-05-01 20:38:19');
INSERT INTO `tb_admin` VALUES ('bc8b695b-2fb0-448e-b2b4-96572eb88b2c', '201558501239', '18c6028302535f2ac33e276f23d307ff', '201558501239', '201558501239', '1', '0', '2019-04-23 11:08:33', null);
INSERT INTO `tb_admin` VALUES ('d110e2c8-efcb-46df-99ff-86a2ae5998ef', '201558501238', '7494f8642495898fa6dce0ab2cb7026f', '201558501238', '201558501238', '1', '0', '2019-04-23 11:08:20', null);

-- ----------------------------
-- Table structure for tb_evaluate
-- ----------------------------
DROP TABLE IF EXISTS `tb_evaluate`;
CREATE TABLE `tb_evaluate` (
  `id` varchar(100) NOT NULL,
  `user_id` varchar(100) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `content` text,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `state` int(1) DEFAULT '1',
  `order_id` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_evaluate
-- ----------------------------
INSERT INTO `tb_evaluate` VALUES ('d71cbbbf-41dd-4327-a1bf-f6f869d7be8b', 'obk_M4voW5z6dAbloPGGdriurP7s', '4', '.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}.com_xing {\n  display: inline-block;\n	float: right;\n}', '2019-04-28 11:08:55', '1', '8755baf7-3fbe-4774-852e-1d060fbc9532');

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` varchar(100) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `price` float NOT NULL,
  `pack_fee` float DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `detail` text,
  `sale` int(11) DEFAULT '0',
  `stock` int(4) DEFAULT '1' COMMENT '有货为1，售罄为0',
  `state` int(4) DEFAULT '1',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  `shop_id` varchar(100) NOT NULL,
  `type_id` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES ('0ed7b8a4-b510-4664-a9c0-674c259cf5d3', '苹果1', '5', '1', '070b908f-0eef-4f47-8a6e-878f89874b5a.png', '<span><div class=\"pre\"></div><p><img src=\"http://localhost:8080/upload/78320f18-df51-47d1-af5c-460f958d4ffd.png\" style=\"max-width:100%;\"><br></p><p><br></p><p><span style=\"font-weight: bold;\">aaaaaaaaaaaaaaaaaa</span></p></span><p><br></p>', '0', '0', '1', '2019-04-24 16:30:35', null, '1f092486-db58-4057-8eed-c3c728aa6e02', '278fbd54-b8bf-425a-94de-6807b47bee96');
INSERT INTO `tb_item` VALUES ('89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', '200', '10', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '<span></span><p><span style=\"font-family: 微软雅黑; text-decoration-line: underline;\">哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈</span></p>', '3', '1', '1', '2019-04-26 20:07:07', null, '0508c52d-5a76-4053-9ed0-31c427a4f270', '3a1db258-15c2-472f-8f43-f9d8b843c86d');
INSERT INTO `tb_item` VALUES ('e7b97636-f403-4bd1-8197-7f3e2618f549', '回台湾不过我不去1热情给', '20', '2', 'd16c2d3d-5c71-41d9-bc4a-5bebb88654dd.png', '<span></span><p>呱呱呱呱呱呱</p>', '0', '0', '1', '2019-04-28 22:40:58', null, '0508c52d-5a76-4053-9ed0-31c427a4f270', 'e8761172-b8b4-4271-be9b-87e74fae2041');
INSERT INTO `tb_item` VALUES ('37d0bd84-f66a-4ee9-940f-3cad5a4a2306', '香蕉', '7', '1', 'cfd54126-db0a-45fd-987e-8a1adac146d1.jpg', '<span></span><p><br></p>', '0', '1', '1', '2019-04-29 21:23:23', null, '0508c52d-5a76-4053-9ed0-31c427a4f270', '278fbd54-b8bf-425a-94de-6807b47bee96');

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` varchar(100) NOT NULL,
  `order_num` varchar(100) DEFAULT NULL,
  `shop_id` varchar(100) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL,
  `state` int(4) DEFAULT '1' COMMENT '0-已删除 1-待付款 2-待发货  3-待收货 4-待评价 5-已完成',
  `user_id` varchar(100) DEFAULT NULL,
  `address_id` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('8755baf7-3fbe-4774-852e-1d060fbc9532', '201904281108262145', '1f092486-db58-4057-8eed-c3c728aa6e02', 'test1', '1', 'obk_M4voW5z6dAbloPGGdriurP7s', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-28 11:08:26');
INSERT INTO `tb_order` VALUES ('86773d7c-bff7-4e2e-9c5b-baf60b2ad1fb', '201904281157243879', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈', '3', 'obk_M4voW5z6dAbloPGGdriurP7s', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-28 23:57:24');
INSERT INTO `tb_order` VALUES ('6cda514c-706b-4bbe-a9a3-c1409b8624dc', '201904290536423057', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈', '4', 'undefined', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-29 17:36:42');
INSERT INTO `tb_order` VALUES ('1f954555-88bf-4d47-9861-1b8ca08865db', '201904290537355951', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈', '3', 'undefined', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-29 17:37:35');
INSERT INTO `tb_order` VALUES ('39c9c045-1d49-486f-98f5-0bf85edecf68', '201904290551356609', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈', '3', 'undefined', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-29 17:51:35');
INSERT INTO `tb_order` VALUES ('510e5c23-d86a-4062-a65d-2a5b1fc3f26b', '201904290553267871', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈', '3', 'undefined', 'ea8a1801-388a-4bed-a0c8-4c470daf7f12', '2019-04-29 17:53:26');

-- ----------------------------
-- Table structure for tb_orderdetail
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderdetail`;
CREATE TABLE `tb_orderdetail` (
  `id` varchar(100) NOT NULL,
  `item_id` varchar(100) DEFAULT NULL,
  `item_name` varchar(255) DEFAULT NULL,
  `pic` varchar(255) DEFAULT NULL,
  `packFee` float DEFAULT '0',
  `price` float DEFAULT '0' COMMENT '单价',
  `amount` int(10) DEFAULT '0' COMMENT '数量',
  `money` float DEFAULT '0' COMMENT '小结',
  `state` int(4) DEFAULT '1',
  `order_id` varchar(255) DEFAULT NULL,
  `order_num` varchar(100) DEFAULT NULL,
  `shop_id` varchar(100) DEFAULT NULL,
  `shop_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_orderdetail
-- ----------------------------
INSERT INTO `tb_orderdetail` VALUES ('01881cd7-6108-4286-9789-c941b421ff3a', '4161dc0b-ce25-412b-ae8a-f154cde35d90', '橘子', '8b49929d-b2af-467a-b0a5-a4eec8920669.png', '0', '7', '1', '7', '1', 'ebfec559-aebc-4c61-a41d-3460dbd9280c', '201904270954074753', '1f092486-db58-4057-8eed-c3c728aa6e02', 'test1');
INSERT INTO `tb_orderdetail` VALUES ('53f15a24-6c1c-4859-a01d-f43a4f174c3c', '4161dc0b-ce25-412b-ae8a-f154cde35d90', '橘子', '8b49929d-b2af-467a-b0a5-a4eec8920669.png', '0', '7', '1', '7', '1', 'af09c0c1-2db3-4bf4-89c0-ee63f9b1d209', '201904281050074345', '1f092486-db58-4057-8eed-c3c728aa6e02', 'test1');
INSERT INTO `tb_orderdetail` VALUES ('387c090d-a9a2-4152-addb-715141948b45', '4161dc0b-ce25-412b-ae8a-f154cde35d90', '橘子', '8b49929d-b2af-467a-b0a5-a4eec8920669.png', '0', '7', '1', '7', '1', '8755baf7-3fbe-4774-852e-1d060fbc9532', '201904281108262145', '1f092486-db58-4057-8eed-c3c728aa6e02', 'test1');
INSERT INTO `tb_orderdetail` VALUES ('60979a9d-c4f8-4254-84ca-5f4126216c89', '89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '0', '200', '1', '200', '1', '86773d7c-bff7-4e2e-9c5b-baf60b2ad1fb', '201904281157243879', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈');
INSERT INTO `tb_orderdetail` VALUES ('878c1463-89d6-49ed-afd1-f37c8cee3500', '89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '0', '200', '1', '200', '1', '6cda514c-706b-4bbe-a9a3-c1409b8624dc', '201904290536423057', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈');
INSERT INTO `tb_orderdetail` VALUES ('db7eba39-9693-4761-a9f2-7eecc59f9a5e', '89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '0', '200', '1', '200', '1', '1f954555-88bf-4d47-9861-1b8ca08865db', '201904290537355951', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈');
INSERT INTO `tb_orderdetail` VALUES ('5bdb2240-2434-4c14-bf51-912558ab27b3', '89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '0', '200', '2', '400', '1', '39c9c045-1d49-486f-98f5-0bf85edecf68', '201904290551356609', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈');
INSERT INTO `tb_orderdetail` VALUES ('e26b5036-fae2-4610-b80e-a2dad7617dbc', '89b2b5a2-abe8-4dd1-a518-467c2bd0bcf0', '4646161口红55555哈哈哈哈哈558红火火恍恍苹果1惚7999', 'e9dd4501-f695-40b1-9ebd-98ea9f0b53ed.png', '0', '200', '3', '600', '1', '510e5c23-d86a-4062-a65d-2a5b1fc3f26b', '201904290553267871', '0508c52d-5a76-4053-9ed0-31c427a4f270', '哈哈');

-- ----------------------------
-- Table structure for tb_shop
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `id` varchar(100) NOT NULL,
  `login_code` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `shop_name` varchar(100) DEFAULT NULL,
  `shop_logo` varchar(100) DEFAULT NULL,
  `shop_bg` varchar(100) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `legal_person` varchar(100) DEFAULT NULL,
  `tel` varchar(100) DEFAULT NULL,
  `state` int(4) DEFAULT '1',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `logined` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------
INSERT INTO `tb_shop` VALUES ('0508c52d-5a76-4053-9ed0-31c427a4f270', '201558501238', '7494f8642495898fa6dce0ab2cb7026f', '哈哈', '3fd1fdc0-3cc8-4f35-b863-4d7b2c899c44.png', '3ada5537-ca68-47bf-9d1a-52ee7621627d.jpg', '良心**店', 'wzx', '123456', '1', '2019-04-23 11:52:34', null);
INSERT INTO `tb_shop` VALUES ('1f092486-db58-4057-8eed-c3c728aa6e02', '201558501201', '49bca8efb38b78436533b12393677bc8', 'test1', '3f2d8027-0ceb-4dbe-bf60-3bb79c029315.png', '30c38374-d595-4c5e-a468-732039c50a80.png', '良心水果店', 'test1', '427572', '1', '2019-04-23 23:11:14', null);

-- ----------------------------
-- Table structure for tb_system
-- ----------------------------
DROP TABLE IF EXISTS `tb_system`;
CREATE TABLE `tb_system` (
  `id` int(1) DEFAULT NULL,
  `appID` varchar(100) DEFAULT NULL,
  `appSecret` varchar(100) DEFAULT NULL,
  `appName` varchar(100) DEFAULT NULL,
  `notice` text,
  `about` text,
  `contact` text,
  `pic1` varchar(255) DEFAULT NULL,
  `pic2` varchar(255) DEFAULT NULL,
  `pic3` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_system
-- ----------------------------
INSERT INTO `tb_system` VALUES ('1', 'wxcbba0f5e23553d36', '1fb90f3082933798266c039a4ae242ec', '八方汇学子小铺', '3983', '89383', '3536838', '0596b425-47d1-4433-9ee5-b8637e4d59f6.png', '19eb7577-7c3f-492c-9bcf-2cb96f8f54ac.png', '65220b50-78f6-46a6-986f-5eb6fdd3ee17.png');

-- ----------------------------
-- Table structure for tb_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `id` varchar(100) NOT NULL,
  `type_name` varchar(50) DEFAULT NULL,
  `desp` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT '1',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_type
-- ----------------------------
INSERT INTO `tb_type` VALUES ('278fbd54-b8bf-425a-94de-6807b47bee96', '水果', '水果水果水果水果', '1', '2019-04-23 16:28:14', null);
INSERT INTO `tb_type` VALUES ('3a1db258-15c2-472f-8f43-f9d8b843c86d', '化妆品', '化妆品化妆品化妆品', '1', '2019-04-23 16:31:35', null);
INSERT INTO `tb_type` VALUES ('53086146-8f7f-43d8-8c67-c4f17ac19762', '手机', '手机手机', '1', '2019-04-29 20:36:55', null);
INSERT INTO `tb_type` VALUES ('e8761172-b8b4-4271-be9b-87e74fae2041', '特产', '特产特产特产', '1', '2019-04-23 16:31:25', null);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` varchar(100) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `avatar` varchar(300) DEFAULT NULL,
  `state` int(4) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('obk_M4voW5z6dAbloPGGdriurP7s', '倪畅', 'https://wx.qlogo.cn/mmopen/vi_32/jRpdRxWGhPP0JCiaY5icLth5npicfa8yZ8g4wD2JNkMjmLKSUZ6AAw5RyukM9LxGiaRAt2bpBZGrvLEE9fCpPkGsJw/132', '1');
