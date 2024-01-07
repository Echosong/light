/*
 Navicat Premium Data Transfer

 Source Server         : localhost1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:6446
 Source Schema         : db_light_demo

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/01/2024 22:55:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '内容',
  `project_id` int(11) NULL DEFAULT NULL COMMENT '项目id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型{\n    \"0\": \"新闻\",\n    \"1\": \"公告\",\n    \"3\": \"文章\",\n    \"4\": \"通知\"\n}',
  `img_url` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `file_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '附件',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '新闻' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_article
-- ----------------------------
INSERT INTO `sys_article` VALUES (1, '2023-12-28 09:57:32.401000', 1, '2024-01-01 22:55:21.769000', 1, '<p>ddasdsadsadffff222</p>', 2, '323232', 3, 'http://localhost:2024/u/2024/01/01/d0b7bfe2-b509-4f52-bd10-994c85e4a411.png', 'http://localhost:8090/u/2023/12/29/11f14ca0-4d0c-43e9-b0b8-fa3a47935587.pdf', NULL);
INSERT INTO `sys_article` VALUES (2, '2023-12-29 23:36:56.604000', 1, '2023-12-29 23:36:56.604000', 1, '<p>这里发布第一个公告</p>', 232, '测试公告', 1, 'http://localhost:8090/u/2023/12/29/aeb32857-0c61-4c41-8b84-9cb947959857.png', 'http://localhost:8090/u/2023/12/29/5085e936-5a27-465a-a02f-def8e92a9e8b.doc', NULL);
INSERT INTO `sys_article` VALUES (3, '2024-01-02 20:42:58.524000', 1, '2024-01-02 20:42:58.524000', 1, '<p>2121212</p>', 0, '121', 4, '', '', NULL);

-- ----------------------------
-- Table structure for sys_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_category`;
CREATE TABLE `sys_category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '上级id',
  `use_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_category
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述(如果选择框用,隔开)',
  `group` int(11) NULL DEFAULT NULL COMMENT '配置分组',
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置字段名',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置说明',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型(配置html展现类型){\n    \"0\": \"文本框\",\n    \"1\": \"下拉选择框\",\n    \"2\": \"switch\",\n    \"3\": \"复选框\",\n    \"4\": \"上传\",\n    \"5\": \"文本域\",\n    \"6\": \"上传文件\",\n    \"7\": \"富文本\"\n}',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '配置值',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '2021-10-30 17:41:31', 38, '2023-01-01 16:23:18', 1, '系统名称', 0, 'sysName', '系统名称', 0, '轻巧之光项目管理', NULL);
INSERT INTO `sys_config` VALUES (2, '2021-06-30 23:52:39', 26, '2022-09-07 10:36:51', 1, '版本号', 0, '1001', '版本号', 0, 'v1.0', NULL);
INSERT INTO `sys_config` VALUES (3, NULL, NULL, '2023-12-30 18:50:13', 1, '开启,关闭', 0, 'state', '状态', 2, '1', NULL);
INSERT INTO `sys_config` VALUES (4, NULL, NULL, '2022-09-29 14:08:29', 1, '协议', 0, 'contace', '使用协议', 5, '协议内容', NULL);
INSERT INTO `sys_config` VALUES (5, NULL, NULL, '2022-12-15 22:59:04', 1, '红,绿色,蓝色', 0, 'them', '风格', 1, '绿色', NULL);
INSERT INTO `sys_config` VALUES (6, NULL, NULL, '2023-12-30 18:50:13', 1, '只能上传jpg/png文件，且不超过500kb', 0, 'logo', '上传logo', 4, 'http://localhost:8090/u/2023/12/30/86b426ab-49e2-48ec-a8da-a7fd610b5c49.png', NULL);
INSERT INTO `sys_config` VALUES (7, NULL, NULL, '2021-12-24 11:05:11', 1, '软件,硬件,实施', 0, 'selectTypes', '服务类型', 3, 'bbbbbb', NULL);

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
  `css_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `default_flg` int(11) NULL DEFAULT NULL,
  `dict_code` bigint(20) NULL DEFAULT NULL,
  `dict_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_sort` bigint(20) NULL DEFAULT NULL,
  `dict_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `list_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `dict_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dict_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `extend` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件扩展名',
  `file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件存储路径',
  `file_size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `file_type` int(11) NULL DEFAULT NULL COMMENT '文件类型{\n    \"1\": \"音频\",\n    \"2\": \"视频\"\n}',
  `ico` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `url_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相对路径',
  `uuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件唯一标识',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (1, '2023-12-28 10:38:06.198000', 5, '2023-12-28 10:38:06.198000', 5, 'png', 'u563', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\28cab9f1bd-4c35-448a-a6ae-d3b012698b94.png', 3889, 1, NULL, '2023/12/28/cab9f1bd-4c35-448a-a6ae-d3b012698b94.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (2, '2023-12-28 10:40:04.147000', 5, '2023-12-28 10:40:04.147000', 5, 'png', 'u563', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\284c4af2a7-db4d-44e2-a635-5c65e83a081c.png', 3889, 1, NULL, '2023/12/28/4c4af2a7-db4d-44e2-a635-5c65e83a081c.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (3, '2023-12-28 10:40:58.711000', 5, '2023-12-28 10:40:58.711000', 5, 'png', 'u563', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\281454dc56-b03f-40fa-9b2d-6ac3fc0acc20.png', 3889, 1, NULL, '2023/12/28/1454dc56-b03f-40fa-9b2d-6ac3fc0acc20.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (4, '2023-12-28 10:41:18.726000', 5, '2023-12-28 10:41:18.726000', 5, 'png', 'u562', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\28855e0153-311b-4881-bf78-10c45f96108b.png', 27810, 1, NULL, '2023/12/28/855e0153-311b-4881-bf78-10c45f96108b.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (5, '2023-12-28 10:41:42.112000', 5, '2023-12-29 15:07:26.508000', 1, 'png', '', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\28a4cb2b45-13cf-49ef-b9b2-001828370a10.png', 3889, 1, NULL, '2023/12/28/a4cb2b45-13cf-49ef-b9b2-001828370a10.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (6, '2023-12-29 15:00:42.141000', 5, '2023-12-29 15:00:42.141000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29755ce6d0-a7e4-4ab9-b9ac-1802aaa84ed5.jpg', 42669, 1, NULL, '2023/12/29/755ce6d0-a7e4-4ab9-b9ac-1802aaa84ed5.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (7, '2023-12-29 15:01:32.502000', 5, '2023-12-29 15:01:32.502000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29bed3bb46-1c09-49b3-ba75-29d5acefc31a.jpg', 42669, 1, NULL, '2023/12/29/bed3bb46-1c09-49b3-ba75-29d5acefc31a.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (8, '2023-12-29 15:02:23.325000', 5, '2023-12-29 15:08:18.616000', 1, 'jpg', 'http://localhost:8090/u/2023/12/29/2b3deb68-ca93-4569-af50-f7baaf472117.jpg', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29a46b7147-5968-4d04-82ea-a2f346ba0095.jpg', 70418, 1, NULL, '2023/12/29/a46b7147-5968-4d04-82ea-a2f346ba0095.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (9, '2023-12-29 15:07:50.082000', 5, '2023-12-29 15:07:50.082000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\290bc58134-9aa5-4345-8937-32203c458ba3.jpg', 42669, 1, NULL, '2023/12/29/0bc58134-9aa5-4345-8937-32203c458ba3.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (10, '2023-12-29 15:07:53.870000', 5, '2023-12-29 15:15:23.853000', 1, 'jpg', 'http://localhost:8090/u/2023/12/29/f86c85b7-8514-4dd0-a4b7-a3d00a6e8d5d.jpg', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\292b3deb68-ca93-4569-af50-f7baaf472117.jpg', 46291, 1, 'http://localhost:8090/u/2023/12/29/b2db71a4-ee4b-43ee-a0e8-2d92db856518.jpg', '2023/12/29/2b3deb68-ca93-4569-af50-f7baaf472117.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (11, '2023-12-29 15:15:15.886000', 5, '2023-12-29 15:15:15.886000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29b2db71a4-ee4b-43ee-a0e8-2d92db856518.jpg', 42669, 1, NULL, '2023/12/29/b2db71a4-ee4b-43ee-a0e8-2d92db856518.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (12, '2023-12-29 15:15:21.544000', 5, '2023-12-29 15:19:16.884000', 1, 'jpg', 'http://localhost:8090/u/2023/12/29/955062fa-f37f-4181-b5ac-14a1ef31cd97.jpg', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29f86c85b7-8514-4dd0-a4b7-a3d00a6e8d5d.jpg', 62933, 1, 'http://localhost:8090/u/2023/12/29/3769cb09-2af2-4a53-8e68-25a409a3d849.jpg', '2023/12/29/f86c85b7-8514-4dd0-a4b7-a3d00a6e8d5d.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (14, '2023-12-29 15:19:15.228000', 5, '2023-12-29 15:20:17.645000', 1, 'jpg', '油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29955062fa-f37f-4181-b5ac-14a1ef31cd97.jpg', 68745, 1, 'http://localhost:8090/u/2023/12/29/ef124f25-2014-473b-829d-10094b3ef418.jpg', '2023/12/29/955062fa-f37f-4181-b5ac-14a1ef31cd97.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (15, '2023-12-29 15:19:56.544000', 5, '2023-12-29 15:21:01.049000', 1, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29ef124f25-2014-473b-829d-10094b3ef418.jpg', 42669, 1, 'http://localhost:8090/u/2023/12/29/4da574e3-a16c-4248-903d-d7e23b7fc374.jpg', '2023/12/29/ef124f25-2014-473b-829d-10094b3ef418.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (16, '2023-12-29 15:20:28.736000', 5, '2023-12-29 19:51:17.732000', 1, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\294da574e3-a16c-4248-903d-d7e23b7fc374.jpg', 42669, 1, NULL, '2023/12/29/4da574e3-a16c-4248-903d-d7e23b7fc374.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (17, '2023-12-29 19:58:56.012000', 5, '2023-12-29 19:58:56.012000', 5, 'jpg', '油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\299b95f086-5514-4004-a1ff-5e5c024b8da5.jpg', 68745, 1, NULL, '2023/12/29/9b95f086-5514-4004-a1ff-5e5c024b8da5.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (18, '2023-12-29 21:07:39.051000', 5, '2023-12-29 21:07:39.051000', 5, 'jpg', '油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29f0ce55f7-7ae0-46b2-9009-2e0c57515615.jpg', 68745, 1, NULL, '2023/12/29/f0ce55f7-7ae0-46b2-9009-2e0c57515615.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (19, '2023-12-29 21:07:43.213000', 5, '2023-12-29 21:07:43.213000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\2927c58784-9abb-4619-86b8-a384288fdec4.jpg', 42669, 1, NULL, '2023/12/29/27c58784-9abb-4619-86b8-a384288fdec4.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (20, '2023-12-29 22:33:14.332000', 5, '2023-12-29 22:33:14.332000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29ad28b25a-d967-46ad-9200-b2b3a31dec46.jpg', 42669, 1, NULL, '2023/12/29/ad28b25a-d967-46ad-9200-b2b3a31dec46.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (21, '2023-12-29 22:34:08.601000', 5, '2023-12-29 22:34:08.601000', 5, 'jpg', '树脂+油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29ce533d5c-fcfb-43cc-ad7b-99afba9c8196.jpg', 42669, 1, NULL, '2023/12/29/ce533d5c-fcfb-43cc-ad7b-99afba9c8196.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (22, '2023-12-29 23:13:29.880000', 5, '2023-12-29 23:13:29.880000', 5, 'jpg', '油漆生产流程示意图', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\2966e86df8-6b80-4351-9761-a8a8dac299b9.jpg', 68745, 1, NULL, '2023/12/29/66e86df8-6b80-4351-9761-a8a8dac299b9.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (23, '2023-12-29 23:13:45.956000', 5, '2023-12-29 23:13:45.956000', 5, 'pdf', '宋飞刚-Java', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29b01b180a-e284-49da-a338-e4710e36f7e3.pdf', 273919, 1, NULL, '2023/12/29/b01b180a-e284-49da-a338-e4710e36f7e3.pdf', NULL, NULL);
INSERT INTO `sys_file` VALUES (24, '2023-12-29 23:14:16.597000', 5, '2023-12-29 23:14:16.597000', 5, 'pdf', '宋飞刚-Java', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29815bf9dc-3889-414e-ad11-2309a2ddec13.pdf', 273919, 1, NULL, '2023/12/29/815bf9dc-3889-414e-ad11-2309a2ddec13.pdf', NULL, NULL);
INSERT INTO `sys_file` VALUES (25, '2023-12-29 23:16:08.065000', 5, '2023-12-29 23:16:08.065000', 5, 'pdf', '宋飞刚-Java', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\2911f14ca0-4d0c-43e9-b0b8-fa3a47935587.pdf', 273919, 1, NULL, '2023/12/29/11f14ca0-4d0c-43e9-b0b8-fa3a47935587.pdf', NULL, NULL);
INSERT INTO `sys_file` VALUES (26, '2023-12-29 23:36:35.882000', 5, '2023-12-29 23:36:35.882000', 5, 'png', 'logo2', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\29aeb32857-0c61-4c41-8b84-9cb947959857.png', 199591, 1, NULL, '2023/12/29/aeb32857-0c61-4c41-8b84-9cb947959857.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (27, '2023-12-29 23:36:52.757000', 5, '2023-12-29 23:36:52.757000', 5, 'doc', '陈伟钢java-简历(1)', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\295085e936-5a27-465a-a02f-def8e92a9e8b.doc', 89602, 1, NULL, '2023/12/29/5085e936-5a27-465a-a02f-def8e92a9e8b.doc', NULL, NULL);
INSERT INTO `sys_file` VALUES (28, '2023-12-30 18:47:28.833000', 5, '2023-12-30 18:47:28.833000', 5, 'jpg', '银行卡', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\30cacfad32-3e13-4b95-9229-6f10b784bafa.jpg', 788779, 1, NULL, '2023/12/30/cacfad32-3e13-4b95-9229-6f10b784bafa.jpg', NULL, NULL);
INSERT INTO `sys_file` VALUES (29, '2023-12-30 18:47:44.928000', 5, '2023-12-30 18:47:44.928000', 5, 'png', '签名', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\30f86aaa22-f44b-41fa-9f85-678504d073e5.png', 44963, 1, NULL, '2023/12/30/f86aaa22-f44b-41fa-9f85-678504d073e5.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (30, '2023-12-30 18:50:06.937000', 5, '2023-12-30 18:50:06.937000', 5, 'png', 'f83fc6b0e34b08b3ce80db73de9a7ba9', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2023\\12\\3086b426ab-49e2-48ec-a8da-a7fd610b5c49.png', 235415, 1, NULL, '2023/12/30/86b426ab-49e2-48ec-a8da-a7fd610b5c49.png', NULL, NULL);
INSERT INTO `sys_file` VALUES (31, '2024-01-01 22:55:19.549000', 5, '2024-01-01 22:55:19.549000', 5, 'png', '签名', 'E:\\外包项目整理\\light轻巧之光\\light\\upload\\2024\\01\\01d0b7bfe2-b509-4f52-bd10-994c85e4a411.png', 44963, 1, NULL, '2024/01/01/d0b7bfe2-b509-4f52-bd10-994c85e4a411.png', NULL, NULL);

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
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `browser` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `exception_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '异常详情',
  `log_type` int(11) NULL DEFAULT NULL COMMENT '日志类型{\n    \"20\": \"测试\",\n    \"10\": \"登录\",\n    \"50\": \"其他\"\n}',
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '参数',
  `request_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求ip',
  `time` bigint(20) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES (1, '2024-01-01 10:02:50.102000', 5, '2024-01-01 10:02:50.102000', 5, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '用户登录', '', 10, 'com.kdao.light.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (2, '2024-01-01 22:51:00.513000', 5, '2024-01-01 22:51:00.513000', 5, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '用户登录', '', 10, 'com.kdao.light.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (3, '2024-01-01 22:51:19.567000', 5, '2024-01-01 22:51:19.567000', 5, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '用户登录', '', 10, 'com.kdao.light.controller.UserController.login()', '{\"username\":\"admin\",\"password\":\"MTIzNDU2\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (4, '2024-01-01 22:55:21.724000', 1, '2024-01-01 22:55:21.724000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"323232\",\"imgUrl\":\"http://localhost:2024/u/2024/01/01/d0b7bfe2-b509-4f52-bd10-994c85e4a411.png\",\"type\":3,\"content\":\"<p>ddasdsadsadffff222</p>\",\"fileUrl\":\"http://localhost:8090/u/2023/12/29/11f14ca0-4d0c-43e9-b0b8-fa3a47935587.pdf\",\"projectId\":2,\"id\":1,\"createTime\":1703728652000,\"updateTime\":1703864152000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (5, '2024-01-02 13:25:16.079000', 1, '2024-01-02 13:25:16.079000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":0,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1703927287000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (6, '2024-01-02 13:25:16.770000', 1, '2024-01-02 13:25:16.770000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":1,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1703927287000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (7, '2024-01-02 13:29:56.448000', 1, '2024-01-02 13:29:56.448000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改产品信息', '', 50, 'cn.light.admin.controller.ProductController.save()', '{\"name\":\"3434\",\"price\":34}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (8, '2024-01-02 16:46:22.693000', 1, '2024-01-02 16:46:22.693000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改产品信息', '', 50, 'cn.light.admin.controller.ProductController.save()', '{\"name\":\"3434\",\"price\":34,\"id\":1,\"createTime\":1704173396000,\"updateTime\":1704173396000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (9, '2024-01-02 16:46:41.733000', 1, '2024-01-02 16:46:41.733000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改产品信息', '', 50, 'cn.light.admin.controller.ProductController.save()', '{\"name\":\"3434\",\"price\":34,\"id\":1,\"createTime\":1704173396000,\"updateTime\":1704185182000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (10, '2024-01-02 16:52:57.587000', 1, '2024-01-02 16:52:57.587000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改产品信息', '', 50, 'cn.light.admin.controller.ProductController.save()', '{\"name\":\"3434\",\"price\":34,\"info\":\"<p>edsfsdfdsfsdfds</p>\",\"id\":1,\"createTime\":1704173396000,\"updateTime\":1704185201000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (11, '2024-01-02 20:42:58.480000', 1, '2024-01-02 20:42:58.480000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '新增|修改新闻', '', 50, 'cn.light.admin.controller.ArticleController.save()', '{\"title\":\"121\",\"imgUrl\":\"\",\"type\":4,\"content\":\"<p>2121212</p>\",\"fileUrl\":\"\",\"projectId\":0}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (12, '2024-01-02 21:19:44.303000', 1, '2024-01-02 21:19:44.303000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":0,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1704173116000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 2, 'admin', NULL);
INSERT INTO `sys_log` VALUES (13, '2024-01-02 21:19:44.919000', 1, '2024-01-02 21:19:44.919000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":1,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1704173116000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (14, '2024-01-02 21:20:42.343000', 1, '2024-01-02 21:20:42.343000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":0,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1704173116000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (15, '2024-01-02 21:20:45.839000', 1, '2024-01-02 21:20:45.839000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":1,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1704173116000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (16, '2024-01-02 21:20:48.686000', 1, '2024-01-02 21:20:48.686000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18817710255\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"3232\",\"type\":0,\"name\":\"23\",\"state\":0,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":5,\"createTime\":1703679565000,\"updateTime\":1704173116000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (17, '2024-01-02 21:20:52.422000', 1, '2024-01-02 21:20:52.422000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18832263600\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"23323\",\"type\":1,\"name\":\"23\",\"state\":0,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":4,\"createTime\":1703679375000,\"updateTime\":1703927286000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (18, '2024-01-02 21:25:27.126000', 1, '2024-01-02 21:25:27.126000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '更新用户', '', 50, 'cn.light.admin.controller.UserController.update()', '{\"username\":\"18832263600\",\"sex\":0,\"height\":0,\"weight\":0,\"nick\":\"23323\",\"type\":1,\"name\":\"23\",\"state\":1,\"email\":\"2323236@qq.com\",\"regIp\":\"0:0:0:0:0:0:0:1\",\"loginIp\":\"23\",\"id\":4,\"createTime\":1703679375000,\"updateTime\":1704201652000,\"creatorId\":1,\"updaterId\":1}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (19, '2024-01-03 08:34:06.978000', 1, '2024-01-03 08:34:06.978000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.test()', '', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (20, '2024-01-03 08:35:15.410000', 1, '2024-01-03 08:35:15.410000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.test()', '', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (21, '2024-01-03 08:46:53.829000', 1, '2024-01-03 08:46:53.829000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.test()', '', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (22, '2024-01-03 08:48:56.785000', 1, '2024-01-03 08:48:56.785000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.test()', '', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (23, '2024-01-03 08:55:26.292000', 1, '2024-01-03 08:55:26.292000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (24, '2024-01-03 09:00:35.864000', 1, '2024-01-03 09:00:35.864000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (25, '2024-01-03 09:01:24.119000', 1, '2024-01-03 09:01:24.119000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (26, '2024-01-03 09:02:35.919000', 1, '2024-01-03 09:02:35.919000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (27, '2024-01-03 09:10:48.526000', 1, '2024-01-03 09:10:48.526000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (28, '2024-01-03 09:15:05.944000', 1, '2024-01-03 09:15:05.944000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (29, '2024-01-03 09:19:35.238000', 1, '2024-01-03 09:19:35.238000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (30, '2024-01-03 09:19:47.977000', 1, '2024-01-03 09:19:47.977000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (31, '2024-01-03 09:20:36.733000', 1, '2024-01-03 09:20:36.733000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (32, '2024-01-03 09:21:17.839000', 1, '2024-01-03 09:21:17.839000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (33, '2024-01-03 09:22:44.438000', 1, '2024-01-03 09:22:44.438000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (34, '2024-01-03 09:23:42.630000', 1, '2024-01-03 09:23:42.630000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":4,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (35, '2024-01-03 09:33:18.084000', 1, '2024-01-03 09:33:18.084000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (36, '2024-01-03 09:35:12.957000', 1, '2024-01-03 09:35:12.957000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (37, '2024-01-03 09:35:25.276000', 1, '2024-01-03 09:35:25.276000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (38, '2024-01-03 09:35:46.926000', 1, '2024-01-03 09:35:46.926000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (39, '2024-01-03 09:36:52.355000', 1, '2024-01-03 09:36:52.355000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (40, '2024-01-03 09:42:32.580000', 1, '2024-01-03 09:42:32.580000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (41, '2024-01-03 09:43:44.955000', 1, '2024-01-03 09:43:44.955000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (42, '2024-01-03 09:44:22.116000', 1, '2024-01-03 09:44:22.116000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (43, '2024-01-03 09:45:35.373000', 1, '2024-01-03 09:45:35.373000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (44, '2024-01-03 09:45:38.138000', 1, '2024-01-03 09:45:38.138000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (45, '2024-01-03 09:45:39.186000', 1, '2024-01-03 09:45:39.186000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (46, '2024-01-03 09:45:59.659000', 1, '2024-01-03 09:45:59.659000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (47, '2024-01-03 09:46:50.683000', 1, '2024-01-03 09:46:50.683000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (48, '2024-01-03 09:46:52.825000', 1, '2024-01-03 09:46:52.825000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (49, '2024-01-03 09:47:21.678000', 1, '2024-01-03 09:47:21.678000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (50, '2024-01-03 09:47:33.691000', 1, '2024-01-03 09:47:33.691000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (51, '2024-01-03 09:47:52.269000', 1, '2024-01-03 09:47:52.269000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (52, '2024-01-03 09:48:21.097000', 1, '2024-01-03 09:48:21.097000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":4,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (53, '2024-01-03 09:48:58.525000', 1, '2024-01-03 09:48:58.525000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (54, '2024-01-03 09:50:19.811000', 1, '2024-01-03 09:50:19.811000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (55, '2024-01-03 09:50:45.082000', 1, '2024-01-03 09:50:45.082000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (56, '2024-01-03 09:51:01.945000', 1, '2024-01-03 09:51:01.945000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (57, '2024-01-03 09:51:28.385000', 1, '2024-01-03 09:51:28.385000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (58, '2024-01-03 09:52:23.232000', 1, '2024-01-03 09:52:23.232000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (59, '2024-01-03 09:53:56.244000', 1, '2024-01-03 09:53:56.244000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (60, '2024-01-03 09:54:13.129000', 1, '2024-01-03 09:54:13.129000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (61, '2024-01-03 09:54:41.041000', 1, '2024-01-03 09:54:41.041000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (62, '2024-01-03 09:55:11.137000', 1, '2024-01-03 09:55:11.137000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":0,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (63, '2024-01-03 09:58:28.750000', 1, '2024-01-03 09:58:28.750000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":4,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (64, '2024-01-03 09:59:01.419000', 1, '2024-01-03 09:59:01.419000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出新闻', '', 50, 'cn.light.admin.controller.ArticleController.export()', '{\"title\":\"\",\"type\":4,\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (65, '2024-01-03 10:09:07.943000', 1, '2024-01-03 10:09:07.943000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"username\":\"\",\"description\":\"\",\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 0, 'admin', NULL);
INSERT INTO `sys_log` VALUES (66, '2024-01-03 10:12:00.696000', 1, '2024-01-03 10:12:00.696000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"username\":\"\",\"description\":\"\",\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);
INSERT INTO `sys_log` VALUES (67, '2024-01-03 10:35:46.131000', 1, '2024-01-03 10:35:46.131000', 1, NULL, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36 Edg/120.0.0.0', '导出日志', '', 50, 'cn.light.admin.controller.LogController.export()', '{\"username\":\"\",\"description\":\"\",\"page\":1,\"pageSize\":10,\"sort\":true,\"direction\":false,\"sortCol\":\"id\"}', '0:0:0:0:0:0:0:1', 1, 'admin', NULL);

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `good_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_order
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
  `sort` int(11) NULL DEFAULT NULL COMMENT '排序',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型(权限类型 0：目录   1：菜单   2：按钮)',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `show` int(11) NULL DEFAULT NULL COMMENT '是否展现 1 展现 0 不展现',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, '系统管理', 'Setting', '系统管理', '', 0, NULL, 12, 0, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (2, '角色管理', NULL, '项目管理', 'role-list', 1, NULL, 1, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (3, '系统配置', NULL, '系统配置', 'sys-config', 1, NULL, 3, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (4, '用户列表', NULL, '用户列表', 'user-list', 1, NULL, 2, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (6, '系统日志', NULL, '系统日志', 'log-list', 1, NULL, 5, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (8, '内容管理', 'Files', '项目管理', NULL, 0, NULL, 1, 0, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (9, '文章列表', NULL, '文章列表', 'article-list', 8, NULL, 2, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (10, '文件管理', NULL, '文件管理', 'file-list', 1, NULL, 3, 1, NULL, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_permission` VALUES (11, '权限管理', NULL, '权限管理', 'permission-list', 1, NULL, 2, 1, NULL, '2023-12-29 10:40:29', NULL, 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, '系统设置', '', '系统设置', 'set-set', 1, '', 10, 1, '2023-12-28 22:08:30', '2023-12-28 22:08:30', 1, 1, 1, NULL);
INSERT INTO `sys_permission` VALUES (13, '删除用户', NULL, '用户列表', 'user-delete', 4, NULL, 2, 1, '2023-12-30 17:42:32', '2023-12-30 17:42:32', 1, 1, NULL, NULL);
INSERT INTO `sys_permission` VALUES (14, '产品管理', '', '项目管理', 'product-list', 8, 'prodouct-list', 1, 1, '2024-01-02 13:27:02', '2024-01-02 13:27:15', 1, 1, NULL, NULL);

-- ----------------------------
-- Table structure for sys_product
-- ----------------------------
DROP TABLE IF EXISTS `sys_product`;
CREATE TABLE `sys_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品名称',
  `price` decimal(38, 2) NULL DEFAULT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品描述',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_kdproduct_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_product
-- ----------------------------
INSERT INTO `sys_product` VALUES (1, '2024-01-02 13:29:56.456000', 1, '2024-01-02 16:52:57.638000', 1, '3434', 34.00, '<p>edsfsdfdsfsdfds</p>', NULL);

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
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户名称',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '所属客户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '2022-09-06 22:33:23.000000', NULL, '2023-12-29 10:23:09.655000', 1, '公司用户信息可以全部权限', '超级管理员', NULL);
INSERT INTO `sys_role` VALUES (2, '2022-09-06 22:47:44.716000', 1, '2023-12-29 10:23:24.707000', 1, '描述', '财务部', NULL);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (10, '2023-12-29 10:22:31.997000', 1, '2023-12-29 10:22:31.997000', 1, 8, 2, NULL);
INSERT INTO `sys_role_permission` VALUES (11, '2023-12-29 10:22:32.046000', 1, '2023-12-29 10:22:32.046000', 1, 9, 2, NULL);
INSERT INTO `sys_role_permission` VALUES (22, '2024-01-02 13:27:22.883000', 1, '2024-01-02 13:27:22.883000', 1, 8, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (23, '2024-01-02 13:27:22.885000', 1, '2024-01-02 13:27:22.885000', 1, 14, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (24, '2024-01-02 13:27:22.887000', 1, '2024-01-02 13:27:22.887000', 1, 9, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (25, '2024-01-02 13:27:22.888000', 1, '2024-01-02 13:27:22.888000', 1, 1, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (26, '2024-01-02 13:27:22.889000', 1, '2024-01-02 13:27:22.889000', 1, 2, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (27, '2024-01-02 13:27:22.890000', 1, '2024-01-02 13:27:22.890000', 1, 4, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (28, '2024-01-02 13:27:22.891000', 1, '2024-01-02 13:27:22.891000', 1, 13, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (29, '2024-01-02 13:27:22.892000', 1, '2024-01-02 13:27:22.892000', 1, 11, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (30, '2024-01-02 13:27:22.894000', 1, '2024-01-02 13:27:22.894000', 1, 3, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (31, '2024-01-02 13:27:22.895000', 1, '2024-01-02 13:27:22.895000', 1, 10, 1, NULL);
INSERT INTO `sys_role_permission` VALUES (32, '2024-01-02 13:27:22.896000', 1, '2024-01-02 13:27:22.896000', 1, 6, 1, NULL);

-- ----------------------------
-- Table structure for sys_stock_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_stock_log`;
CREATE TABLE `sys_stock_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `creator_id` int(11) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `updater_id` int(11) NULL DEFAULT NULL,
  `version` int(11) NULL DEFAULT NULL,
  `business_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_stock_log
-- ----------------------------

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
  `height` double NULL DEFAULT NULL,
  `login_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录ip',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `nick` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `reg_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册ip',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别{\n    \"0\": \"男\",\n    \"1\": \"女\"\n}',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态(0,1){\n    \"0\": \"未启用\",\n    \"1\": \"正常\",\n    \"2\": \"禁用\",\n    \"3\": \"删除\"\n}',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `weight` double NULL DEFAULT NULL,
  `front_card` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证正面',
  `reverse_card` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证反面',
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个人简介',
  `type` int(11) NULL DEFAULT NULL COMMENT '注册方式(0,1,2){\n    \"0\": \"线下注册\",\n    \"1\": \"微信\",\n    \"2\": \"支付宝\"\n}',
  `version` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '2022-09-06 21:32:29.000000', 1, '2023-12-31 10:26:18.818000', 1, '31369063@qq.com', 182, '0:0:0:0:0:0:0:1', '宋飞', NULL, '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '192.168.10.105', 1, 1, 'admin', 2, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '2023-12-27 20:14:11.022000', 1, '2023-12-30 11:30:28.209000', 1, '1213183178@qq.com', 0, '12', '121', '212', 'bdca9d8517ec69073382ede5d6ab19849ea1131d9b5d434a69037d008d36bd91', '0:0:0:0:0:0:0:1', 0, 1, '18817712500', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '2023-12-27 20:14:25.875000', 1, '2023-12-27 20:30:08.900000', 1, '1213183178@qq.com', 0, '12', '121', '212', 'bdca9d8517ec69073382ede5d6ab19849ea1131d9b5d434a69037d008d36bd91', '0:0:0:0:0:0:0:1', 0, 3, '18817712501', 0, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '2023-12-27 20:16:15.925000', 1, '2024-01-02 21:25:27.133000', 1, '2323236@qq.com', 0, '23', '23', '23323', '914265234e56ab38169387e973eb95a06471cad9ada7110e5844da0f324430e9', '0:0:0:0:0:0:0:1', 0, 1, '18832263600', 0, NULL, NULL, NULL, 1, NULL);
INSERT INTO `sys_user` VALUES (5, '2023-12-27 20:19:25.187000', 1, '2024-01-02 21:20:48.693000', 1, '2323236@qq.com', 0, '23', '23', '3232', '4764fcb42487be33b9acae25816a12615b0095a35df9e083ecba6f9d7fb958c6', '0:0:0:0:0:0:0:1', 0, 0, '18817710255', 0, NULL, NULL, NULL, 0, NULL);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, '2022-09-06 22:33:47.000000', 1, '2022-09-06 22:33:52.000000', 1, 1, 1, NULL);
INSERT INTO `sys_user_role` VALUES (2, '2022-09-29 11:44:46.977000', 1, '2022-09-29 11:44:46.977000', 1, 2, 2, NULL);
INSERT INTO `sys_user_role` VALUES (3, '2022-09-29 11:52:35.164000', 1, '2022-09-29 11:52:35.164000', 1, 2, 3, NULL);
INSERT INTO `sys_user_role` VALUES (4, '2023-12-27 20:14:11.054000', 1, '2023-12-27 20:14:11.054000', 1, 1, 2, NULL);
INSERT INTO `sys_user_role` VALUES (5, '2023-12-27 20:14:25.882000', 1, '2023-12-27 20:14:25.882000', 1, 1, 3, NULL);
INSERT INTO `sys_user_role` VALUES (6, '2023-12-27 20:16:15.930000', 1, '2023-12-27 20:16:15.930000', 1, 1, 4, NULL);
INSERT INTO `sys_user_role` VALUES (7, '2023-12-27 20:19:25.193000', 1, '2023-12-27 20:19:25.193000', 1, 1, 5, NULL);

SET FOREIGN_KEY_CHECKS = 1;
