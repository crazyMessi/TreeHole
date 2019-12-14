/*
 Navicat Premium Data Transfer

 Source Server         : toUbuntu
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 49.235.253.22:3306
 Source Schema         : treehole

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 30/11/2019 19:45:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(255) NOT NULL,
  `commentId` int(255) NOT NULL AUTO_INCREMENT,
  `comContent` varchar(5000) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `comUpdateTime` timestamp(6) NULL DEFAULT NULL,
  `userId` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `commentCount` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`commentId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `feelingId`(`id`) USING BTREE,
  INDEX `comuserInfomation`(`userId`) USING BTREE,
  CONSTRAINT `comuserInfomation` FOREIGN KEY (`userId`) REFERENCES `userinformation` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf32 COLLATE = utf32_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for events
-- ----------------------------
DROP TABLE IF EXISTS `events`;
CREATE TABLE `events`  (
  `userId` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL COMMENT '用户名\r\n',
  `target` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL COMMENT '操作对象\r\n\r\n',
  `support` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL COMMENT '点赞（null表示不点赞）',
  `id` int(255) NULL DEFAULT NULL COMMENT '对象的id'
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for feeling
-- ----------------------------
DROP TABLE IF EXISTS `feeling`;
CREATE TABLE `feeling`  (
  `feelingId` int(255) NOT NULL AUTO_INCREMENT,
  `feelContent` varchar(5000) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `feUpdateTime` timestamp(6) NULL DEFAULT NULL,
  `ifPrivate` varchar(100) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `userId` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `supportCount` int(255) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  `commentCount` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`feelingId`) USING BTREE,
  INDEX `feeluserinfomation`(`userId`) USING BTREE,
  CONSTRAINT `feeluserinfomation` FOREIGN KEY (`userId`) REFERENCES `userinformation` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for music
-- ----------------------------
DROP TABLE IF EXISTS `music`;
CREATE TABLE `music`  (
  `musicUrl` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `tag` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `musicName` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `musicId` int(255) NOT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`musicId`) USING BTREE,
  INDEX `musicTag`(`tag`) USING BTREE,
  CONSTRAINT `musicTag` FOREIGN KEY (`tag`) REFERENCES `tag` (`tag`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for saying
-- ----------------------------
DROP TABLE IF EXISTS `saying`;
CREATE TABLE `saying`  (
  `sayingId` int(255) NOT NULL AUTO_INCREMENT,
  `tag` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  `supportCount` int(255) NULL DEFAULT NULL,
  `commentCount` int(255) NULL DEFAULT NULL,
  `imgUrl` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sayingId`) USING BTREE,
  INDEX `sayingTag`(`tag`) USING BTREE,
  CONSTRAINT `sayingTag` FOREIGN KEY (`tag`) REFERENCES `tag` (`tag`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
  `tag` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  PRIMARY KEY (`tag`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for userinformation
-- ----------------------------
DROP TABLE IF EXISTS `userinformation`;
CREATE TABLE `userinformation`  (
  `userId` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NOT NULL,
  `userPhoto` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT '',
  `userPassword` varchar(255) CHARACTER SET utf16 COLLATE utf16_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userId`) USING BTREE,
  INDEX `userId`(`userId`) USING BTREE,
  INDEX `userId_2`(`userId`) USING BTREE,
  INDEX `userPhotoUrl`(`userPhoto`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf16 COLLATE = utf16_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
