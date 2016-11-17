/*
Navicat MySQL Data Transfer

Source Server         : 本地库
Source Server Version : 50605
Source Host           : localhost:3306
Source Database       : parksys

Target Server Type    : MYSQL
Target Server Version : 50605
File Encoding         : 65001

Date: 2016-10-18 11:35:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for garage_history
-- ----------------------------
DROP TABLE IF EXISTS `garage_history`;
CREATE TABLE `garage_history` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `storage_time` varchar(32) DEFAULT NULL COMMENT '入库时间',
  `delivery_time` varchar(32) DEFAULT NULL COMMENT '出库时间',
  `park_number` varchar(32) DEFAULT NULL COMMENT '停车位编号',
  `park_status` varchar(16) DEFAULT '0' COMMENT '0：default, 1：盘点车库内车辆未出库已登记出库,2：盘点车库内车辆未出库未登记入库，3：盘点车库内车辆已出库未登记出库， 4：盘点车库内车辆已出库未登记入库）',
  `data_sources` varchar(16) DEFAULT '0' COMMENT '0：default  正常出入库记录 1：正常入库非正常出库2：非正常入库正常出库3：非正常入库非正常出库',
  `regis_time` varchar(32) DEFAULT NULL COMMENT '登记时间（数据来源时间）',
  `plate_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garage_history
-- ----------------------------
INSERT INTO `garage_history` VALUES ('1', '2016-09-12 12:00:31', '2016-09-12 12:00:31', '12', '1', '0', '2016-09-12 12:00:31', null);
INSERT INTO `garage_history` VALUES ('2', '2016-09-12 12:00:33', '2016-09-12 12:00:35', '23', '0', '2', '2016-09-12 12:00:31', null);
INSERT INTO `garage_history` VALUES ('3', '2016-09-12 12:00:39', '2016-09-12 12:00:31', '13', '1', '0', '2016-09-12 12:00:31', null);
INSERT INTO `garage_history` VALUES ('4', '2015-09-12 12:00:35', '2015-09-1212:00:31', '90', '2', '3', '2015-09-1212:00:31', null);
INSERT INTO `garage_history` VALUES ('8', '2015-09-12 12:00:23', '2016-10-14 15:04:44', 'B00001', '0', '0', null, 'A88888');
INSERT INTO `garage_history` VALUES ('9', '2016-09-12 12:30:23', '2016-09-14 15:04:44', 'B00001', '0', '0', '2016-09-14 15:32:44', 'A88888');
INSERT INTO `garage_history` VALUES ('10', '2016-09-12 12:32:23', '2016-09-14 15:33:44', 'B00001', '0', '0', '2016-09-14 15:32:44', 'A88888');
INSERT INTO `garage_history` VALUES ('11', '2016-09-12 12:32:23', '2016-09-14 15:33:45', 'B00001', '0', '0', '2016-09-14 15:32:44', 'A88881');
INSERT INTO `garage_history` VALUES ('12', '2016-10-12 07:32:23', '2016-10-12 08:32:23', 'B00001', '0', '0', '2016-10-12 08:35:23', 'A88883');
INSERT INTO `garage_history` VALUES ('13', '2016-10-18 09:42:31', '2016-10-18 09:56:31', 'B00001', '0', '0', '2016-10-18 09:56:31', 'A88861');
INSERT INTO `garage_history` VALUES ('14', '2016-10-18 09:42:31', '2016-10-18 10:42:31', 'B00001', '0', '0', '2016-10-18 09:46:31', 'A88851');
INSERT INTO `garage_history` VALUES ('15', '2016-10-18 09:33:31', '2016-10-18 10:07:02', 'B00001', '3:盘点遗漏数据', '2:系统盘点', '2016-10-18 09:33:31', 'A88881');
INSERT INTO `garage_history` VALUES ('16', '2016-10-18 09:42:31', '2016-10-18 10:07:03', 'B00001', '2:出库未登记', '2:系统盘点', '2016-10-18 09:42:31', 'A88861');
INSERT INTO `garage_history` VALUES ('17', '2016-10-18 10:07:03', '2016-10-18 10:21:25', null, '0', '0', '2016-10-18 10:14:59', 'A88831');
INSERT INTO `garage_history` VALUES ('18', '2016-10-18 11:14:00', '2016-10-18 11:14:40', 'B00001', '3:盘点遗漏数据', '2:系统盘点', '2016-10-18 11:14:00', 'A88855');
INSERT INTO `garage_history` VALUES ('19', '2016-10-18 11:15:00', '2016-10-18 11:18:41', 'B00001', '3:盘点遗漏数据', '2:系统盘点', '2016-10-18 11:15:00', 'A88856');

-- ----------------------------
-- Table structure for garage_table
-- ----------------------------
DROP TABLE IF EXISTS `garage_table`;
CREATE TABLE `garage_table` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `storage_time` varchar(32) DEFAULT NULL COMMENT '车辆入库时间',
  `delivery_time` varchar(32) DEFAULT NULL COMMENT '车辆出库时间',
  `park_number` varchar(32) DEFAULT NULL COMMENT '停车位编号',
  `park_status` varchar(16) DEFAULT '0' COMMENT '0：default, 1：盘点车库内车辆未出库已登记出库,2：盘点车库内车辆未出库未登记入库，3：盘点车库内车辆已出库未登记出库，4：盘点车库内车辆已出库未登记入库',
  `data_sources` varchar(16) DEFAULT '0' COMMENT '数据来源0：default  正常出入库记录,1：正常入库非正常出库,2：非正常入库正常出库,3：非正常入库非正常出库',
  `regis_time` varchar(32) DEFAULT NULL COMMENT '数据来源时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of garage_table
-- ----------------------------
INSERT INTO `garage_table` VALUES ('9', 'A88888', '2016-09-12 12:00:31', null, 'B00001', '1:盘点正常', '1:盘点正常', '2016-09-12 12:00:31');
INSERT INTO `garage_table` VALUES ('10', 'A88887', '2016-09-12 12:11:31', null, 'B00001', '1:盘点正常', '1:盘点正常', '2016-09-12 12:11:31');
INSERT INTO `garage_table` VALUES ('11', 'A88886', '2016-09-12 12:12:31', null, 'B00002', '1:盘点正常', '1:盘点正常', '2016-09-12 12:12:31');
INSERT INTO `garage_table` VALUES ('12', 'A88885', '2016-09-12 12:23:31', null, 'B00002', '1:盘点正常', '1:盘点正常', '2016-09-12 12:23:31');
INSERT INTO `garage_table` VALUES ('13', 'A88884', '2016-09-12 12:25:31', null, 'B00003', '1:盘点正常', '1:盘点正常', '2016-09-12 12:25:31');
INSERT INTO `garage_table` VALUES ('14', 'A88882', '2016-10-18 09:25:31', null, 'B00001', '1:盘点正常', '1:盘点正常', '2016-10-18 09:25:31');
INSERT INTO `garage_table` VALUES ('19', 'A88852', '2016-10-18 11:00:00', null, 'B00001', '1:盘点正常', '1:盘点正常', '2016-10-18 11:00:00');

-- ----------------------------
-- Table structure for inventory_record
-- ----------------------------
DROP TABLE IF EXISTS `inventory_record`;
CREATE TABLE `inventory_record` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `inventory_number` varchar(32) DEFAULT NULL COMMENT '盘点数量',
  `inventory_start_time` varchar(32) DEFAULT NULL COMMENT '盘点开始时间',
  `inventory_end_time` varchar(32) DEFAULT NULL COMMENT '盘点结束时间',
  `regis_start_time` varchar(32) DEFAULT NULL COMMENT '登记开始时间',
  `regis_end_time` varchar(32) DEFAULT NULL COMMENT '登记结束时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventory_record
-- ----------------------------
INSERT INTO `inventory_record` VALUES ('1', '5', '2016-10-17 17:25:31', '2016-10-17 16:25:31', '2016-10-17 16:25:31', '2016-10-17 17:25:31');

-- ----------------------------
-- Table structure for inventory_table
-- ----------------------------
DROP TABLE IF EXISTS `inventory_table`;
CREATE TABLE `inventory_table` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `car_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `inventory_time` varchar(32) DEFAULT NULL COMMENT '盘点时间（盘点开始时间）',
  `inventory_personnel` varchar(32) DEFAULT NULL COMMENT '盘点人员',
  `regis_time` varchar(32) DEFAULT NULL COMMENT '登记时间（开始登记时间）',
  `inventory_status` varchar(16) DEFAULT '0' COMMENT '盘点状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventory_table
-- ----------------------------
INSERT INTO `inventory_table` VALUES ('1', 'A88888', '2016-10-17 13:25:31', '测试', '2016-10-17 17:25:31', '0');
INSERT INTO `inventory_table` VALUES ('2', 'A88887', '2016-10-17 17:25:31', '测试', '2016-10-17 17:25:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('3', 'A88886', '2016-10-17 17:25:31', '测试', '2016-10-17 17:25:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('4', 'A88885', '2016-10-17 17:25:31', '测试', '2016-10-17 17:25:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('5', 'A88884', '2016-10-17 17:25:31', '测试', '2016-10-17 17:25:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('6', 'A88883', '2016-10-17 17:40:31', '测试数据', '2016-10-17 17:41:31', '2:盘点期间车辆出库');
INSERT INTO `inventory_table` VALUES ('7', 'A88882', '2016-10-18 11:40:31', '测试数据', '2016-10-18 11:45:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('8', 'A88851', '2016-10-18 11:42:31', '测试数据', '2016-10-18 11:45:31', '2:盘点期间车辆出库');
INSERT INTO `inventory_table` VALUES ('9', 'A88852', '2016-10-18 11:42:31', '测试数据', '2016-10-18 11:53:31', '1:盘点正常');
INSERT INTO `inventory_table` VALUES ('10', 'A88853', '2016-10-18 11:53:31', '测试数据', '2016-10-18 11:54:31', '3:数据录入异常');

-- ----------------------------
-- Table structure for park_meter
-- ----------------------------
DROP TABLE IF EXISTS `park_meter`;
CREATE TABLE `park_meter` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `park_number` varchar(32) DEFAULT NULL COMMENT '停车位编号',
  `park_postion` varchar(32) DEFAULT NULL COMMENT '停车位位置',
  `park_fee` varchar(32) DEFAULT NULL COMMENT '停车费用',
  `use_state` varchar(32) DEFAULT '0' COMMENT '停车位是否可用状态：0可用，1不可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of park_meter
-- ----------------------------

-- ----------------------------
-- Table structure for vehicle_information
-- ----------------------------
DROP TABLE IF EXISTS `vehicle_information`;
CREATE TABLE `vehicle_information` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_name` varchar(32) DEFAULT NULL COMMENT '车主姓名',
  `owner_phone` varchar(32) DEFAULT NULL COMMENT '车主手机号 ',
  `car_number` varchar(32) DEFAULT NULL COMMENT '车牌号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vehicle_information
-- ----------------------------
INSERT INTO `vehicle_information` VALUES ('1', null, null, null);
