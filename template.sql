/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50624
 Source Host           : localhost:3306
 Source Schema         : template

 Target Server Type    : MySQL
 Target Server Version : 50624
 File Encoding         : 65001

 Date: 12/03/2020 22:50:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `seller` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `price` float(10, 2) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_item
-- ----------------------------
INSERT INTO `tb_item` VALUES (1, '小米10', '小米公司', 2999.00, '很好用');
INSERT INTO `tb_item` VALUES (2, '小米mix4', '小米公司', 3999.00, '非常好');

-- ----------------------------
-- Table structure for tb_resources
-- ----------------------------
DROP TABLE IF EXISTS `tb_resources`;
CREATE TABLE `tb_resources`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyurl` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `filtername` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `sortnum` int(11) DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_resources
-- ----------------------------
INSERT INTO `tb_resources` VALUES (1, '/login.html', 'anon', 1, '1');
INSERT INTO `tb_resources` VALUES (2, '/shiro/login', 'anon', 2, '1');
INSERT INTO `tb_resources` VALUES (3, '/**', 'authc', 30, '1');
INSERT INTO `tb_resources` VALUES (4, '/shiro/logout', 'logout', 4, '1');
INSERT INTO `tb_resources` VALUES (5, '/teacher.html', 'roles[tea]', 5, '1');
INSERT INTO `tb_resources` VALUES (6, '/student.html', 'roles[stu]', 6, '1');

-- ----------------------------
-- Table structure for tb_role_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_user`;
CREATE TABLE `tb_role_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_role_user
-- ----------------------------
INSERT INTO `tb_role_user` VALUES (1, 3, 1);
INSERT INTO `tb_role_user` VALUES (2, 2, 2);

-- ----------------------------
-- Table structure for tb_roles
-- ----------------------------
DROP TABLE IF EXISTS `tb_roles`;
CREATE TABLE `tb_roles`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_roles
-- ----------------------------
INSERT INTO `tb_roles` VALUES (1, 'tea', '1');
INSERT INTO `tb_roles` VALUES (2, 'stu', '1');
INSERT INTO `tb_roles` VALUES (3, 'admin', '1');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `u_pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, 'zhouzhou', '8d44463ed0117ee7863e7eafccb6ecd5', '1', '');
INSERT INTO `tb_user` VALUES (2, 'zhouyi', 'cb1446343380d9a83069b92085edc548', '1', '');

SET FOREIGN_KEY_CHECKS = 1;
