/*
Navicat MySQL Data Transfer

Source Server         : localhost1
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : db_light

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2023-01-01 16:18:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kd_article
-- ----------------------------
DROP TABLE IF EXISTS `kd_article`;
CREATE TABLE `kd_article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `project_id` int(11) DEFAULT NULL COMMENT '项目id',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `type` varchar(255) DEFAULT NULL COMMENT '类型{\n    "0": "新闻",\n    "1": "公告"\n}',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='新闻';

-- ----------------------------
-- Records of kd_article
-- ----------------------------

-- ----------------------------
-- Table structure for kd_config
-- ----------------------------
DROP TABLE IF EXISTS `kd_config`;
CREATE TABLE `kd_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL COMMENT '描述(如果选择框用,隔开)',
  `group` int(11) DEFAULT NULL COMMENT '配置分组',
  `key` varchar(255) DEFAULT NULL COMMENT '配置字段名',
  `name` varchar(255) DEFAULT NULL COMMENT '配置说明',
  `type` int(11) DEFAULT NULL COMMENT '类型(配置html展现类型)',
  `value` varchar(255) DEFAULT NULL COMMENT '配置值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='系统配置';

-- ----------------------------
-- Records of kd_config
-- ----------------------------
INSERT INTO `kd_config` VALUES ('1', '2021-10-30 17:41:31', '38', '2022-12-15 22:59:04', '1', '系统名称', '0', 'sysName', '系统名称', '0', '轻巧之光项目管理');
INSERT INTO `kd_config` VALUES ('2', '2021-06-30 23:52:39', '26', '2022-09-07 10:36:51', '1', '版本号', '0', '1001', '版本号', '0', 'v1.0');
INSERT INTO `kd_config` VALUES ('3', null, null, '2022-09-07 10:24:39', '1', '开启,关闭', '0', 'state', '状态', '2', 'true');
INSERT INTO `kd_config` VALUES ('4', null, null, '2022-09-29 14:08:29', '1', '协议', '0', 'contace', '使用协议', '5', '协议内容');
INSERT INTO `kd_config` VALUES ('5', null, null, '2022-12-15 22:59:04', '1', '红,绿色,蓝色', '0', 'them', '风格', '1', '绿色');
INSERT INTO `kd_config` VALUES ('6', null, null, '2022-09-29 14:08:29', '1', '只能上传jpg/png文件，且不超过500kb', '0', 'logo', '上传logo', '4', 'http://admin.kongdaozn.com/upload/2022/09/29/b4834260-4d6c-4f8a-8078-5ce497bd5364.png');
INSERT INTO `kd_config` VALUES ('7', null, null, '2021-12-24 11:05:11', '1', '软件,硬件,实施', '0', 'selectTypes', '服务类型', '3', 'bbbbbb');

-- ----------------------------
-- Table structure for kd_file
-- ----------------------------
DROP TABLE IF EXISTS `kd_file`;
CREATE TABLE `kd_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `extend` varchar(255) DEFAULT NULL COMMENT '文件扩展名',
  `file_name` varchar(2000) DEFAULT NULL COMMENT '文件名',
  `file_path` varchar(255) DEFAULT NULL COMMENT '文件存储路径',
  `file_size` bigint(20) DEFAULT NULL COMMENT '文件大小',
  `file_type` int(11) DEFAULT NULL COMMENT '文件类型{\n    "1": "音频",\n    "2": "视频"\n}',
  `ico` varchar(2000) DEFAULT NULL COMMENT '图标',
  `url_path` varchar(255) DEFAULT NULL COMMENT '相对路径',
  `uuid` varchar(255) DEFAULT NULL COMMENT '文件唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件管理';

-- ----------------------------
-- Records of kd_file
-- ----------------------------

-- ----------------------------
-- Table structure for kd_log
-- ----------------------------
DROP TABLE IF EXISTS `kd_log`;
CREATE TABLE `kd_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `browser` varchar(255) DEFAULT NULL COMMENT '浏览器',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `exception_detail` varchar(255) DEFAULT NULL COMMENT '异常详情',
  `log_type` varchar(255) DEFAULT NULL COMMENT '日志类型',
  `method` varchar(255) DEFAULT NULL COMMENT '方法名',
  `params` varchar(1000) DEFAULT NULL COMMENT '参数',
  `request_ip` varchar(255) DEFAULT NULL COMMENT '请求ip',
  `time` varchar(255) DEFAULT NULL COMMENT '请求耗时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='日志';

-- ----------------------------
-- Records of kd_log
-- ----------------------------
INSERT INTO `kd_log` VALUES ('1', '2023-01-01 16:12:42.378000', '1', '2023-01-01 16:12:42.378000', '1', null, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54', '退出登录', null, 'info', 'com.kdao.light.controller.UserController.logout()', '', '127.0.0.1', '1');
INSERT INTO `kd_log` VALUES ('2', '2023-01-01 16:13:01.407000', '5', '2023-01-01 16:13:01.407000', '5', null, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54', '用户登录', null, 'info', 'com.kdao.light.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"NjU0MzIx\"}', '127.0.0.1', '0');
INSERT INTO `kd_log` VALUES ('3', '2023-01-01 16:13:06.006000', '5', '2023-01-01 16:13:06.006000', '5', null, 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36 Edg/108.0.1462.54', '用户登录', null, 'info', 'com.kdao.light.controller.UserController.login()', '{\"username\":\"18800000000\",\"password\":\"MTIzNDU2\"}', '127.0.0.1', '0');

-- ----------------------------
-- Table structure for kd_permission
-- ----------------------------
DROP TABLE IF EXISTS `kd_permission`;
CREATE TABLE `kd_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `description` varchar(255) DEFAULT NULL COMMENT '权限描述',
  `perms` varchar(255) DEFAULT NULL COMMENT '权限标识',
  `parent_id` int(11) DEFAULT NULL COMMENT '父级id',
  `url` varchar(255) DEFAULT NULL COMMENT '访问路径',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `type` int(11) DEFAULT NULL COMMENT '类型(权限类型 0：目录   1：菜单   2：按钮)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COMMENT='权限';

-- ----------------------------
-- Records of kd_permission
-- ----------------------------
INSERT INTO `kd_permission` VALUES ('1', '系统管理', 'el-icon-user', '系统管理', '', '0', null, '12', '0', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('2', '角色管理', null, '项目管理', 'role-list', '1', null, '1', '1', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('3', '系统配置', null, '系统配置', 'sys-config', '1', null, '3', '1', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('4', '用户列表', null, '用户列表', 'user-list', '1', null, '2', '1', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('6', '系统日志', null, '系统日志', 'log-list', '1', null, '5', '1', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('8', '内容管理', 'el-icon-cpu', '项目管理', null, '0', null, '1', '0', null, null, null, null);
INSERT INTO `kd_permission` VALUES ('9', '文章列表', null, '文章列表', 'article-list', '8', null, '2', '1', null, null, null, null);

-- ----------------------------
-- Table structure for kd_role
-- ----------------------------
DROP TABLE IF EXISTS `kd_role`;
CREATE TABLE `kd_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='所属客户';

-- ----------------------------
-- Records of kd_role
-- ----------------------------
INSERT INTO `kd_role` VALUES ('1', '2022-09-06 22:33:23.000000', null, '2022-09-07 11:03:48.115000', '1', '公司用户信息可以全部权限', '空道公司');
INSERT INTO `kd_role` VALUES ('2', '2022-09-06 22:47:44.716000', '1', '2022-09-06 22:47:44.716000', '1', '描述', '山东项目');

-- ----------------------------
-- Table structure for kd_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `kd_role_permission`;
CREATE TABLE `kd_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of kd_role_permission
-- ----------------------------
INSERT INTO `kd_role_permission` VALUES ('1', null, null, null, null, '1', '1');
INSERT INTO `kd_role_permission` VALUES ('2', null, null, null, null, '2', '1');
INSERT INTO `kd_role_permission` VALUES ('3', null, null, null, null, '3', '1');
INSERT INTO `kd_role_permission` VALUES ('4', null, null, null, null, '4', '1');
INSERT INTO `kd_role_permission` VALUES ('5', null, null, null, null, '6', '1');
INSERT INTO `kd_role_permission` VALUES ('6', null, null, null, null, '8', '1');
INSERT INTO `kd_role_permission` VALUES ('7', null, null, null, null, '9', '1');
INSERT INTO `kd_role_permission` VALUES ('8', null, null, null, null, '21', '1');

-- ----------------------------
-- Table structure for kd_user
-- ----------------------------
DROP TABLE IF EXISTS `kd_user`;
CREATE TABLE `kd_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `height` varchar(255) DEFAULT NULL COMMENT '身高',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登录ip',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `nick` varchar(255) DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `reg_ip` varchar(255) DEFAULT NULL COMMENT '注册ip',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `state` int(11) DEFAULT NULL COMMENT '状态默认开启(0,1){\n    "0": "未启用",\n    "1": "正常",\n    "2": "禁用",\n    "3": "删除"\n}',
  `type` varchar(255) DEFAULT NULL COMMENT '注册方式(0,1,2){\n    "0": "线下注册",\n    "1": "微信",\n    "2": "支付宝"\n}',
  `username` varchar(255) DEFAULT NULL COMMENT '账号',
  `weight` varchar(255) DEFAULT NULL COMMENT '体重',
  `front_card` varchar(255) DEFAULT NULL COMMENT '身份证正面',
  `reverse_card` varchar(255) DEFAULT NULL COMMENT '身份证反面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of kd_user
-- ----------------------------
INSERT INTO `kd_user` VALUES ('1', '2022-09-06 21:32:29.000000', '1', '2023-01-01 12:34:37.001000', '5', '31369063@qq.com', '182', '127.0.0.1', '宋飞', null, '207cf410532f92a47dee245ce9b11ff71f578ebd763eb3bbea44ebd043d018fb', '192.168.10.105', '1', '1', '1', 'admin', '2', null, null);

-- ----------------------------
-- Table structure for kd_user_role
-- ----------------------------
DROP TABLE IF EXISTS `kd_user_role`;
CREATE TABLE `kd_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `creator_id` int(11) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `updater_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户角色';

-- ----------------------------
-- Records of kd_user_role
-- ----------------------------
INSERT INTO `kd_user_role` VALUES ('1', '2022-09-06 22:33:47.000000', '1', '2022-09-06 22:33:52.000000', '1', '1', '1');
INSERT INTO `kd_user_role` VALUES ('2', '2022-09-29 11:44:46.977000', '1', '2022-09-29 11:44:46.977000', '1', '2', '2');
INSERT INTO `kd_user_role` VALUES ('3', '2022-09-29 11:52:35.164000', '1', '2022-09-29 11:52:35.164000', '1', '2', '3');
