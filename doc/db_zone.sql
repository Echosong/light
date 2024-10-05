/*
 Navicat Premium Data Transfer

 Source Server         : localhost1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : db_zone

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 05/10/2024 20:35:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sd_area_dj
-- ----------------------------
DROP TABLE IF EXISTS `sd_area_dj`;
CREATE TABLE `sd_area_dj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `area_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_add` int(11) NULL DEFAULT NULL,
  `parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_area_dj
-- ----------------------------

-- ----------------------------
-- Table structure for sd_feedback
-- ----------------------------
DROP TABLE IF EXISTS `sd_feedback`;
CREATE TABLE `sd_feedback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `images` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_read` smallint(6) NULL DEFAULT NULL,
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for sd_finance
-- ----------------------------
DROP TABLE IF EXISTS `sd_finance`;
CREATE TABLE `sd_finance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `after_money` decimal(38, 2) NULL DEFAULT NULL,
  `begin_money` decimal(38, 2) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `money` decimal(38, 2) NULL DEFAULT NULL,
  `relate_id` int(11) NULL DEFAULT NULL,
  `relate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_finance
-- ----------------------------

-- ----------------------------
-- Table structure for sd_finance_invoice
-- ----------------------------
DROP TABLE IF EXISTS `sd_finance_invoice`;
CREATE TABLE `sd_finance_invoice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `amount` double NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_finance_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for sd_finance_invoice_head
-- ----------------------------
DROP TABLE IF EXISTS `sd_finance_invoice_head`;
CREATE TABLE `sd_finance_invoice_head`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_finance_invoice_head
-- ----------------------------

-- ----------------------------
-- Table structure for sd_finance_pay
-- ----------------------------
DROP TABLE IF EXISTS `sd_finance_pay`;
CREATE TABLE `sd_finance_pay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `merchant_id` int(11) NULL DEFAULT NULL,
  `merchant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `merchant_pay_id` int(11) NULL DEFAULT NULL,
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pay_amount` decimal(38, 2) NULL DEFAULT NULL,
  `pay_state` int(11) NULL DEFAULT NULL,
  `pay_time` datetime(6) NULL DEFAULT NULL,
  `pay_type` int(11) NULL DEFAULT NULL,
  `platform` int(11) NULL DEFAULT NULL,
  `refund_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `relation_type` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_finance_pay
-- ----------------------------

-- ----------------------------
-- Table structure for sd_member
-- ----------------------------
DROP TABLE IF EXISTS `sd_member`;
CREATE TABLE `sd_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `add_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `all_point` decimal(38, 2) NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `balance` decimal(38, 2) NULL DEFAULT NULL,
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `brokerage_price` decimal(38, 2) NULL DEFAULT NULL,
  `card_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `card_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cash` decimal(38, 2) NULL DEFAULT NULL,
  `city_id` int(11) NULL DEFAULT NULL,
  `consume` decimal(38, 2) NULL DEFAULT NULL,
  `fast_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `follow_count` int(11) NULL DEFAULT NULL,
  `freeze` decimal(38, 2) NULL DEFAULT NULL,
  `geo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `group_id` int(11) NULL DEFAULT NULL,
  `is_auth` int(11) NULL DEFAULT NULL,
  `is_perfect` int(11) NULL DEFAULT NULL,
  `last_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `level` int(11) NULL DEFAULT NULL,
  `likes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `login_type` int(11) NULL DEFAULT NULL,
  `logout_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_expire_time` datetime(6) NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `parent_time` datetime(6) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `point` decimal(38, 2) NULL DEFAULT NULL,
  `push_client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sex` int(11) NULL DEFAULT NULL,
  `spread_count` bigint(20) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_member
-- ----------------------------

-- ----------------------------
-- Table structure for sd_member_address
-- ----------------------------
DROP TABLE IF EXISTS `sd_member_address`;
CREATE TABLE `sd_member_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city_id` int(11) NULL DEFAULT NULL,
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` int(11) NULL DEFAULT NULL,
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `post_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_member_address
-- ----------------------------

-- ----------------------------
-- Table structure for sd_member_point_log
-- ----------------------------
DROP TABLE IF EXISTS `sd_member_point_log`;
CREATE TABLE `sd_member_point_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT NULL,
  `level_type` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_member_point_log
-- ----------------------------

-- ----------------------------
-- Table structure for sd_member_tocash
-- ----------------------------
DROP TABLE IF EXISTS `sd_member_tocash`;
CREATE TABLE `sd_member_tocash`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `money` decimal(38, 2) NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `withdrawals` decimal(38, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_member_tocash
-- ----------------------------

-- ----------------------------
-- Table structure for sd_order_product
-- ----------------------------
DROP TABLE IF EXISTS `sd_order_product`;
CREATE TABLE `sd_order_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `cost_amount` decimal(38, 2) NULL DEFAULT NULL,
  `earnest_amount` decimal(38, 2) NULL DEFAULT NULL,
  `finance_pay_id` int(11) NULL DEFAULT NULL,
  `give_point` int(11) NULL DEFAULT NULL,
  `invoice_head_id` int(11) NULL DEFAULT NULL,
  `market_amount` decimal(38, 2) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_price` decimal(38, 2) NULL DEFAULT NULL,
  `out_order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pay_amount` decimal(38, 2) NULL DEFAULT NULL,
  `pay_time` datetime(6) NULL DEFAULT NULL,
  `point` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `express_company` int(11) NULL DEFAULT NULL,
  `express_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_address_id` int(11) NULL DEFAULT NULL,
  `order_status` int(11) NULL DEFAULT NULL,
  `post_price` decimal(38, 2) NULL DEFAULT NULL,
  `refund_amount` decimal(38, 2) NULL DEFAULT NULL,
  `refund_state` int(11) NULL DEFAULT NULL,
  `weight` decimal(38, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_order_product
-- ----------------------------

-- ----------------------------
-- Table structure for sd_post
-- ----------------------------
DROP TABLE IF EXISTS `sd_post`;
CREATE TABLE `sd_post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `click_num` int(11) NULL DEFAULT NULL,
  `collect_num` int(11) NULL DEFAULT NULL,
  `comment_num` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_anonymous` int(11) NULL DEFAULT NULL,
  `is_hot` int(11) NULL DEFAULT NULL,
  `like_num` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reward` int(11) NULL DEFAULT NULL,
  `show_geo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_post
-- ----------------------------

-- ----------------------------
-- Table structure for sd_post_comment
-- ----------------------------
DROP TABLE IF EXISTS `sd_post_comment`;
CREATE TABLE `sd_post_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `goods` int(11) NULL DEFAULT NULL,
  `member_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `post_id` int(11) NULL DEFAULT NULL,
  `reply_id` int(11) NULL DEFAULT NULL,
  `reply_member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_post_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sd_product
-- ----------------------------
DROP TABLE IF EXISTS `sd_product`;
CREATE TABLE `sd_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `alarm_threshold_value` int(11) NULL DEFAULT NULL,
  `browse` int(11) NULL DEFAULT NULL,
  `cate_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `cate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `collect` int(11) NULL DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品详情',
  `ficti` int(11) NULL DEFAULT NULL,
  `give_integral` decimal(38, 2) NULL DEFAULT NULL,
  `group_duration` int(11) NULL DEFAULT NULL,
  `group_num` int(11) NULL DEFAULT NULL,
  `group_people_num` int(11) NULL DEFAULT NULL,
  `group_price` decimal(38, 2) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `integral` int(11) NULL DEFAULT NULL,
  `is_good` int(11) NULL DEFAULT NULL,
  `is_group` int(11) NULL DEFAULT NULL,
  `is_hot` int(11) NULL DEFAULT NULL,
  `is_integral` int(11) NULL DEFAULT NULL,
  `is_new` int(11) NULL DEFAULT NULL,
  `is_postage` int(11) NULL DEFAULT NULL,
  `is_seckill` int(11) NULL DEFAULT NULL,
  `is_sub` int(11) NULL DEFAULT NULL,
  `ot_price` decimal(38, 2) NULL DEFAULT NULL,
  `point_rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `postage` decimal(38, 2) NULL DEFAULT NULL,
  `price` decimal(38, 2) NULL DEFAULT NULL,
  `refuse_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL,
  `seckill_end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `seckill_start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `skill_price` decimal(38, 2) NULL DEFAULT NULL,
  `slider_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `spec_type` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `store_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `store_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `temp_id` int(11) NULL DEFAULT NULL,
  `volume` decimal(38, 2) NULL DEFAULT NULL,
  `weight` decimal(38, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_product
-- ----------------------------

-- ----------------------------
-- Table structure for sd_product_car
-- ----------------------------
DROP TABLE IF EXISTS `sd_product_car`;
CREATE TABLE `sd_product_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `market_price` decimal(38, 2) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `price` decimal(38, 2) NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL,
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sku_id` int(11) NULL DEFAULT NULL,
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_product_car
-- ----------------------------

-- ----------------------------
-- Table structure for sd_product_category
-- ----------------------------
DROP TABLE IF EXISTS `sd_product_category`;
CREATE TABLE `sd_product_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parent_id` int(11) NULL DEFAULT NULL,
  `sort` int(11) NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_product_category
-- ----------------------------

-- ----------------------------
-- Table structure for sd_product_comment
-- ----------------------------
DROP TABLE IF EXISTS `sd_product_comment`;
CREATE TABLE `sd_product_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `good_id` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `order_id` int(11) NULL DEFAULT NULL,
  `parent_comment` int(11) NULL DEFAULT NULL,
  `pics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_product_comment
-- ----------------------------

-- ----------------------------
-- Table structure for sd_product_rule
-- ----------------------------
DROP TABLE IF EXISTS `sd_product_rule`;
CREATE TABLE `sd_product_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `cost` decimal(38, 2) NULL DEFAULT NULL,
  `group_price` decimal(38, 2) NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ot_price` decimal(38, 2) NULL DEFAULT NULL,
  `price` decimal(38, 2) NULL DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `rule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `rule_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `settle_price` decimal(38, 2) NULL DEFAULT NULL,
  `skill_price` decimal(38, 2) NULL DEFAULT NULL,
  `stock` int(11) NULL DEFAULT NULL,
  `volume` decimal(38, 2) NULL DEFAULT NULL,
  `weight` decimal(38, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_product_rule
-- ----------------------------

-- ----------------------------
-- Table structure for sd_supplier
-- ----------------------------
DROP TABLE IF EXISTS `sd_supplier`;
CREATE TABLE `sd_supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `city_id` int(11) NULL DEFAULT NULL,
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `expire_time` datetime(6) NULL DEFAULT NULL,
  `in_price` decimal(38, 2) NULL DEFAULT NULL,
  `in_price_time` datetime(6) NULL DEFAULT NULL,
  `margin` decimal(38, 2) NULL DEFAULT NULL,
  `material` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sale_count` int(11) NULL DEFAULT NULL,
  `shop_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `shop_banner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `shop_city_id` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `shop_star` double NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sd_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for sys_ad
-- ----------------------------
DROP TABLE IF EXISTS `sys_ad`;
CREATE TABLE `sys_ad`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `info` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '关联的实体',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `position` int(11) NULL DEFAULT NULL COMMENT '位置{\n    \"40\": \"商城Banner\",\n    \"10\": \"首页Banner\",\n    \"50\": \"引导\",\n    \"20\": \"园区Banner\",\n    \"30\": \"园区底部广告位\"\n}',
  `relation_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联代码',
  `state` int(11) NOT NULL DEFAULT 10 COMMENT '状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转位置',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '广告管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_ad
-- ----------------------------

-- ----------------------------
-- Table structure for sys_article
-- ----------------------------
DROP TABLE IF EXISTS `sys_article`;
CREATE TABLE `sys_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `img_url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `project_id` int(11) NULL DEFAULT NULL COMMENT '项目id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型{\n    \"1\": \"帮助中心\",\n    \"2\": \"公告\",\n    \"3\": \"文章\",\n    \"4\": \"通知\",\n    \"5\": \"爱好\"\n}',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  `publish_time` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '新闻' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
INSERT INTO `sys_article` VALUES (1, '2024-08-04 14:35:59.588000', 0, '2024-08-04 14:56:55.699000', 0, NULL, '', '', NULL, 0, '测试数据', 30, NULL, NULL, NULL);
INSERT INTO `sys_article` VALUES (2, '2024-08-04 14:54:38.124000', 0, '2024-08-04 15:04:59.614000', 0, NULL, '<p>dsfdsfdsfssss</p>', '', NULL, 0, '测试数据', 20, NULL, NULL, '2024-08-11 15:04:56.000000');

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `number` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_code
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述(如果选择框用,隔开)',
  `group` int(11) NULL DEFAULT NULL COMMENT '配置分组(0:其他)',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置字段名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置说明',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型(配置html展现类型){\n    \"0\": \"文本框\",\n    \"1\": \"下拉选择框\",\n    \"2\": \"switch\",\n    \"3\": \"复选框\",\n    \"4\": \"上传\",\n    \"5\": \"文本域\",\n    \"6\": \"上传文件\",\n    \"7\": \"富文本\"\n}',
  `value` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '配置值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '2024-08-04 19:36:14.000000', 0, '2024-08-04 19:36:17.000000', 1, 0, '系统名称可以展现在登录窗体', 10, 'system_name', '系统名称', 0, '轻巧之光1');
INSERT INTO `sys_config` VALUES (2, '2021-06-30 23:52:39.000000', 26, '2024-03-10 10:41:55.000000', 1, NULL, '0', 1001, '版本号', '0', NULL, '0');
INSERT INTO `sys_config` VALUES (12, NULL, NULL, '2024-03-10 11:33:34.000000', 1, NULL, '[{\"v\":10,\"n\":\"本地存储\"},{\"v\":20,\"n\":\"阿里云OSS\"}]', 40, 'storageService', '存储类型选择', 1, '20');
INSERT INTO `sys_config` VALUES (13, NULL, NULL, '2024-03-10 11:32:27.000000', 1, NULL, '输入配置的 json格式', 40, 'ossConfig', '阿里云oss配置', 5, '{\"aliyun_accessKeyId\":\"LTAI4GAXb4A3tfxdLxHw6u5U\", \"aliyun_accessKeySecret\":\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\", \"aliyun_endpoint\":\"oss-cn-shanghai.aliyuncs.com\",\"aliyun_image_bucket\":\"djlx-image-bucket\", \"aliyun_video_bucket\": \"djlx-video-bucket\",\"aliyun_file_bucket\": \"djlx-file-bucket\",\"aliyun_origin_image_url\":\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\", \"aliyun_origin_video_url\":\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\", \"aliyun_origin_file_url\":\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\" }');
INSERT INTO `sys_config` VALUES (14, NULL, NULL, '2024-03-10 10:41:55.000000', 1, NULL, '输入json配置', 40, 'localConfig', '本地存储配置', 5, '{}');
INSERT INTO `sys_config` VALUES (15, NULL, NULL, '2024-08-04 21:13:16.050000', 0, NULL, '注册协议', 10, 'reg_contact', '注册协议', 7, NULL);
INSERT INTO `sys_config` VALUES (17, NULL, NULL, NULL, NULL, NULL, '60', 60, 'receipt_long', '确认收货时长(天)', 7, '10');
INSERT INTO `sys_config` VALUES (18, NULL, NULL, '2024-04-28 13:37:03.000000', 1, NULL, '50', 10, 'about', '关于我们', NULL, '0');
INSERT INTO `sys_config` VALUES (19, '2024-04-11 08:49:52.000000', NULL, '2024-04-11 08:49:54.000000', NULL, NULL, '60', 60, 'cancel_long', '订单取消时长(分钟)', 0, '7');
INSERT INTO `sys_config` VALUES (20, '2024-09-28 20:13:15.000000', NULL, '2024-09-28 20:13:18.000000', NULL, NULL, '消费获取积分', 60, 'product_point', '消费获取积分', 0, '1:1');
INSERT INTO `sys_config` VALUES (21, '2024-09-28 20:13:15.000000', NULL, '2024-09-28 20:13:18.000000', NULL, NULL, '分销返利比', 60, 'distributor_ratio', '分销返利比', 0, '0.01');

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `css_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性',
  `default_flg` int(11) NULL DEFAULT NULL COMMENT '是否默认(Y=是,N=否)',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `dict_label` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典描述',
  `dict_sort` bigint(20) NULL DEFAULT NULL,
  `dict_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_value` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '字典键值',
  `list_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格字典样式',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(1=正常,0=停用){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `dict_key` bigint(20) NULL DEFAULT NULL,
  `type_id` int(11) NULL DEFAULT NULL COMMENT '类型id',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `dict_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态(1=正常,0=停用){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `group_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分组',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `extend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `file_type` int(11) NULL DEFAULT NULL COMMENT '文件类型{\n    \"1\": \"音频\",\n    \"2\": \"视频\"\n}',
  `ico` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `url_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相对路径',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件唯一标识',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (5, '2024-08-04 11:09:32.302000', 0, '2024-08-04 11:09:32.302000', 0, NULL, 'png', 'ios', 'https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/008538e4-708a-4369-9981-ac9b6fb24365.png', 95551, 1, NULL, '2024/08/04/008538e4-708a-4369-9981-ac9b6fb24365.png', NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (6, '2024-08-04 11:10:08.675000', 0, '2024-08-04 11:40:23.478000', 0, NULL, 'doc', '7830145706747284830_2985257681418489697_包装前检，包装后检相关车间库存问题', 'https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc', 224768, 1, '[\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/10565ad4-7f5a-405b-a0d2-5a24ac241479.png\"]', '2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc', 'dsf', NULL, NULL);
INSERT INTO `sys_file` VALUES (7, '2024-08-04 11:40:21.480000', 0, '2024-08-04 11:40:21.480000', 0, NULL, 'png', 'PixPin_2024-08-01_14-05-39', 'https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/10565ad4-7f5a-405b-a0d2-5a24ac241479.png', 323016, 1, NULL, '2024/08/04/10565ad4-7f5a-405b-a0d2-5a24ac241479.png', NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (8, '2024-08-04 14:35:43.551000', 0, '2024-08-04 14:35:43.551000', 0, NULL, 'png', 'PixPin_2024-08-01_14-05-39', 'https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/7a467cc1-cdf8-41a8-8758-dd86844556d2.png', 323016, 1, NULL, '2024/08/04/7a467cc1-cdf8-41a8-8758-dd86844556d2.png', NULL, NULL, NULL);
INSERT INTO `sys_file` VALUES (9, '2024-08-04 14:53:50.784000', 0, '2024-08-04 14:53:50.784000', 0, NULL, 'png', 'PixPin_2024-08-01_14-05-39', 'https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/4e054ad0-fad1-4813-a8af-ed344c8bb7c9.png', 323016, 1, NULL, '2024/08/04/4e054ad0-fad1-4813-a8af-ed344c8bb7c9.png', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `browser` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '浏览器',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `exception_detail` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常详情',
  `log_type` int(11) NULL DEFAULT NULL COMMENT '日志类型{\n    \"10\": \"登录\",\n    \"11\": \"注册\",\n    \"12\": \"发送验证码\",\n    \"20\": \"其他\",\n    \"13\": \"前端修改用户信息\"\n}',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '参数',
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 197 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (2, '2024-07-21 22:23:12.211000', 0, '2024-07-21 22:23:12.211000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"mdfu\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (3, '2024-07-21 22:24:46.320000', 0, '2024-07-21 22:24:46.320000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"Idfd\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (4, '2024-07-21 22:25:46.560000', 0, '2024-07-21 22:25:46.560000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MjMyMw==\",\"code\":\"2323\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (5, '2024-07-21 22:26:59.562000', 0, '2024-07-21 22:26:59.562000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2YWE=\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (6, '2024-07-21 22:27:13.065000', 0, '2024-07-21 22:27:13.065000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2YWE=\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (7, '2024-07-21 22:27:18.009000', 0, '2024-07-21 22:27:18.009000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2YWE=\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (8, '2024-07-21 22:31:22.693000', 0, '2024-07-21 22:31:22.693000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (9, '2024-07-21 22:32:19.619000', 0, '2024-07-21 22:32:19.619000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 1, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (10, '2024-07-21 22:36:02.872000', 0, '2024-07-21 22:36:02.872000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (11, '2024-07-21 22:36:17.184000', 0, '2024-07-21 22:36:17.184000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (12, '2024-07-21 22:37:42.688000', 0, '2024-07-21 22:37:42.688000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 1, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (13, '2024-07-21 22:41:00.809000', 1, '2024-07-21 22:41:00.809000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (14, '2024-07-21 22:51:54.718000', 1, '2024-07-21 22:51:54.718000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xvtb\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (15, '2024-07-21 23:02:50.245000', 1, '2024-07-21 23:02:50.245000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"xyar\",\"codeUid\":\"969e6eef-9842-43df-8e76-7d9df9708fb0\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (16, '2024-07-21 23:03:43.857000', 1, '2024-07-21 23:03:43.857000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"wz2u\",\"codeUid\":\"76c699c8-600c-46b3-89fb-fe9fabc651f1\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (17, '2024-07-21 23:05:14.130000', 1, '2024-07-21 23:05:14.130000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"n35ej\",\"codeUid\":\"8dec5151-6730-4ee2-832b-269fab819fdc\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (18, '2024-07-21 23:06:29.912000', 1, '2024-07-21 23:06:29.912000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"4sqAg\",\"codeUid\":\"43b8d2a0-d05a-463c-975b-59cdd0a5795e\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (19, '2024-07-21 23:33:40.422000', 1, '2024-07-21 23:33:40.422000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"FgUny\",\"codeUid\":\"44794d34-c9f1-4074-b465-e1834ef67d7a\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (20, '2024-07-21 23:34:46.754000', 1, '2024-07-21 23:34:46.754000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"FYJV4\",\"codeUid\":\"08606f0e-b059-4f64-bc2e-f1ebea703b17\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (21, '2024-07-24 10:22:13.726000', 0, '2024-07-24 10:22:13.726000', 0, NULL, NULL, 'Apifox/1.0.0 (https://apifox.com)', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"财务角色\",\"description\":\"测试添加财务角色\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (22, '2024-07-24 10:25:05.748000', 0, '2024-07-24 10:25:05.748000', 0, NULL, NULL, 'Apifox/1.0.0 (https://apifox.com)', '删除某个角色', '', 50, 'cn.light.admin.controller.RoleController.delete()', '', '0:0:0:0:0:0:0:1', 1, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (23, '2024-07-26 22:57:16.597000', 0, '2024-07-26 22:57:16.597000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"sjlp\",\"codeUid\":\"640ab2cb-54b5-4b07-9833-730958e3b8bf\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (24, '2024-07-26 22:57:27.004000', 0, '2024-07-26 22:57:27.004000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"TYBj0\",\"codeUid\":\"47beed66-219c-4b8c-b6b0-21396043f6ad\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (25, '2024-07-26 22:57:38.917000', 0, '2024-07-26 22:57:38.917000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"JLZJI\",\"codeUid\":\"1c9ab68b-899c-4c48-96e1-586a58403789\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (26, '2024-07-26 22:58:18.388000', 0, '2024-07-26 22:58:18.388000', 0, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"GE5EJ\",\"codeUid\":\"5bd6a4cf-a680-4b46-b19f-c3a6e3d65e67\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (27, '2024-07-26 23:08:49.461000', 1, '2024-07-26 23:08:49.461000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"orxub\",\"codeUid\":\"5178cac9-e024-4e43-94fb-a5d04dde0b53\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (28, '2024-07-26 23:08:56.801000', 1, '2024-07-26 23:08:56.801000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"9xfpo\",\"codeUid\":\"55ac155f-e8f6-4e07-b2de-13ca1eed35bf\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (29, '2024-07-26 23:09:08.058000', 1, '2024-07-26 23:09:08.058000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"3PqLU\",\"codeUid\":\"093b1fa6-2ed6-4687-86f4-777fc1721b0e\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (30, '2024-07-26 23:14:31.772000', 1, '2024-07-26 23:14:31.772000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"8CJ17\",\"codeUid\":\"5c356cad-db6c-4ebf-92d4-1af5ae2cea2a\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (31, '2024-07-26 23:14:40.656000', 1, '2024-07-26 23:14:40.656000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"HPFR5\",\"codeUid\":\"86677c03-6593-43cb-a72c-73619eb19170\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (32, '2024-07-26 23:22:58.675000', 1, '2024-07-26 23:22:58.675000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"E5GLa\",\"codeUid\":\"79449043-f2b7-4427-9d9a-9abf717462d6\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (33, '2024-07-26 23:26:10.253000', 1, '2024-07-26 23:26:10.253000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"hfz95\",\"codeUid\":\"f9d2bd91-2fa0-48c1-a563-af72480f1eb6\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (34, '2024-07-26 23:26:22.644000', 1, '2024-07-26 23:26:22.644000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"hfz95\",\"codeUid\":\"f9d2bd91-2fa0-48c1-a563-af72480f1eb6\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (35, '2024-07-26 23:30:57.275000', 1, '2024-07-26 23:30:57.275000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"OCzviv\",\"codeUid\":\"dac19980-06f4-40bf-9f69-02e97c4271e2\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (36, '2024-07-26 23:31:07.672000', 1, '2024-07-26 23:31:07.672000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"JNO5Q\",\"codeUid\":\"f9456e0a-490d-473f-9de5-629dabc45185\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (37, '2024-07-26 23:32:57.763000', 1, '2024-07-26 23:32:57.763000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"JIXhl\",\"codeUid\":\"d7ff5a44-76f0-415e-9a59-6e35ee85b7f6\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (38, '2024-07-26 23:33:51.235000', 1, '2024-07-26 23:33:51.235000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"seywh\",\"codeUid\":\"a80a2fb1-cdf2-44be-bce6-def6f97ea3d0\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (39, '2024-07-26 23:35:13.215000', 1, '2024-07-31 08:17:54.032000', 100, NULL, 'xxxxxxxxxxxxxx', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"IArig\",\"codeUid\":\"a411bbef-d2a7-40f4-a8ad-bd6b068e2c34\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (40, '2024-07-26 23:35:20.617000', 1, '2024-07-27 15:13:42.322000', 1, NULL, 'xxxxxxxxxxxxxx', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"Her66\",\"codeUid\":\"8e51847c-b946-49c0-a168-777dada74365\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (41, '2024-07-26 23:37:48.243000', 1, '2024-07-27 14:49:48.589000', 0, NULL, 'xxxxxxxxxxxxxx', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (42, '2024-07-27 22:45:21.830000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU5Ng==\",\"code\":\"tmpvs\",\"codeUid\":\"8695e0ba-ee88-44e7-abe3-ef16f85ef24f\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (43, '2024-07-27 22:45:32.362000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"tmpvs\",\"codeUid\":\"8695e0ba-ee88-44e7-abe3-ef16f85ef24f\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (44, '2024-07-27 22:53:49.858000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"7w8ss\",\"codeUid\":\"17f4a950-0d48-4e2d-b78d-ee5f8957845a\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (45, '2024-07-27 23:07:43.835000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"pyxx\",\"codeUid\":\"4b3cee03-32c4-488e-82b0-c77ee7b7b12d\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (46, '2024-07-27 23:07:56.584000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"BiJ3x\",\"codeUid\":\"2b088562-4347-4fb2-aa84-b7dbb0407a02\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (47, '2024-07-27 23:08:13.266000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"o3cfv\",\"codeUid\":\"87de7807-4877-4673-a0d8-ea62a7162df9\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (48, '2024-07-27 23:51:23.409000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"TPb7f\",\"codeUid\":\"d5bb08a1-1977-402a-9e3f-78f2e956aa12\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (49, '2024-07-27 23:54:43.678000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"TN7Ge\",\"codeUid\":\"3f7a7287-584d-4ddc-ba6e-e8b244410b8b\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (50, '2024-07-28 12:02:02.673000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"Rgzsk\",\"codeUid\":\"10471aaf-7def-4e7d-8541-b6f786971460\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (51, '2024-07-28 12:10:55.239000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"bxfkj\",\"codeUid\":\"9f9c4c92-82b0-41cb-b50e-fcb4fb4ca5a0\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (52, '2024-07-28 12:46:26.126000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"oioa4\",\"codeUid\":\"00a61480-a49a-47d0-adb3-fe67d25d4ff7\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (53, '2024-07-28 12:47:53.870000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"keygp\",\"codeUid\":\"e72de13d-6ba5-447f-8937-b3de57209579\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (54, '2024-07-28 12:49:10.840000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"INBzf\",\"codeUid\":\"e6ed7e9d-a770-4802-9ed3-58304aa86fb8\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (55, '2024-07-28 12:49:22.532000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"INBZF\",\"codeUid\":\"e6ed7e9d-a770-4802-9ed3-58304aa86fb8\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (56, '2024-07-28 12:49:29.756000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"60dbB\",\"codeUid\":\"e8c64116-0b0b-4b15-9853-68172e70280c\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (57, '2024-07-28 12:50:28.157000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"hxcjp\",\"codeUid\":\"bd2d4897-6254-43cf-91c9-c41ff3a9289c\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (58, '2024-07-28 13:55:43.979000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"E1ehc\",\"codeUid\":\"fa021d65-350d-47c6-ba7e-3b0881269a9f\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (59, '2024-07-28 13:56:57.515000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"cu6dq\",\"codeUid\":\"2bbb8769-2ddb-4f6b-a474-21a248a0f418\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (60, '2024-07-28 13:57:04.462000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"Ngiyk\",\"codeUid\":\"43aab413-d5c9-4b0b-8e6a-edfaf9e64e3f\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (61, '2024-07-28 14:02:43.584000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"wqcrp\",\"codeUid\":\"79254c64-bec9-4727-bd59-2fb5f2828601\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (62, '2024-07-28 14:07:23.916000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"8YybI\",\"codeUid\":\"9a71789c-704c-4828-9e69-24faa94adf47\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (63, '2024-07-28 14:07:30.720000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"30tmj\",\"codeUid\":\"4d0f530e-ef5a-460b-a787-578c8ca9b7bc\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (64, '2024-07-28 14:08:50.122000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"BLU4u\",\"codeUid\":\"088fe4d8-5234-4a5c-84e4-a57aa95184f7\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (65, '2024-07-28 14:11:12.499000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"dcfpl\",\"codeUid\":\"1de989a0-6adf-4ec3-8759-8da613c7aebb\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (66, '2024-07-28 14:15:14.865000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"ehf86\",\"codeUid\":\"049855e6-2a0c-4ae1-bf8f-abdef6de5617\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (67, '2024-07-28 14:16:46.170000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"zqsdq\",\"codeUid\":\"1e919f51-2b39-4ddf-915a-72f79876a3b0\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (68, '2024-07-28 14:19:58.124000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"iwh55\",\"codeUid\":\"1db038dd-37ad-46a3-b2c5-2bc845bf6ae4\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (69, '2024-07-28 14:21:19.506000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"iwh55\",\"codeUid\":\"1db038dd-37ad-46a3-b2c5-2bc845bf6ae4\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (70, '2024-07-28 15:49:24.670000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (71, '2024-07-28 15:50:17.043000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"RR9c6\",\"codeUid\":\"6fa1b691-38f7-42e2-a7bb-1130a1fcdcb9\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (72, '2024-07-28 15:50:23.988000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"arki6\",\"codeUid\":\"1c76bd42-9f70-4899-aca0-6e9198ee6bdd\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (73, '2024-07-28 16:43:03.326000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"空道公司\",\"code\":\"002\",\"description\":\"公司用户信息可以全部权限\",\"id\":1,\"createTime\":1662474803000,\"updateTime\":1662519828000,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (74, '2024-07-28 16:43:27.771000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"空道公司\",\"code\":\"003\",\"description\":\"公司用户信息可以全部权限\",\"id\":1,\"createTime\":1662474803000,\"updateTime\":1722156183000,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (75, '2024-07-28 16:43:51.529000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"山东项目\",\"code\":\"001\",\"description\":\"描述\",\"id\":2,\"createTime\":1662475664000,\"updateTime\":1662475664000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (76, '2024-07-28 16:44:03.881000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"管理员\",\"code\":\"003\",\"description\":\"公司用户信息可以全部权限\",\"id\":1,\"createTime\":1662474803000,\"updateTime\":1722156207000,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (77, '2024-07-28 16:44:16.640000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"财务部\",\"code\":\"002  \",\"description\":\"描述\",\"id\":2,\"createTime\":1662475664000,\"updateTime\":1722156231000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (78, '2024-07-28 16:44:24.171000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"管理员\",\"code\":\"001\",\"description\":\"公司用户信息可以全部权限\",\"id\":1,\"createTime\":1662474803000,\"updateTime\":1722156243000,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (79, '2024-07-28 16:44:51.729000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"市场部\",\"code\":\"003\",\"description\":\"市场部门相关角色\",\"createTime\":1662475664000,\"updateTime\":1722156256000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (80, '2024-07-28 16:47:52.137000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"pwIN8\",\"codeUid\":\"46bbb8a1-2560-477f-9edc-7e18bfe53b97\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (81, '2024-07-28 16:48:35.609000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '修改角色', '', 50, 'cn.light.admin.controller.RoleController.save()', '{\"name\":\"管理员\",\"code\":\"admin\",\"description\":\"公司用户信息可以全部权限\",\"id\":1,\"createTime\":1662474803000,\"updateTime\":1722156264000,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (82, '2024-07-28 16:48:37.651000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 0, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (83, '2024-07-28 16:48:52.390000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"H3pGt\",\"codeUid\":\"823ee5ff-bf79-451b-9151-338a3c584c77\"}', '0:0:0:0:0:0:0:1', 0, NULL, NULL, NULL);
INSERT INTO `sys_log` VALUES (84, '2024-07-28 16:49:54.255000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"sepbi\",\"codeUid\":\"15e93064-809f-4a01-aba4-a1955b85d4f2\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (85, '2024-07-28 16:52:41.884000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"vbr48\",\"codeUid\":\"95afc1b7-0611-4012-af48-a35bd0debd89\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL, NULL);
INSERT INTO `sys_log` VALUES (86, '2024-07-28 16:52:54.701000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '删除某个角色', '', 50, 'cn.light.admin.controller.RoleController.delete()', '', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (87, '2024-08-03 09:28:14.841000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"ND7zu\",\"codeUid\":\"f5cc8ba5-1bd1-406a-af33-b50a6243b26e\"}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (88, '2024-08-03 09:29:26.982000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"Picnj\",\"codeUid\":\"273a9361-737f-45ef-97a2-c2451d9acbaf\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (89, '2024-08-03 09:48:13.996000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (90, '2024-08-03 09:56:54.504000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"5rktf\",\"codeUid\":\"c2d109f6-7edc-423b-8cab-9f76f8ca0164\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (91, '2024-08-03 12:08:28.544000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (92, '2024-08-03 12:08:48.049000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (93, '2024-08-03 12:09:16.898000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (94, '2024-08-03 12:13:57.953000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (95, '2024-08-03 12:18:21.810000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"width\\\":100,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (96, '2024-08-03 12:19:13.871000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":50,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"width\\\":100,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (97, '2024-08-03 12:19:23.231000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":50,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"width\\\":120,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (98, '2024-08-03 12:19:33.000000', 1, '2024-08-03 14:07:08.611000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":50,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (104, '2024-08-03 13:33:04.245000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '删除日志', '', 50, 'cn.light.admin.controller.LogController.delete()', '', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (105, '2024-08-03 13:33:12.000000', 1, '2024-08-03 14:07:31.497000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '删除日志', '', 50, 'cn.light.admin.controller.LogController.delete()', 'fdsf', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (106, '2024-08-03 14:06:05.000000', 1, '2024-08-03 14:10:17.273000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改存储列设置表\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.TableColumnController.save()\",\"params\":\"{\\\"tableName\\\":\\\"log\\\",\\\"columns\\\":\\\"[{\\\\\\\"columnKey\\\\\\\":\\\\\\\"username\\\\\\\",\\\\\\\"sort\\\\\\\":1,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"requestIp\\\\\\\",\\\\\\\"sort\\\\\\\":2,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"address\\\\\\\",\\\\\\\"sort\\\\\\\":3,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"description\\\\\\\",\\\\\\\"sort\\\\\\\":4,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"browser\\\\\\\",\\\\\\\"sort\\\\\\\":5,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"time\\\\\\\",\\\\\\\"sort\\\\\\\":6,\\\\\\\"width\\\\\\\":50,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"method\\\\\\\",\\\\\\\"sort\\\\\\\":7,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"createTime\\\\\\\",\\\\\\\"sort\\\\\\\":8,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"params\\\\\\\",\\\\\\\"sort\\\\\\\":9,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"action\\\\\\\",\\\\\\\"sort\\\\\\\":10,\\\\\\\"width\\\\\\\":90,\\\\\\\"showFlag\\\\\\\":true}]\\\"}\",\"logType\":50,\"id\":98,\"createTime\":1722658773000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (107, '2024-08-03 14:07:08.605000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改存储列设置表\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.TableColumnController.save()\",\"params\":\"{\\\"tableName\\\":\\\"log\\\",\\\"columns\\\":\\\"[{\\\\\\\"columnKey\\\\\\\":\\\\\\\"username\\\\\\\",\\\\\\\"sort\\\\\\\":1,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"requestIp\\\\\\\",\\\\\\\"sort\\\\\\\":2,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"address\\\\\\\",\\\\\\\"sort\\\\\\\":3,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"description\\\\\\\",\\\\\\\"sort\\\\\\\":4,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"browser\\\\\\\",\\\\\\\"sort\\\\\\\":5,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"time\\\\\\\",\\\\\\\"sort\\\\\\\":6,\\\\\\\"width\\\\\\\":50,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"method\\\\\\\",\\\\\\\"sort\\\\\\\":7,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"createTime\\\\\\\",\\\\\\\"sort\\\\\\\":8,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"params\\\\\\\",\\\\\\\"sort\\\\\\\":9,\\\\\\\"showFlag\\\\\\\":true},{\\\\\\\"columnKey\\\\\\\":\\\\\\\"action\\\\\\\",\\\\\\\"sort\\\\\\\":10,\\\\\\\"width\\\\\\\":90,\\\\\\\"showFlag\\\\\\\":true}]\\\"}\",\"logType\":50,\"id\":98,\"createTime\":1722658773000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (108, '2024-08-03 14:07:31.000000', 1, '2024-08-03 14:10:12.688000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"删除日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.delete()\",\"params\":\"fdsf\",\"logType\":50,\"id\":105,\"createTime\":1722663192000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (109, '2024-08-03 14:08:27.632000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"删除日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.delete()\\\",\\\"params\\\":\\\"fdsf\\\",\\\"logType\\\":50,\\\"id\\\":105,\\\"createTime\\\":1722663192000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":108,\"createTime\":1722665251000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (110, '2024-08-03 14:10:10.330000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"删除日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.delete()\\\",\\\"params\\\":\\\"fdsf\\\",\\\"logType\\\":50,\\\"id\\\":105,\\\"createTime\\\":1722663192000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":108,\"createTime\":1722665251000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (111, '2024-08-03 14:10:11.747000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"删除日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.delete()\\\",\\\"params\\\":\\\"fdsf\\\",\\\"logType\\\":50,\\\"id\\\":105,\\\"createTime\\\":1722663192000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":108,\"createTime\":1722665251000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (112, '2024-08-03 14:10:12.437000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"删除日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.delete()\\\",\\\"params\\\":\\\"fdsf\\\",\\\"logType\\\":50,\\\"id\\\":105,\\\"createTime\\\":1722663192000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":108,\"createTime\":1722665251000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (113, '2024-08-03 14:10:12.683000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"删除日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.delete()\\\",\\\"params\\\":\\\"fdsf\\\",\\\"logType\\\":50,\\\"id\\\":105,\\\"createTime\\\":1722663192000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":108,\"createTime\":1722665251000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (114, '2024-08-03 14:10:16.600000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改存储列设置表\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.TableColumnController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"tableName\\\\\\\":\\\\\\\"log\\\\\\\",\\\\\\\"columns\\\\\\\":\\\\\\\"[{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true}]\\\\\\\"}\\\",\\\"logType\\\":50,\\\"id\\\":98,\\\"createTime\\\":1722658773000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":106,\"createTime\":1722665165000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (115, '2024-08-03 14:10:17.000000', 1, '2024-08-03 14:10:53.000000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改存储列设置表\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.TableColumnController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"tableName\\\\\\\":\\\\\\\"log\\\\\\\",\\\\\\\"columns\\\\\\\":\\\\\\\"[{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\":true}]\\\\\\\"}\\\",\\\"logType\\\":50,\\\"id\\\":98,\\\"createTime\\\":1722658773000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":106,\"createTime\":1722665165000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (116, '2024-08-03 14:10:53.000000', 1, '2024-08-03 14:11:57.000000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"username\\\\\\\":\\\\\\\"18800000000\\\\\\\",\\\\\\\"requestIp\\\\\\\":\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\",\\\\\\\"description\\\\\\\":\\\\\\\"新增|修改存储列设置表\\\\\\\",\\\\\\\"browser\\\\\\\":\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\",\\\\\\\"time\\\\\\\":0,\\\\\\\"method\\\\\\\":\\\\\\\"cn.light.admin.controller.TableColumnController.save()\\\\\\\",\\\\\\\"params\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"tableName\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"log\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"columns\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"[{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true}]\\\\\\\\\\\\\\\"}\\\\\\\",\\\\\\\"logType\\\\\\\":50,\\\\\\\"id\\\\\\\":98,\\\\\\\"createTime\\\\\\\":1722658773000,\\\\\\\"creatorId\\\\\\\":1}\\\",\\\"logType\\\":50,\\\"id\\\":106,\\\"createTime\\\":1722665165000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":115,\"createTime\":1722665417000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (117, '2024-08-03 14:11:57.408000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"username\\\\\\\":\\\\\\\"18800000000\\\\\\\",\\\\\\\"requestIp\\\\\\\":\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\",\\\\\\\"description\\\\\\\":\\\\\\\"新增|修改日志\\\\\\\",\\\\\\\"browser\\\\\\\":\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\",\\\\\\\"time\\\\\\\":0,\\\\\\\"method\\\\\\\":\\\\\\\"cn.light.admin.controller.LogController.save()\\\\\\\",\\\\\\\"params\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"18800000000\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"新增|修改存储列设置表\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\":0,\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"cn.light.admin.controller.TableColumnController.save()\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"tableName\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"log\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columns\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"[{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true}]\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"}\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"logType\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\"id\\\\\\\\\\\\\\\":98,\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\":1722658773000,\\\\\\\\\\\\\\\"creatorId\\\\\\\\\\\\\\\":1}\\\\\\\",\\\\\\\"logType\\\\\\\":50,\\\\\\\"id\\\\\\\":106,\\\\\\\"createTime\\\\\\\":1722665165000,\\\\\\\"creatorId\\\\\\\":1}\\\",\\\"logType\\\":50,\\\"id\\\":115,\\\"createTime\\\":1722665417000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":116,\"createTime\":1722665453000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (118, '2024-08-03 14:12:54.000000', 1, '2024-08-03 14:15:58.698000', 1, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"username\\\\\\\":\\\\\\\"18800000000\\\\\\\",\\\\\\\"requestIp\\\\\\\":\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\",\\\\\\\"description\\\\\\\":\\\\\\\"新增|修改存储列设置表\\\\\\\",\\\\\\\"browser\\\\\\\":\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\",\\\\\\\"time\\\\\\\":0,\\\\\\\"method\\\\\\\":\\\\\\\"cn.light.admin.controller.TableColumnController.save()\\\\\\\",\\\\\\\"params\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"tableName\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"log\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"columns\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"[{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true}]\\\\\\\\\\\\\\\"}\\\\\\\",\\\\\\\"logType\\\\\\\":50,\\\\\\\"id\\\\\\\":98,\\\\\\\"createTime\\\\\\\":1722658773000,\\\\\\\"creatorId\\\\\\\":1}\\\",\\\"logType\\\":50,\\\"id\\\":106,\\\"createTime\\\":1722665165000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":115,\"createTime\":1722665417000,\"updateTime\":1722665453000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (119, '2024-08-03 14:13:44.829000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"username\\\\\\\":\\\\\\\"18800000000\\\\\\\",\\\\\\\"requestIp\\\\\\\":\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\",\\\\\\\"description\\\\\\\":\\\\\\\"新增|修改日志\\\\\\\",\\\\\\\"browser\\\\\\\":\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\",\\\\\\\"time\\\\\\\":0,\\\\\\\"method\\\\\\\":\\\\\\\"cn.light.admin.controller.LogController.save()\\\\\\\",\\\\\\\"params\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"18800000000\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"新增|修改存储列设置表\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\":0,\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"cn.light.admin.controller.TableColumnController.save()\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"tableName\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"log\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columns\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"[{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true}]\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"}\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"logType\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\"id\\\\\\\\\\\\\\\":98,\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\":1722658773000,\\\\\\\\\\\\\\\"creatorId\\\\\\\\\\\\\\\":1}\\\\\\\",\\\\\\\"logType\\\\\\\":50,\\\\\\\"id\\\\\\\":106,\\\\\\\"createTime\\\\\\\":1722665165000,\\\\\\\"creatorId\\\\\\\":1}\\\",\\\"logType\\\":50,\\\"id\\\":115,\\\"createTime\\\":1722665417000,\\\"creatorId\\\":1}\",\"logType\":50,\"id\":116,\"createTime\":1722665453000,\"updateTime\":1722665517000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (120, '2024-08-03 14:15:58.691000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.LogController.save()', '{\"username\":\"18800000000\",\"requestIp\":\"0:0:0:0:0:0:0:1\",\"description\":\"新增|修改日志\",\"browser\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\",\"time\":0,\"method\":\"cn.light.admin.controller.LogController.save()\",\"params\":\"{\\\"username\\\":\\\"18800000000\\\",\\\"requestIp\\\":\\\"0:0:0:0:0:0:0:1\\\",\\\"description\\\":\\\"新增|修改日志\\\",\\\"browser\\\":\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\",\\\"time\\\":0,\\\"method\\\":\\\"cn.light.admin.controller.LogController.save()\\\",\\\"params\\\":\\\"{\\\\\\\"username\\\\\\\":\\\\\\\"18800000000\\\\\\\",\\\\\\\"requestIp\\\\\\\":\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\",\\\\\\\"description\\\\\\\":\\\\\\\"新增|修改日志\\\\\\\",\\\\\\\"browser\\\\\\\":\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\",\\\\\\\"time\\\\\\\":0,\\\\\\\"method\\\\\\\":\\\\\\\"cn.light.admin.controller.LogController.save()\\\\\\\",\\\\\\\"params\\\\\\\":\\\\\\\"{\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"18800000000\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"0:0:0:0:0:0:0:1\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"新增|修改存储列设置表\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\":0,\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"cn.light.admin.controller.TableColumnController.save()\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\"{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"tableName\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"log\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columns\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"[{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"username\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":1,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"requestIp\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":2,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"address\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":3,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"description\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":4,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"browser\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":5,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"time\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":6,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"method\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":7,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":8,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"params\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":9,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true},{\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"columnKey\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"action\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"sort\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":10,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"width\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":90,\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"showFlag\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\":true}]\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\"}\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"logType\\\\\\\\\\\\\\\":50,\\\\\\\\\\\\\\\"id\\\\\\\\\\\\\\\":98,\\\\\\\\\\\\\\\"createTime\\\\\\\\\\\\\\\":1722658773000,\\\\\\\\\\\\\\\"creatorId\\\\\\\\\\\\\\\":1}\\\\\\\",\\\\\\\"logType\\\\\\\":50,\\\\\\\"id\\\\\\\":106,\\\\\\\"createTime\\\\\\\":1722665165000,\\\\\\\"creatorId\\\\\\\":1}\\\",\\\"logType\\\":50,\\\"id\\\":115,\\\"createTime\\\":1722665417000,\\\"updateTime\\\":1722665453000,\\\"creatorId\\\":1,\\\"updaterId\\\":1}\",\"logType\":50,\"id\":118,\"createTime\":1722665574000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (121, '2024-08-03 14:19:36.489000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":false},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":51,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (122, '2024-08-03 17:22:53.094000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (123, '2024-08-03 17:23:10.254000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (124, '2024-08-03 17:24:14.528000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (125, '2024-08-03 17:24:30.238000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (126, '2024-08-03 17:25:13.374000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (127, '2024-08-03 17:25:15.298000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (128, '2024-08-03 17:25:16.002000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (129, '2024-08-03 17:26:45.561000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (130, '2024-08-03 17:27:06.979000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (131, '2024-08-03 17:30:59.226000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (132, '2024-08-03 17:36:26.787000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (133, '2024-08-03 17:36:33.139000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (134, '2024-08-03 17:38:31.390000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (135, '2024-08-03 17:38:46.140000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (136, '2024-08-03 17:40:03.259000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (137, '2024-08-03 17:40:04.055000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (138, '2024-08-03 17:40:25.223000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (139, '2024-08-03 17:40:25.470000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (140, '2024-08-03 17:40:25.594000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (141, '2024-08-03 17:40:25.764000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (142, '2024-08-03 17:41:22.635000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (143, '2024-08-03 17:41:47.190000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (144, '2024-08-03 17:43:57.036000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (145, '2024-08-03 17:44:12.653000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (146, '2024-08-03 17:52:59.466000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (147, '2024-08-03 18:05:01.178000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (148, '2024-08-03 18:08:19.047000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (149, '2024-08-03 18:08:38.413000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (150, '2024-08-03 18:09:39.699000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (151, '2024-08-03 18:11:50.978000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (152, '2024-08-03 18:12:14.649000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (153, '2024-08-03 18:14:56.220000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (154, '2024-08-03 18:23:31.924000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":2,\\\"showFlag\\\":false},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":false},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":51,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (155, '2024-08-03 18:23:40.184000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"username\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"requestIp\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"address\\\",\\\"sort\\\":3,\\\"showFlag\\\":false},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"browser\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"time\\\",\\\"sort\\\":6,\\\"width\\\":51,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"method\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"createTime\\\",\\\"sort\\\":8,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"params\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":90,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (156, '2024-08-03 19:22:53.081000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"QAIBz\",\"codeUid\":\"a609ebe0-0824-4ddf-b2a9-50aa49e9f933\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (157, '2024-08-03 19:23:54.667000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"8JNov\",\"codeUid\":\"3936ae58-b9d5-4224-8fd4-472060f4b806\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (158, '2024-08-03 19:55:13.052000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"ico\":\"\",\"fileName\":\"\",\"extend\":\"\",\"filePath\":\"\",\"fileType\":0,\"fileSize\":0,\"urlPath\":\"\",\"uuid\":\"\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (159, '2024-08-03 19:55:38.735000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"ico\":\"\",\"fileName\":\"\",\"extend\":\"\",\"filePath\":\"\",\"fileType\":0,\"fileSize\":0,\"urlPath\":\"\",\"uuid\":\"\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (160, '2024-08-03 19:56:08.397000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"ico\":\"\",\"fileName\":\"\",\"extend\":\"\",\"filePath\":\"\",\"fileType\":0,\"fileSize\":0,\"urlPath\":\"\",\"uuid\":\"\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (161, '2024-08-03 19:57:41.388000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"ico\":\"\",\"fileName\":\"\",\"extend\":\"3\",\"filePath\":\"3434\",\"fileType\":0,\"fileSize\":0,\"urlPath\":\"434\",\"uuid\":\"3434\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (162, '2024-08-04 11:39:06.009000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"fileName\":\"7830145706747284830_2985257681418489697_包装前检，包装后检相关车间库存问题\",\"extend\":\"doc\",\"filePath\":\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc\",\"fileType\":1,\"fileSize\":224768,\"urlPath\":\"2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc\",\"uuid\":\"dsf\",\"id\":6,\"createTime\":1722741008000,\"updateTime\":1722741008000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (163, '2024-08-04 11:40:23.471000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改日志', '', 50, 'cn.light.admin.controller.FileController.save()', '{\"ico\":\"[\\\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/10565ad4-7f5a-405b-a0d2-5a24ac241479.png\\\"]\",\"fileName\":\"7830145706747284830_2985257681418489697_包装前检，包装后检相关车间库存问题\",\"extend\":\"doc\",\"filePath\":\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc\",\"fileType\":1,\"fileSize\":224768,\"urlPath\":\"2024/08/04/000d5242-fd78-48c8-b79b-2cfd6f7f33c2.doc\",\"uuid\":\"dsf\",\"id\":6,\"createTime\":1722741008000,\"updateTime\":1722742746000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (164, '2024-08-04 14:35:59.575000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"imgUrl\":\"[\\\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/7a467cc1-cdf8-41a8-8758-dd86844556d2.png\\\"]\",\"type\":0,\"content\":\"\",\"fileUrl\":\"\",\"projectId\":0}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (165, '2024-08-04 14:36:52.068000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":3,\"content\":\"\",\"fileUrl\":\"\",\"projectId\":0,\"id\":1,\"createTime\":1722753359000,\"updateTime\":1722753359000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (166, '2024-08-04 14:54:38.112000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"imgUrl\":\"[\\\"https://djlx-file-bucket.oss-cn-shanghai.aliyuncs.com/2024/08/04/4e054ad0-fad1-4813-a8af-ed344c8bb7c9.png\\\"]\",\"type\":20,\"content\":\"\",\"fileUrl\":\"\",\"projectId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (167, '2024-08-04 14:56:55.650000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":30,\"content\":\"\",\"fileUrl\":\"\",\"projectId\":0,\"id\":1,\"createTime\":1722753359000,\"updateTime\":1722753412000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (168, '2024-08-04 15:01:18.681000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":20,\"content\":\"<p>dsfdsfdsf</p>\",\"fileUrl\":\"\",\"projectId\":0,\"id\":2,\"createTime\":1722754478000,\"updateTime\":1722754478000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (169, '2024-08-04 15:01:29.077000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":20,\"content\":\"<p>dsfdsfdsf</p>\",\"fileUrl\":\"\",\"projectId\":0,\"id\":2,\"createTime\":1722754478000,\"updateTime\":1722754878000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (170, '2024-08-04 15:01:32.575000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":20,\"content\":\"<p>dsfdsfdsf</p>\",\"fileUrl\":\"\",\"projectId\":0,\"id\":2,\"createTime\":1722754478000,\"updateTime\":1722754889000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (171, '2024-08-04 15:01:36.741000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":20,\"content\":\"<p>dsfdsfdsfssss</p>\",\"fileUrl\":\"\",\"projectId\":0,\"id\":2,\"createTime\":1722754478000,\"updateTime\":1722754892000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (172, '2024-08-04 15:04:59.562000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"测试数据\",\"type\":20,\"content\":\"<p>dsfdsfdsfssss</p>\",\"fileUrl\":\"\",\"projectId\":0,\"publishTime\":1723359896000,\"id\":2,\"createTime\":1722754478000,\"updateTime\":1722754896000,\"creatorId\":0,\"updaterId\":0}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (173, '2024-08-04 19:35:38.312000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"10\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (174, '2024-08-04 19:39:05.837000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"system_name\",\"name\":\"系统名称\",\"group\":10,\"value\":\"轻巧之光\",\"type\":0,\"description\":\"系统名称可以展现在登录窗体\",\"id\":1,\"createTime\":1722771374000,\"updateTime\":1722771377000,\"creatorId\":0,\"updaterId\":1},{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"20\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (175, '2024-08-04 21:05:06.317000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"system_name\",\"name\":\"系统名称\",\"group\":10,\"value\":\"轻巧之光\",\"type\":0,\"description\":\"系统名称可以展现在登录窗体\",\"id\":1,\"createTime\":1722771374000,\"updateTime\":1722771377000,\"creatorId\":0,\"updaterId\":1},{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"20\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (176, '2024-08-04 21:05:12.577000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"system_name\",\"name\":\"系统名称\",\"group\":10,\"value\":\"轻巧之光\",\"type\":0,\"description\":\"系统名称可以展现在登录窗体\",\"id\":1,\"createTime\":1722771374000,\"updateTime\":1722771377000,\"creatorId\":0,\"updaterId\":1},{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"20\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"value\":\"{}\",\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (177, '2024-08-04 21:06:53.696000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"system_name\",\"name\":\"系统名称\",\"group\":10,\"value\":\"轻巧之光1\",\"type\":0,\"description\":\"系统名称可以展现在登录窗体\",\"id\":1,\"createTime\":1722771374000,\"updateTime\":1722771377000,\"creatorId\":0,\"updaterId\":1},{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"20\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"value\":\"{}\",\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (178, '2024-08-04 21:13:16.039000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '批量新增|修改系统配置', '', 50, 'cn.light.admin.controller.ConfigController.saveList()', '[{\"key\":\"system_name\",\"name\":\"系统名称\",\"group\":10,\"value\":\"轻巧之光1\",\"type\":0,\"description\":\"系统名称可以展现在登录窗体\",\"id\":1,\"createTime\":1722771374000,\"updateTime\":1722771377000,\"creatorId\":0,\"updaterId\":1},{\"key\":\"reg_contact\",\"name\":\"注册协议\",\"group\":10,\"type\":7,\"description\":\"注册协议\",\"id\":15},{\"key\":\"storageService\",\"name\":\"存储类型选择\",\"group\":40,\"value\":\"20\",\"type\":1,\"description\":\"[{\\\"v\\\":10,\\\"n\\\":\\\"本地存储\\\"},{\\\"v\\\":20,\\\"n\\\":\\\"阿里云OSS\\\"}]\",\"id\":12,\"updateTime\":1710041614000,\"updaterId\":1},{\"key\":\"ossConfig\",\"name\":\"阿里云oss配置\",\"group\":40,\"value\":\"{\\\"aliyun_accessKeyId\\\":\\\"LTAI4GAXb4A3tfxdLxHw6u5U\\\", \\\"aliyun_accessKeySecret\\\":\\\"XADubD30SLtGX4cPmdu48GEWc4jqjJ\\\", \\\"aliyun_endpoint\\\":\\\"oss-cn-shanghai.aliyuncs.com\\\",\\\"aliyun_image_bucket\\\":\\\"djlx-image-bucket\\\", \\\"aliyun_video_bucket\\\": \\\"djlx-video-bucket\\\",\\\"aliyun_file_bucket\\\": \\\"djlx-file-bucket\\\",\\\"aliyun_origin_image_url\\\":\\\"djlx-image-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_video_url\\\":\\\"djlx-video-bucket.oss-cn-shanghai.aliyuncs.com\\\", \\\"aliyun_origin_file_url\\\":\\\"djlx-file-bucket.oss-cn-shanghai.aliyuncs.com\\\" }\",\"type\":5,\"description\":\"输入配置的 json格式\",\"id\":13,\"updateTime\":1710041547000,\"updaterId\":1},{\"key\":\"localConfig\",\"name\":\"本地存储配置\",\"group\":40,\"value\":\"{}\",\"type\":5,\"description\":\"输入json配置\",\"id\":14,\"updateTime\":1710038515000,\"updaterId\":1}]', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (179, '2024-08-04 23:47:07.815000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改站内消息、短息 记录', '', 50, 'cn.light.admin.controller.MessageController.save()', '{\"type\":5,\"templateCode\":\"001\",\"receiverId\":0,\"receiverAccount\":\"0\",\"senderId\":0,\"senderAccount\":\"0\",\"state\":2,\"params\":\"\",\"message\":\"测试消息\"}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (180, '2024-08-04 23:56:08.227000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (181, '2024-08-21 09:15:06.252000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"2HOYb\",\"codeUid\":\"46263ba8-8a8e-40e3-989b-a5ac221e46a8\"}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (182, '2024-08-21 09:15:12.914000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\",\"code\":\"2HOYb\",\"codeUid\":\"46263ba8-8a8e-40e3-989b-a5ac221e46a8\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (183, '2024-08-21 09:15:48.790000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"2HOYb\",\"codeUid\":\"46263ba8-8a8e-40e3-989b-a5ac221e46a8\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (184, '2024-08-21 09:16:34.262000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"2HOYb\",\"codeUid\":\"46263ba8-8a8e-40e3-989b-a5ac221e46a8\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (185, '2024-08-21 09:38:03.377000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改订单', '', 50, 'cn.light.admin.controller.OrderController.save()', '{\"code\":\"8bga\",\"state\":1,\"amount\":2}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (186, '2024-08-21 09:53:41.354000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/127.0.0.0 Safari/537.36 Edg/127.0.0.0', '新增|修改订单', '', 50, 'cn.light.admin.controller.OrderController.save()', '{\"code\":\"8bga\",\"state\":10,\"amount\":2,\"id\":1,\"createTime\":1724204283000,\"creatorId\":1}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (187, '2024-09-27 22:03:37.318000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"name\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"url\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"perms\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"component\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"type\\\",\\\"sort\\\":6,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"sort\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"icon\\\",\\\"sort\\\":8,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"show\\\",\\\"sort\\\":9,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":10,\\\"width\\\":120,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (188, '2024-09-27 22:39:41.857000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (189, '2024-09-27 22:39:58.483000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"kzhzl\",\"codeUid\":\"3ecd41bb-3b8f-4b4d-bb34-f569a3126fc3\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (190, '2024-09-27 23:11:06.728000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (191, '2024-09-27 23:11:18.173000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"akvTI\",\"codeUid\":\"f518abc9-d2d8-4548-a43a-6c5c9d74b2a0\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (192, '2024-09-27 23:11:49.529000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (193, '2024-09-27 23:12:07.668000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"A6YhA\",\"codeUid\":\"0669aeaf-7d08-4b9e-8c49-1381ab5fdde0\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (194, '2024-09-27 23:49:27.874000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '新增|修改存储列设置表', '', 50, 'cn.light.admin.controller.TableColumnController.save()', '{\"tableName\":\"log\",\"columns\":\"[{\\\"columnKey\\\":\\\"name\\\",\\\"sort\\\":1,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"description\\\",\\\"sort\\\":2,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"perms\\\",\\\"sort\\\":3,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"component\\\",\\\"sort\\\":4,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"type\\\",\\\"sort\\\":5,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"icon\\\",\\\"sort\\\":6,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"sort\\\",\\\"sort\\\":7,\\\"showFlag\\\":true},{\\\"columnKey\\\":\\\"action\\\",\\\"sort\\\":8,\\\"width\\\":120,\\\"showFlag\\\":true}]\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (195, '2024-10-05 20:16:46.279000', 1, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '退出登录', '', 10, 'cn.light.admin.controller.UserController.logout()', '', '0:0:0:0:0:0:0:1', 1, '18800000000', NULL, NULL);
INSERT INTO `sys_log` VALUES (196, '2024-10-05 20:17:14.347000', 0, NULL, NULL, NULL, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36 Edg/129.0.0.0', '用户登录', '', 10, 'cn.light.admin.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\",\"code\":\"oUxuO\",\"codeUid\":\"692beaed-f97f-4f28-a576-5b8cac4ac440\"}', '0:0:0:0:0:0:0:1', 0, '18800000000', NULL, NULL);

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `message` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '消息内容',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息参数',
  `receiver_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息接收者账号',
  `receiver_id` int(11) NULL DEFAULT NULL COMMENT '消息接收者',
  `sender_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息发送者账号',
  `sender_id` int(11) NULL DEFAULT NULL COMMENT '消息发送者',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"1\": \"新建\",\n    \"2\": \"发送\",\n    \"3\": \"已读\",\n    \"4\": \"已删除\",\n    \"5\": \"发送错误\"\n}',
  `template_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息模板编码',
  `type` int(11) NULL DEFAULT NULL COMMENT '消息类型{\n    \"1\": \"转发\",\n    \"2\": \"验证码短信\",\n    \"3\": \"通知短信\",\n    \"4\": \"推送消息\",\n    \"5\": \"站内消息\",\n    \"6\": \"推送模板消息\"\n}',
  `code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息参数',
  `result` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '结果',
  `template_id` int(11) NULL DEFAULT NULL COMMENT '模板id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '站内消息、短息 记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_message
-- ----------------------------
INSERT INTO `sys_message` VALUES (1, '2024-08-04 23:47:07.828000', 0, NULL, NULL, NULL, '测试消息', '', '0', 0, '0', 0, 2, '001', 5, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_message_template
-- ----------------------------
DROP TABLE IF EXISTS `sys_message_template`;
CREATE TABLE `sys_message_template`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板编码',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '模板内容',
  `params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '限定参数',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '模板说明',
  `type` int(11) NULL DEFAULT NULL COMMENT '模板类型{\n    \"1\": \"转发\",\n    \"2\": \"验证码短信\",\n    \"3\": \"通知短信\",\n    \"4\": \"推送消息\",\n    \"5\": \"站内消息\",\n    \"6\": \"推送模板消息\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '消息模板' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_message_template
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限描述',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问路径',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型(权限类型 1：目录   2：菜单   3：按钮)',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `show` int(11) NULL DEFAULT NULL COMMENT '是否展现 1 展现 0 不展现',
  `version` int(11) NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', 'AuditOutlined', '系统管理', '', 0, NULL, 12, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, '角色管理', NULL, '项目管理', 'role-list', 1, NULL, 1, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/role/list.vue');
INSERT INTO `sys_permission` VALUES (3, '系统配置', NULL, '系统配置', 'sys-config', 1, NULL, 3, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/config/setting.vue');
INSERT INTO `sys_permission` VALUES (4, '用户列表', NULL, '用户列表', 'user-list', 1, NULL, 2, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/user/list.vue');
INSERT INTO `sys_permission` VALUES (6, '系统日志', NULL, '系统日志', 'log-list', 1, NULL, 5, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/log/list.vue');
INSERT INTO `sys_permission` VALUES (8, '内容管理', 'AreaChartOutlined', '项目管理', NULL, 0, NULL, 1, 1, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, '文章列表', NULL, '文章列表', 'article-list', 8, NULL, 2, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/article/list.vue');
INSERT INTO `sys_permission` VALUES (10, '文件管理', NULL, '文件管理', 'file-list', 8, NULL, 3, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/file/list.vue');
INSERT INTO `sys_permission` VALUES (11, '菜单管理', NULL, NULL, 'permission-list', 1, NULL, 1, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/permission/list.vue');
INSERT INTO `sys_permission` VALUES (12, '消息管理', NULL, '消息管理', 'message-list', 8, NULL, 2, 2, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, '/system/message/list.vue');
INSERT INTO `sys_permission` VALUES (13, '订单列表', '', '订单列表', 'order-list', 8, '', 0, 2, '2024-08-21 09:35:38', '2024-08-21 09:35:38', 1, 1, 1, NULL, NULL, NULL, '/system/order/list.vue');
INSERT INTO `sys_permission` VALUES (14, '商城管理', 'TaobaoCircleOutlined', '商城管理', '', 0, '', 2, 1, '2024-09-27 22:33:54', '2024-09-27 22:33:54', 0, 0, 1, NULL, NULL, NULL, '');
INSERT INTO `sys_permission` VALUES (15, '供货商管理', '', '供货商管理', 'supplier-list', 14, 'supplier-list', 1, 2, '2024-09-27 22:35:00', '2024-09-27 22:36:24', 0, 0, 1, NULL, NULL, NULL, '/business/supplier/list.vue');
INSERT INTO `sys_permission` VALUES (16, '园区管理', 'DatabaseFilled', '园区管理', '', 0, '', 3, 1, '2024-09-27 22:44:55', '2024-09-27 22:44:55', 1, 1, 1, NULL, NULL, NULL, '');
INSERT INTO `sys_permission` VALUES (17, '园区分类', '', '园区分类', 'zoneCategory-list', 16, '', 1, 2, '2024-09-27 22:45:50', '2024-09-27 22:45:50', 1, 1, 1, NULL, NULL, NULL, '/business/zoneCategory/list.vue');
INSERT INTO `sys_permission` VALUES (18, '分区楼层', '', '分区楼层', 'zoneFloor-list', 16, '', 2, 2, '2024-09-27 22:47:14', '2024-09-27 22:47:14', 1, 1, 1, NULL, NULL, NULL, '/business/zoneFloor/list.vue');
INSERT INTO `sys_permission` VALUES (19, '房间管理', '', '房间管理', 'zoneRoom-list', 16, '', 3, 2, '2024-09-27 22:48:08', '2024-09-27 22:48:08', 1, 1, 1, NULL, NULL, NULL, '/business/zoneRoom/list.vue');
INSERT INTO `sys_permission` VALUES (20, '租赁合同', '', '租赁合同', 'leaseContract-list', 16, '', 4, 2, '2024-09-27 22:49:18', '2024-09-27 22:49:18', 1, 1, 1, NULL, NULL, NULL, '/business/leaseContract/list.vue');
INSERT INTO `sys_permission` VALUES (21, '租赁预约', '', '租赁预约', 'leaseFrom-list', 16, '', 5, 2, '2024-09-27 22:50:38', '2024-09-27 22:50:38', 1, 1, 1, NULL, NULL, NULL, '/business/leaseFrom/list.vue');
INSERT INTO `sys_permission` VALUES (22, '服务管理', '', '服务管理', 'serviceFrom-list', 16, '', 6, 2, '2024-09-27 22:51:43', '2024-09-27 22:51:43', 1, 1, 1, NULL, NULL, NULL, '/business/serviceFrom/list.vue');
INSERT INTO `sys_permission` VALUES (23, '商品分类', '', '商品分类', 'productCategory-list', 14, '', 2, 2, '2024-09-27 22:53:07', '2024-09-27 22:53:07', 1, 1, 1, NULL, NULL, NULL, '/business/productCategory/list.vue');
INSERT INTO `sys_permission` VALUES (24, '商品列表', '', '商品列表', 'product-list', 14, '', 3, 2, '2024-09-27 22:53:35', '2024-09-27 22:53:35', 1, 1, 1, NULL, NULL, NULL, '/business/product/list.vue');
INSERT INTO `sys_permission` VALUES (25, '商品订单', '', '商品订单', 'orderProduct-list', 14, '', 4, 2, '2024-09-27 22:54:45', '2024-09-27 22:54:45', 1, 1, 1, NULL, NULL, NULL, '/business/orderProduct/list.vue');
INSERT INTO `sys_permission` VALUES (26, '商品评价', '', '商品评价', 'productComment-list', 14, '', 5, 2, '2024-09-27 22:59:47', '2024-09-27 22:59:47', 1, 1, 1, NULL, NULL, NULL, '/business/productComment/list.vue');
INSERT INTO `sys_permission` VALUES (27, '收货地址', '', '收货地址', 'memberAddress-list', 14, '', 6, 2, '2024-09-27 23:00:20', '2024-09-27 23:00:20', 1, 1, 1, NULL, NULL, NULL, '/business/memberAddress/list.vue');
INSERT INTO `sys_permission` VALUES (28, '会员管理', 'UserAddOutlined', '会员管理', '', 0, '', 0, 1, '2024-09-27 23:02:55', '2024-09-27 23:02:55', 1, 1, 1, NULL, NULL, NULL, '');
INSERT INTO `sys_permission` VALUES (29, '会员列表', '', '会员列表', 'member-list', 28, '', 1, 2, '2024-09-27 23:03:36', '2024-09-27 23:03:36', 1, 1, 1, NULL, NULL, NULL, '/system/member/list.vue');
INSERT INTO `sys_permission` VALUES (30, '广告管理', '', '广告管理', 'ad-list', 8, '', 2, 2, '2024-09-27 23:04:25', '2024-09-27 23:04:25', 1, 1, 1, NULL, NULL, NULL, '/system/ad/list.vue');
INSERT INTO `sys_permission` VALUES (31, '财务管理', 'MoneyCollectFilled', '财务管理', '', 0, '', 3, 1, '2024-09-27 23:05:27', '2024-09-27 23:05:27', 1, 1, 1, NULL, NULL, NULL, '');
INSERT INTO `sys_permission` VALUES (32, '资金日志', '', '资金日志', 'finance-list', 31, '', 1, 2, '2024-09-27 23:06:20', '2024-09-27 23:17:33', 1, 1, 1, NULL, NULL, NULL, '/business/finance/list.vue');
INSERT INTO `sys_permission` VALUES (33, '提现管理', '', '提现管理', 'memberTocash-list', 31, '', 2, 2, '2024-09-27 23:07:06', '2024-09-27 23:17:39', 1, 1, 1, NULL, NULL, NULL, '/business/memberTocash/list.vue');
INSERT INTO `sys_permission` VALUES (34, '支付配置', '', '支付配置', 'merchantPayment-list', 31, '', 3, 2, '2024-09-27 23:07:38', '2024-09-27 23:17:44', 1, 1, 1, NULL, NULL, NULL, '/business/merchantPayment/list.vue');
INSERT INTO `sys_permission` VALUES (35, '会员积分', '', '会员积分', 'memberPointLog', 28, '', 2, 2, '2024-09-27 23:08:10', '2024-09-27 23:08:10', 1, 1, 1, NULL, NULL, NULL, '/system/memberPointLog/list.vue');
INSERT INTO `sys_permission` VALUES (36, '反馈建议', '', '反馈建议', 'feedback-list', 8, '', 4, 2, '2024-09-27 23:08:54', '2024-09-27 23:08:54', 1, 1, 1, NULL, NULL, NULL, '/system/feedback/list.vue');
INSERT INTO `sys_permission` VALUES (37, '行政区域', '', '行政区域', 'areaDj-list', 8, '', 5, 2, '2024-09-27 23:10:19', '2024-09-27 23:10:19', 1, 1, 1, NULL, NULL, NULL, '/system/areaDj/list.vue');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '所属角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2022-09-06 22:33:23.000000', NULL, '2024-07-28 16:48:35.616000', 1, '公司用户信息可以全部权限', '管理员', NULL, 'admin', NULL, NULL);
INSERT INTO `sys_role` VALUES (2, '2022-09-06 22:47:44.716000', 1, '2024-07-28 16:44:16.646000', 1, '描述', '财务部', NULL, '002  ', NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (9, '2024-07-28 19:09:48.275000', 1, '2024-07-28 19:09:48.275000', 1, 8, 2, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (10, '2024-07-28 19:09:48.305000', 1, '2024-07-28 19:09:48.305000', 1, 9, 2, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (11, '2024-07-28 19:09:48.307000', 1, '2024-07-28 19:09:48.307000', 1, 10, 2, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (55, '2024-09-27 23:11:03.706000', 1, '2024-09-27 23:11:03.706000', 1, 1, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (56, '2024-09-27 23:11:03.708000', 1, '2024-09-27 23:11:03.708000', 1, 2, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (57, '2024-09-27 23:11:03.709000', 1, '2024-09-27 23:11:03.709000', 1, 3, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (58, '2024-09-27 23:11:03.711000', 1, '2024-09-27 23:11:03.711000', 1, 4, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (59, '2024-09-27 23:11:03.712000', 1, '2024-09-27 23:11:03.712000', 1, 6, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (60, '2024-09-27 23:11:03.713000', 1, '2024-09-27 23:11:03.713000', 1, 8, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (61, '2024-09-27 23:11:03.714000', 1, '2024-09-27 23:11:03.714000', 1, 9, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (62, '2024-09-27 23:11:03.716000', 1, '2024-09-27 23:11:03.716000', 1, 10, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (63, '2024-09-27 23:11:03.718000', 1, '2024-09-27 23:11:03.718000', 1, 11, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (64, '2024-09-27 23:11:03.719000', 1, '2024-09-27 23:11:03.719000', 1, 12, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (65, '2024-09-27 23:11:03.721000', 1, '2024-09-27 23:11:03.721000', 1, 13, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (66, '2024-09-27 23:11:03.722000', 1, '2024-09-27 23:11:03.722000', 1, 28, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (67, '2024-09-27 23:11:03.723000', 1, '2024-09-27 23:11:03.723000', 1, 29, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (68, '2024-09-27 23:11:03.724000', 1, '2024-09-27 23:11:03.724000', 1, 35, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (69, '2024-09-27 23:11:03.725000', 1, '2024-09-27 23:11:03.725000', 1, 30, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (70, '2024-09-27 23:11:03.726000', 1, '2024-09-27 23:11:03.726000', 1, 36, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (71, '2024-09-27 23:11:03.727000', 1, '2024-09-27 23:11:03.727000', 1, 37, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (72, '2024-09-27 23:11:03.728000', 1, '2024-09-27 23:11:03.728000', 1, 14, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (73, '2024-09-27 23:11:03.730000', 1, '2024-09-27 23:11:03.730000', 1, 15, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (74, '2024-09-27 23:11:03.732000', 1, '2024-09-27 23:11:03.732000', 1, 23, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (75, '2024-09-27 23:11:03.734000', 1, '2024-09-27 23:11:03.734000', 1, 24, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (76, '2024-09-27 23:11:03.735000', 1, '2024-09-27 23:11:03.735000', 1, 25, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (77, '2024-09-27 23:11:03.736000', 1, '2024-09-27 23:11:03.736000', 1, 26, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (78, '2024-09-27 23:11:03.737000', 1, '2024-09-27 23:11:03.737000', 1, 27, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (79, '2024-09-27 23:11:03.738000', 1, '2024-09-27 23:11:03.738000', 1, 16, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (80, '2024-09-27 23:11:03.739000', 1, '2024-09-27 23:11:03.739000', 1, 17, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (81, '2024-09-27 23:11:03.740000', 1, '2024-09-27 23:11:03.740000', 1, 18, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (82, '2024-09-27 23:11:03.742000', 1, '2024-09-27 23:11:03.742000', 1, 19, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (83, '2024-09-27 23:11:03.743000', 1, '2024-09-27 23:11:03.743000', 1, 20, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (84, '2024-09-27 23:11:03.745000', 1, '2024-09-27 23:11:03.745000', 1, 21, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (85, '2024-09-27 23:11:03.746000', 1, '2024-09-27 23:11:03.746000', 1, 22, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (86, '2024-09-27 23:11:03.748000', 1, '2024-09-27 23:11:03.748000', 1, 31, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (87, '2024-09-27 23:11:03.749000', 1, '2024-09-27 23:11:03.749000', 1, 32, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (88, '2024-09-27 23:11:03.751000', 1, '2024-09-27 23:11:03.751000', 1, 33, 1, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (89, '2024-09-27 23:11:03.752000', 1, '2024-09-27 23:11:03.752000', 1, 34, 1, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_table_column
-- ----------------------------
DROP TABLE IF EXISTS `sys_table_column`;
CREATE TABLE `sys_table_column`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `columns` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表列',
  `table_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表名',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id 0表示通用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储列设置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_table_column
-- ----------------------------
INSERT INTO `sys_table_column` VALUES (4, '2024-08-03 12:13:57.980000', 1, '2024-09-27 23:49:27.890000', 1, NULL, '[{\"columnKey\":\"name\",\"sort\":1,\"showFlag\":true},{\"columnKey\":\"description\",\"sort\":2,\"showFlag\":true},{\"columnKey\":\"perms\",\"sort\":3,\"showFlag\":true},{\"columnKey\":\"component\",\"sort\":4,\"showFlag\":true},{\"columnKey\":\"type\",\"sort\":5,\"showFlag\":true},{\"columnKey\":\"icon\",\"sort\":6,\"showFlag\":true},{\"columnKey\":\"sort\",\"sort\":7,\"showFlag\":true},{\"columnKey\":\"action\",\"sort\":8,\"width\":120,\"showFlag\":true}]', 'log', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `height` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身高',
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `reg_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册ip',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别{\n    \"20\": \"女\",\n    \"10\": \"男\"\n}',
  `state` int(11) NOT NULL DEFAULT 0 COMMENT '状态(0,1){\n    \"40\": \"删除\",\n    \"10\": \"未启用\",\n    \"20\": \"正常\",\n    \"30\": \"禁用\"\n}',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册方式(0,1,2){\n    \"0\": \"线下注册\",\n    \"1\": \"微信\",\n    \"2\": \"支付宝\"\n}',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `weight` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '体重',
  `front_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `reverse_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `info` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '个人简介',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2022-09-06 21:32:29.000000', 1, '2024-07-21 22:37:45.456000', 1, '31369063@qq.com', '182', '0:0:0:0:0:0:0:1', '宋飞', NULL, '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '192.168.10.105', 1, 20, '1', '18800000000', '2', NULL, NULL, NULL, NULL, NULL, '18317033205');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `version` int(11) NULL DEFAULT NULL,
  `creator` int(11) NULL DEFAULT NULL,
  `updater` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '2022-09-06 22:33:47.000000', 1, '2022-09-06 22:33:52.000000', 1, 1, 1, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (2, '2022-09-29 11:44:46.977000', 1, '2022-09-29 11:44:46.977000', 1, 2, 2, NULL, NULL, NULL);
INSERT INTO `sys_user_role` VALUES (3, '2022-09-29 11:52:35.164000', 1, '2022-09-29 11:52:35.164000', 1, 2, 3, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for ze_area_dj
-- ----------------------------
DROP TABLE IF EXISTS `ze_area_dj`;
CREATE TABLE `ze_area_dj`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `area_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省市区县',
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '省市区县名',
  `is_add` int(11) NULL DEFAULT NULL COMMENT '是否修改{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `parent_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级code',
  `pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼音',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"20\": \"隐藏\",\n    \"10\": \"显示\"\n}',
  `type` int(11) NULL DEFAULT NULL COMMENT '省市区{\n    \"1\": \"省\",\n    \"2\": \"市\",\n    \"3\": \"区/县\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '行政区划实体' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_area_dj
-- ----------------------------

-- ----------------------------
-- Table structure for ze_feedback
-- ----------------------------
DROP TABLE IF EXISTS `ze_feedback`;
CREATE TABLE `ze_feedback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL COMMENT '意见反馈问题分类{\n    \"1\": \"账号问题\",\n    \"2\": \"APP问题\",\n    \"3\": \"服务问题\",\n    \"4\": \"其他服务\"\n}',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `images` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `is_read` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否已读',
  `user_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '意见反馈' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for ze_finance
-- ----------------------------
DROP TABLE IF EXISTS `ze_finance`;
CREATE TABLE `ze_finance`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `after_money` decimal(19, 2) NULL DEFAULT NULL COMMENT '变动后',
  `begin_money` decimal(19, 2) NULL DEFAULT NULL COMMENT '变动前',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `money` decimal(19, 2) NULL DEFAULT NULL COMMENT '处理金额',
  `relate_id` int(11) NULL DEFAULT NULL COMMENT '关联业务id',
  `relate_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联业务号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '业务类型{\n    \"0\": \"提现扣除\",\n    \"1\": \"销售佣金\",\n    \"2\": \"推广佣金\",\n    \"3\": \"会员推广佣金\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '余额变动' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_finance
-- ----------------------------

-- ----------------------------
-- Table structure for ze_finance_invoice
-- ----------------------------
DROP TABLE IF EXISTS `ze_finance_invoice`;
CREATE TABLE `ze_finance_invoice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发票金额',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发票存储链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '财务发票表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_finance_invoice
-- ----------------------------

-- ----------------------------
-- Table structure for ze_finance_invoice_head
-- ----------------------------
DROP TABLE IF EXISTS `ze_finance_invoice_head`;
CREATE TABLE `ze_finance_invoice_head`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `bank_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `category` int(11) NULL DEFAULT NULL,
  `date` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_default` int(11) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '发票抬头' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_finance_invoice_head
-- ----------------------------

-- ----------------------------
-- Table structure for ze_finance_pay
-- ----------------------------
DROP TABLE IF EXISTS `ze_finance_pay`;
CREATE TABLE `ze_finance_pay`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付单号',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `merchant_id` int(11) NULL DEFAULT NULL COMMENT '收款商户ID',
  `merchant_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收款商户名称',
  `merchant_pay_id` int(11) NULL DEFAULT NULL COMMENT '支付方式',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '交互消息说明',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信或者公众号OpenId',
  `pay_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `pay_state` int(11) NULL DEFAULT NULL COMMENT '订单状态{\n    \"0\": \"待支付\",\n    \"1\": \"进行中\",\n    \"2\": \"已支付\",\n    \"3\": \"已退款\",\n    \"4\": \"已取消\",\n    \"5\": \"已关闭\"\n}',
  `pay_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付时间',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付方式{\n    \"1\": \"支付宝\",\n    \"2\": \"微信\",\n    \"3\": \"余额\"\n}',
  `platform` int(11) NULL DEFAULT NULL COMMENT '平台{\n    \"0\": \"线下注册\",\n    \"1\": \"mp-weixin\",\n    \"2\": \"支付宝\",\n    \"3\": \"app\",\n    \"5\": \"web\"\n}',
  `refund_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款号',
  `relation_type` int(11) NULL DEFAULT NULL COMMENT '支付业务类型{\n    \"50\": \"其他\",\n    \"20\": \"商品\",\n    \"70\": \"B端会员购买\",\n    \"40\": \"活动定制定金\",\n    \"41\": \"活动定制尾款\",\n    \"10\": \"活动全款\",\n    \"60\": \"活动定金\",\n    \"61\": \"活动购买尾款\",\n    \"30\": \"会员购买\"\n}',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付描述',
  `trade_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付交易号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '支付记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_finance_pay
-- ----------------------------

-- ----------------------------
-- Table structure for ze_lease_contract
-- ----------------------------
DROP TABLE IF EXISTS `ze_lease_contract`;
CREATE TABLE `ze_lease_contract`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `contract_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同名称',
  `contract_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同编号',
  `customer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同结束时间',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同附件',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '合同描述',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `room_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '房间信息 (比如: A-1F-2102)',
  `start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '合同开始时间',
  `state` int(11) NULL DEFAULT NULL COMMENT '合同状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租赁合同信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_lease_contract
-- ----------------------------

-- ----------------------------
-- Table structure for ze_lease_from
-- ----------------------------
DROP TABLE IF EXISTS `ze_lease_from`;
CREATE TABLE `ze_lease_from`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '实际段结束',
  `floor_id` int(11) NULL DEFAULT NULL COMMENT '楼层',
  `floor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '楼层名称',
  `member_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `partition_id` int(11) NULL DEFAULT NULL COMMENT '分区',
  `partition_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分区名称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `read_me` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阅读说明',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `room_id` int(11) NULL DEFAULT NULL COMMENT '房间号',
  `room_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '房间名称',
  `start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时间段开始',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `zone_category_id` int(11) NULL DEFAULT NULL COMMENT '园区分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '租赁|预约表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_lease_from
-- ----------------------------

-- ----------------------------
-- Table structure for ze_member
-- ----------------------------
DROP TABLE IF EXISTS `ze_member`;
CREATE TABLE `ze_member`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `add_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '添加ip',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '详细地址',
  `all_point` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '累计积分',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `balance` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '用户余额',
  `birthday` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日(格式：01-01 月-日)',
  `brokerage_price` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '总佣金金额',
  `card_back` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `card_front` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `card_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `cash` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '提现金',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '城市id',
  `consume` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '累计消费金额',
  `fast_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快捷登录openId',
  `follow_count` int(11) NOT NULL DEFAULT 0 COMMENT '关注',
  `freeze` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '冻结佣金',
  `geo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地理位置',
  `group_id` int(11) NULL DEFAULT NULL COMMENT '用户分组id',
  `is_auth` int(11) NOT NULL DEFAULT 0 COMMENT '是否认证{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_perfect` int(11) NOT NULL DEFAULT 0 COMMENT '资料是否完善{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `last_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后一次登录ip',
  `level` int(11) NULL DEFAULT NULL COMMENT '等级',
  `likes` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '喜好(多个可以用逗号隔开)',
  `login_type` int(11) NULL DEFAULT NULL COMMENT '用户登陆类型，h5,wechat,routine{\n    \"0\": \"线下注册\",\n    \"1\": \"mp-weixin\",\n    \"2\": \"支付宝\",\n    \"3\": \"app\",\n    \"5\": \"web\"\n}',
  `logout_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '注销原因',
  `mark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户备注',
  `member_expire_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员到期时间',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '推荐人',
  `parent_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推荐人加入时间',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码（跟pwd）',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `point` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '积分',
  `push_client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '推送客户端id',
  `qq` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地区',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `spread_count` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '下级人数',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态(20为正常，10为禁止)',
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户类型',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账户(用户账户(跟accout一样))',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统前端客户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_member
-- ----------------------------

-- ----------------------------
-- Table structure for ze_member_address
-- ----------------------------
DROP TABLE IF EXISTS `ze_member_address`;
CREATE TABLE `ze_member_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人所在市',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '收货人所在市id',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人详细地址',
  `district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人所在区',
  `is_default` int(11) NULL DEFAULT NULL COMMENT '是否默认',
  `latitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '纬度',
  `longitude` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '经度',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `post_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `province` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人所在省',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收货地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_member_address
-- ----------------------------

-- ----------------------------
-- Table structure for ze_member_audit
-- ----------------------------
DROP TABLE IF EXISTS `ze_member_audit`;
CREATE TABLE `ze_member_audit`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `admin_id` int(11) NULL DEFAULT NULL COMMENT '管理员ID',
  `examine_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核结果描述',
  `examine_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审核时间',
  `id_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `id_pic1` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证正面照',
  `id_pic2` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证背面照',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `other_pic` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其他备注资料',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员备注',
  `status` int(11) NULL DEFAULT NULL COMMENT '审核状态(0：待审核，1：审核通过，2：审核未通过){\n    \"0\": \"待审核\",\n    \"1\": \"已通过\",\n    \"2\": \"未通过\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '实名认证' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_member_audit
-- ----------------------------

-- ----------------------------
-- Table structure for ze_member_point_log
-- ----------------------------
DROP TABLE IF EXISTS `ze_member_point_log`;
CREATE TABLE `ze_member_point_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT NULL COMMENT '积分余额',
  `level_type` int(11) NULL DEFAULT NULL COMMENT '等级类型',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `num` int(11) NULL DEFAULT NULL COMMENT '积分数量',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '积分变动类型{\n    \"130\": \"完善资料\",\n    \"100\": \"打赏\",\n    \"70\": \"购买评论\",\n    \"40\": \"签到\",\n    \"10\": \"消费\",\n    \"140\": \"会员购买\",\n    \"110\": \"被打赏\",\n    \"80\": \"回访问\",\n    \"50\": \"实名认证\",\n    \"150\": \"消费抵扣\",\n    \"120\": \"赠送会员积分\",\n    \"90\": \"点赞\",\n    \"60\": \"发布游记\",\n    \"30\": \"注册\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户积分记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_member_point_log
-- ----------------------------

-- ----------------------------
-- Table structure for ze_member_tocash
-- ----------------------------
DROP TABLE IF EXISTS `ze_member_tocash`;
CREATE TABLE `ze_member_tocash`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `balance` int(11) NULL DEFAULT NULL COMMENT '余额',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `money` decimal(19, 2) NULL DEFAULT NULL COMMENT '提现金额',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `state` int(11) NULL DEFAULT NULL COMMENT '提现状态{\n    \"50\": \"取消提现\",\n    \"20\": \"提现中\",\n    \"40\": \"提现失败\",\n    \"10\": \"新申请\",\n    \"60\": \"打款成功\",\n    \"45\": \"拒绝提现\",\n    \"30\": \"同意提现\"\n}',
  `type` int(11) NULL DEFAULT NULL COMMENT '提现方式{\n    \"1\": \"支付宝\",\n    \"2\": \"微信\",\n    \"3\": \"银行卡\"\n}',
  `withdrawals` decimal(19, 2) NULL DEFAULT NULL COMMENT '提现手续费',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户提现记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_member_tocash
-- ----------------------------

-- ----------------------------
-- Table structure for ze_merchant_payment
-- ----------------------------
DROP TABLE IF EXISTS `ze_merchant_payment`;
CREATE TABLE `ze_merchant_payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `cert_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '证书文件(添加文件微信支付证书（apiclient_cert.pem），前往微信商家平台下载，文件名一般为apiclient_cert.pem)',
  `key_cert_file` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '支付证书秘钥(微信支付证书密钥（apiclient_key.pem），前往微信商家平台下载。文件名一般为apiclient_key.pem)',
  `logo` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'logo(支付方式图标。建议尺寸：宽100px*高100px，jpg，jpeg，png格式)',
  `mch_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信商户id(微信支付商户号（mch_id）)',
  `merchant_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '应用id(支付宝应用APP_ID)',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回调地址(微信支付回调地址（notify_url）)',
  `pay_type` int(11) NULL DEFAULT NULL COMMENT '支付方式{\n    \"1\": \"支付宝\",\n    \"2\": \"微信\",\n    \"3\": \"余额\"\n}',
  `private_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '私钥(支付宝应用私钥（private_key）)',
  `public_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公钥(支付宝公钥（ali_public_key）)',
  `return_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '返回地址(微信支付返回地址（return_url）)',
  `secret_key` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商户秘钥(微信支付商户API密钥（paySignKey）)',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商户支付信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_merchant_payment
-- ----------------------------

-- ----------------------------
-- Table structure for ze_order_product
-- ----------------------------
DROP TABLE IF EXISTS `ze_order_product`;
CREATE TABLE `ze_order_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `cost_amount` decimal(38, 2) NULL DEFAULT NULL,
  `earnest_amount` decimal(38, 2) NULL DEFAULT NULL,
  `finance_pay_id` int(11) NULL DEFAULT NULL,
  `give_point` int(11) NULL DEFAULT NULL,
  `invoice_head_id` int(11) NULL DEFAULT NULL,
  `market_amount` decimal(38, 2) NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `order_price` decimal(38, 2) NULL DEFAULT NULL,
  `out_order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `pay_amount` decimal(38, 2) NULL DEFAULT NULL,
  `pay_time` datetime(6) NULL DEFAULT NULL,
  `point` int(11) NULL DEFAULT NULL,
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `supplier_id` int(11) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `express_company` int(11) NULL DEFAULT NULL COMMENT '快递公司{\n    \"1\": \"申通快递\",\n    \"2\": \"圆通快递\",\n    \"3\": \"韵达快递\",\n    \"4\": \"中通快递\",\n    \"5\": \"顺丰快递\",\n    \"6\": \"顺丰快递\",\n    \"7\": \"天天快递\",\n    \"8\": \"百世快递\",\n    \"9\": \"邮政快递\",\n    \"10\": \"明亮快递\",\n    \"11\": \"中邮快递\"\n}',
  `express_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快递单号',
  `member_address_id` int(11) NULL DEFAULT NULL COMMENT '收货地址',
  `order_status` int(11) NULL DEFAULT NULL COMMENT '订单状态{\n    \"80\": \"已删除\",\n    \"50\": \"已完成\",\n    \"20\": \"待发货\",\n    \"70\": \"已退款\",\n    \"40\": \"待评价\",\n    \"10\": \"待付款\",\n    \"11\": \"拼团中\",\n    \"60\": \"已关闭\",\n    \"30\": \"待收货\"\n}',
  `post_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '快递价格',
  `refund_amount` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '退款金额',
  `refund_state` int(11) NULL DEFAULT NULL COMMENT '退款状态',
  `weight` decimal(19, 2) NULL DEFAULT NULL COMMENT '产品重量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_order_product
-- ----------------------------

-- ----------------------------
-- Table structure for ze_order_product_after
-- ----------------------------
DROP TABLE IF EXISTS `ze_order_product_after`;
CREATE TABLE `ze_order_product_after`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '编码',
  `consignee` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货人',
  `consignee_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `consignee_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收货联系电话',
  `express_company` int(11) NULL DEFAULT NULL COMMENT '快递公司{\n    \"1\": \"申通快递\",\n    \"2\": \"圆通快递\",\n    \"3\": \"韵达快递\",\n    \"4\": \"中通快递\",\n    \"5\": \"顺丰快递\",\n    \"6\": \"顺丰快递\",\n    \"7\": \"天天快递\",\n    \"8\": \"百世快递\",\n    \"9\": \"邮政快递\",\n    \"10\": \"明亮快递\",\n    \"11\": \"中邮快递\"\n}',
  `express_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '快递号',
  `images` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `member_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `order_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '订单号',
  `order_product_item_id` int(11) NULL DEFAULT NULL COMMENT '订单明细id',
  `refund_amount` decimal(19, 2) NULL DEFAULT NULL COMMENT '退款金额',
  `refund_reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款原因',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `shop_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供货商头像',
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"-1\": \"商家拒绝\",\n    \"50\": \"完成\",\n    \"20\": \"商家已同意\",\n    \"40\": \"商家退款\",\n    \"10\": \"提交申请\",\n    \"60\": \"取消\",\n    \"30\": \"寄回商品\"\n}',
  `supplier_id` int(11) NULL DEFAULT NULL COMMENT '供应商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '售后退货单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_order_product_after
-- ----------------------------

-- ----------------------------
-- Table structure for ze_order_product_item
-- ----------------------------
DROP TABLE IF EXISTS `ze_order_product_item`;
CREATE TABLE `ze_order_product_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL COMMENT '数量',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '单价',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '产品id',
  `product_image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT '规格id',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格信息',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UKl3rcrraqw13kqlnvrvju2nukl`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商城订单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_order_product_item
-- ----------------------------

-- ----------------------------
-- Table structure for ze_post
-- ----------------------------
DROP TABLE IF EXISTS `ze_post`;
CREATE TABLE `ze_post`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `click_num` int(11) NULL DEFAULT NULL,
  `collect_num` int(11) NULL DEFAULT NULL,
  `comment_num` int(11) NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '内容',
  `is_anonymous` int(11) NULL DEFAULT NULL COMMENT '是否匿名',
  `is_hot` int(11) NULL DEFAULT NULL COMMENT '是否热门(1 热门，0 不热门)',
  `like_num` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id(会员id)',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发帖人(用户账户(跟accout一样))',
  `pics` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '动态配图',
  `reward` int(11) NOT NULL DEFAULT 0 COMMENT '获得的打赏',
  `show_geo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否显示发布者位置',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"20\": \"审核通过\",\n    \"10\": \"等待审核\",\n    \"30\": \"审核不通过\"\n}',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户发帖' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_post
-- ----------------------------

-- ----------------------------
-- Table structure for ze_post_comment
-- ----------------------------
DROP TABLE IF EXISTS `ze_post_comment`;
CREATE TABLE `ze_post_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评论内容',
  `goods` int(11) NULL DEFAULT NULL COMMENT '点赞数',
  `member_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员账号',
  `post_id` int(11) NULL DEFAULT NULL COMMENT '游记id',
  `reply_id` int(11) NULL DEFAULT NULL COMMENT '回复id',
  `reply_member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '回复对象',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '游记评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_post_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ze_post_comment_relation
-- ----------------------------
DROP TABLE IF EXISTS `ze_post_comment_relation`;
CREATE TABLE `ze_post_comment_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `post_comment_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '帖子用户关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_post_comment_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ze_post_relation
-- ----------------------------
DROP TABLE IF EXISTS `ze_post_relation`;
CREATE TABLE `ze_post_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `post_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `post_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '帖子用户关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_post_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ze_postage
-- ----------------------------
DROP TABLE IF EXISTS `ze_postage`;
CREATE TABLE `ze_postage`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `first_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '首重价格',
  `first_weight` decimal(19, 2) NULL DEFAULT NULL COMMENT '首重',
  `from_city_id` int(11) NULL DEFAULT NULL COMMENT '起始城市id',
  `supplier_id` int(11) NULL DEFAULT NULL COMMENT '店铺id',
  `to_city_id` int(11) NULL DEFAULT NULL COMMENT '起始城市名称',
  `unit_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '续重单价',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '邮费配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_postage
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product
-- ----------------------------
DROP TABLE IF EXISTS `ze_product`;
CREATE TABLE `ze_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `alarm_threshold_value` int(11) NULL DEFAULT NULL COMMENT '告警阀值',
  `browse` int(11) NULL DEFAULT NULL COMMENT '虚拟浏览量',
  `cate_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品分类',
  `cate_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `collect` int(11) NOT NULL DEFAULT 0 COMMENT '收藏量',
  `description` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品详情',
  `ficti` int(11) NULL DEFAULT NULL COMMENT '虚拟销量',
  `give_integral` decimal(19, 2) NULL DEFAULT NULL COMMENT '获得积分',
  `group_duration` int(11) NULL DEFAULT NULL COMMENT '成团时长,单位天',
  `group_num` int(11) NULL DEFAULT NULL COMMENT '总团数',
  `group_people_num` int(11) NULL DEFAULT NULL COMMENT '每团人数',
  `group_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '拼团价格',
  `image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品主图',
  `integral` int(11) NULL DEFAULT NULL COMMENT '积分兑换(需要多少积分兑换 只在开启积分兑换时生效)',
  `is_good` int(11) NULL DEFAULT NULL COMMENT '推荐(是否优品推荐（0：否，1：是）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_group` int(11) NULL DEFAULT NULL COMMENT '拼团状态(是否开启拼团（0：未开启，1：已开启）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_hot` int(11) NULL DEFAULT NULL COMMENT '热卖(是否热卖（0：否，1：是）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_integral` int(11) NULL DEFAULT NULL COMMENT '是否开启积分兑换(是否开启积分兑换（0：否，1：是）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_new` int(11) NULL DEFAULT NULL COMMENT '新品(是否新品（0：否，1：是）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_postage` int(11) NULL DEFAULT NULL COMMENT '是否包邮(是否包邮（0：否，1：是）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_seckill` int(11) NULL DEFAULT NULL COMMENT '秒杀状态(是否开启秒杀（0：未开启，1：已开启）){\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `is_sub` int(11) NULL DEFAULT NULL COMMENT '是否单独分佣',
  `ot_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '市场价(取规格最小市场价格)',
  `point_rule` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '可使用积分方案 50:15',
  `postage` decimal(19, 2) NULL DEFAULT NULL COMMENT '邮费',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '价格(取规格最小价格)',
  `refuse_remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `sales` int(11) NULL DEFAULT NULL COMMENT '销量',
  `seckill_end_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '秒杀结束时间',
  `seckill_start_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '秒杀开始时间',
  `skill_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '秒杀价',
  `slider_image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品轮播图',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `spec_type` int(11) NULL DEFAULT NULL COMMENT '规格(规格（1：统一规格，2：多规格）){\n    \"1\": \"统一规格\",\n    \"2\": \"多规格\"\n}',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态(状态（0：草稿，1：已下架，2：上架审核中，3:已上架，4:审核拒绝）){\n    \"0\": \"草稿箱\",\n    \"1\": \"已下架\",\n    \"2\": \"上架审核中\",\n    \"3\": \"已上架\",\n    \"4\": \"审核拒绝\"\n}',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存(取规格总库存)',
  `store_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `store_info` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '商品简介',
  `store_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `supplier_id` int(11) NULL DEFAULT NULL COMMENT '供货商',
  `supplier_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '供货商名称',
  `temp_id` int(11) NULL DEFAULT NULL COMMENT '邮费模版ID',
  `volume` decimal(19, 2) NULL DEFAULT NULL COMMENT '体积',
  `weight` decimal(19, 2) NULL DEFAULT NULL COMMENT '重量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product_car
-- ----------------------------
DROP TABLE IF EXISTS `ze_product_car`;
CREATE TABLE `ze_product_car`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `market_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '商品市场价',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `num` int(11) NULL DEFAULT NULL COMMENT '商品数量',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '商品价格',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `product_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品图片',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `sku_id` int(11) NULL DEFAULT NULL COMMENT '商品skuId',
  `sku_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品sku名称',
  `supplier_id` int(11) NULL DEFAULT NULL COMMENT '供应商id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product_car
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product_category
-- ----------------------------
DROP TABLE IF EXISTS `ze_product_category`;
CREATE TABLE `ze_product_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `count` int(11) NOT NULL DEFAULT 0 COMMENT '商品数量',
  `img` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类图片',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '分类排序',
  `state` int(11) NOT NULL DEFAULT 10 COMMENT '状态{\n    \"20\": \"隐藏\",\n    \"10\": \"显示\"\n}',
  `select_data` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '选择项(json格式 服务所需要选择项目)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product_category
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product_comment
-- ----------------------------
DROP TABLE IF EXISTS `ze_product_comment`;
CREATE TABLE `ze_product_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '评价内容',
  `good_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `parent_comment` int(11) NULL DEFAULT NULL COMMENT '父级评价',
  `pics` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片集',
  `reply` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '回复',
  `score` int(11) NULL DEFAULT NULL COMMENT '评价1-5星',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product_comment
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `ze_product_relation`;
CREATE TABLE `ze_product_relation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `member_id` int(11) NULL DEFAULT NULL,
  `member_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品ID',
  `product_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '产品用户关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product_relation
-- ----------------------------

-- ----------------------------
-- Table structure for ze_product_rule
-- ----------------------------
DROP TABLE IF EXISTS `ze_product_rule`;
CREATE TABLE `ze_product_rule`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `cost` decimal(19, 2) NULL DEFAULT NULL COMMENT '成本价',
  `group_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '拼团价',
  `image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `ot_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '市场价',
  `price` decimal(19, 2) NULL DEFAULT NULL COMMENT '会员价格',
  `product_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品编号',
  `rule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格名称',
  `rule_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '规格值',
  `settle_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '结算价',
  `skill_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '秒杀价',
  `stock` int(11) NULL DEFAULT NULL COMMENT '库存',
  `volume` decimal(19, 2) NULL DEFAULT NULL COMMENT '体积',
  `weight` decimal(19, 2) NULL DEFAULT NULL COMMENT '重量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品规格' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_product_rule
-- ----------------------------

-- ----------------------------
-- Table structure for ze_service_from
-- ----------------------------
DROP TABLE IF EXISTS `ze_service_from`;
CREATE TABLE `ze_service_from`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系人',
  `content` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '服务内容',
  `images` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务图片(支持多张)',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '服务位置',
  `member_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '会员名称',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '会员账号',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `price` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '价格',
  `read_me` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '阅读说明',
  `select_data` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '服务选择项(json格式)',
  `zone_category_id` int(11) NULL DEFAULT NULL COMMENT '园区分类id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '园区服务表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_service_from
-- ----------------------------

-- ----------------------------
-- Table structure for ze_supplier
-- ----------------------------
DROP TABLE IF EXISTS `ze_supplier`;
CREATE TABLE `ze_supplier`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `city_id` int(11) NULL DEFAULT NULL COMMENT '城市编号',
  `city_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公司名称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `expire_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '到期时间',
  `in_price` decimal(19, 2) NULL DEFAULT NULL COMMENT '加盟费',
  `in_price_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '缴纳加盟费时间',
  `margin` decimal(19, 2) NOT NULL DEFAULT 0.00 COMMENT '保证金',
  `material` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '材料',
  `member_id` int(11) NULL DEFAULT NULL COMMENT '来源关联用户',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sale_count` int(11) NOT NULL DEFAULT 0 COMMENT '销量',
  `shop_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货地址',
  `shop_banner` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '店铺图片',
  `shop_city_id` int(11) NULL DEFAULT NULL COMMENT '店铺发货城市',
  `shop_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '店铺名称',
  `shop_star` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '店铺评分',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '普通商品供货商' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for ze_zone_category
-- ----------------------------
DROP TABLE IF EXISTS `ze_zone_category`;
CREATE TABLE `ze_zone_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态{\n    \"0\": \"关闭\",\n    \"1\": \"开启\"\n}',
  `type` int(11) NOT NULL DEFAULT 10 COMMENT '大类信息{\n    \"20\": \"预约\",\n    \"10\": \"租赁\",\n    \"30\": \"服务\"\n}',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '园区分类管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_zone_category
-- ----------------------------

-- ----------------------------
-- Table structure for ze_zone_floor
-- ----------------------------
DROP TABLE IF EXISTS `ze_zone_floor`;
CREATE TABLE `ze_zone_floor`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `image` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片',
  `level` int(11) NULL DEFAULT NULL COMMENT '类型{\n    \"20\": \"楼层\",\n    \"10\": \"分区\"\n}',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '楼层名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '园区楼层' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_zone_floor
-- ----------------------------

-- ----------------------------
-- Table structure for ze_zone_room
-- ----------------------------
DROP TABLE IF EXISTS `ze_zone_room`;
CREATE TABLE `ze_zone_room`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '面积',
  `disinfection` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '禁烟',
  `floor_id` int(11) NULL DEFAULT NULL COMMENT '楼层id',
  `floor_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '楼层',
  `orientation` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '朝向',
  `remark` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '备注',
  `room_no` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '房间号',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态{\n    \"20\": \"空闲\",\n    \"10\": \"禁用\",\n    \"30\": \"占用\"\n}',
  `window` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '窗户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '园区楼层房间' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ze_zone_room
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
