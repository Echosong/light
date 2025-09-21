-- Light框架数据库性能优化脚本
-- 执行前请备份数据库

-- ===========================================
-- 索引优化
-- ===========================================

-- 用户表索引优化
-- 用户名索引（唯一）
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_user_username ON sys_user(username);
-- 状态索引（查询活跃用户）
CREATE INDEX IF NOT EXISTS idx_sys_user_state ON sys_user(state);
-- 登录IP索引（安全分析）
CREATE INDEX IF NOT EXISTS idx_sys_user_login_ip ON sys_user(login_ip);
-- 创建时间索引（按时间查询）
CREATE INDEX IF NOT EXISTS idx_sys_user_create_time ON sys_user(create_time);
-- 邮箱索引
CREATE INDEX IF NOT EXISTS idx_sys_user_email ON sys_user(email);

-- 角色表索引优化
-- 角色代码索引（唯一）
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_role_code ON sys_role(code);
-- 角色名称索引
CREATE INDEX IF NOT EXISTS idx_sys_role_name ON sys_role(name);

-- 用户角色关联表索引优化
-- 用户ID索引
CREATE INDEX IF NOT EXISTS idx_sys_user_role_user_id ON sys_user_role(user_id);
-- 角色ID索引
CREATE INDEX IF NOT EXISTS idx_sys_user_role_role_id ON sys_user_role(role_id);
-- 联合索引（避免重复分配）
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_user_role_unique ON sys_user_role(user_id, role_id);

-- 权限表索引优化
-- 权限代码索引（唯一）
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_permission_perms ON sys_permission(perms);
-- 父级ID索引（构建树形结构）
CREATE INDEX IF NOT EXISTS idx_sys_permission_parent_id ON sys_permission(parent_id);
-- 类型索引
CREATE INDEX IF NOT EXISTS idx_sys_permission_type ON sys_permission(type);

-- 角色权限关联表索引优化
-- 角色ID索引
CREATE INDEX IF NOT EXISTS idx_sys_role_permission_role_id ON sys_role_permission(role_id);
-- 权限ID索引
CREATE INDEX IF NOT EXISTS idx_sys_role_permission_permission_id ON sys_role_permission(permission_id);

-- 日志表索引优化
-- 用户名索引（查询用户操作）
CREATE INDEX IF NOT EXISTS idx_sys_log_username ON sys_log(username);
-- 请求IP索引（安全分析）
CREATE INDEX IF NOT EXISTS idx_sys_log_request_ip ON sys_log(request_ip);
-- 创建时间索引（按时间范围查询）
CREATE INDEX IF NOT EXISTS idx_sys_log_create_time ON sys_log(create_time);
-- 操作类型索引
CREATE INDEX IF NOT EXISTS idx_sys_log_business ON sys_log(business);
-- 状态索引
CREATE INDEX IF NOT EXISTS idx_sys_log_state ON sys_log(state);
-- 联合索引（常用查询组合）
CREATE INDEX IF NOT EXISTS idx_sys_log_user_time ON sys_log(username, create_time);

-- 文件表索引优化
-- 文件路径索引
CREATE INDEX IF NOT EXISTS idx_sys_file_path ON sys_file(path);
-- 文件类型索引
CREATE INDEX IF NOT EXISTS idx_sys_file_type ON sys_file(type);
-- 创建时间索引
CREATE INDEX IF NOT EXISTS idx_sys_file_create_time ON sys_file(create_time);
-- 创建者索引
CREATE INDEX IF NOT EXISTS idx_sys_file_creator_id ON sys_file(creator_id);

-- 消息表索引优化
-- 接收者索引
CREATE INDEX IF NOT EXISTS idx_sys_message_to_user ON sys_message(to_user);
-- 发送者索引
CREATE INDEX IF NOT EXISTS idx_sys_message_from_user ON sys_message(from_user);
-- 创建时间索引
CREATE INDEX IF NOT EXISTS idx_sys_message_create_time ON sys_message(create_time);
-- 状态索引
CREATE INDEX IF NOT EXISTS idx_sys_message_state ON sys_message(state);

-- 配置表索引优化
-- 配置组索引
CREATE INDEX IF NOT EXISTS idx_sys_config_group_code ON sys_config(group_code);
-- 配置键索引
CREATE INDEX IF NOT EXISTS idx_sys_config_key ON sys_config(`key`);
-- 联合索引（组+键，确保唯一性）
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_config_group_key ON sys_config(group_code, `key`);

-- 字典类型表索引优化
-- 字典类型索引
CREATE UNIQUE INDEX IF NOT EXISTS idx_sys_dict_type_type ON sys_dict_type(type);

-- 字典数据表索引优化
-- 字典类型索引
CREATE INDEX IF NOT EXISTS idx_sys_dict_data_dict_type ON sys_dict_data(dict_type);
-- 字典值索引
CREATE INDEX IF NOT EXISTS idx_sys_dict_data_dict_value ON sys_dict_data(dict_value);
-- 排序索引
CREATE INDEX IF NOT EXISTS idx_sys_dict_data_dict_sort ON sys_dict_data(dict_sort);

-- ===========================================
-- 表结构优化建议
-- ===========================================

-- 1. 为经常更新的字段添加乐观锁版本号
-- ALTER TABLE sys_user ADD COLUMN version INT DEFAULT 0 COMMENT '版本号，用于乐观锁';
-- ALTER TABLE sys_role ADD COLUMN version INT DEFAULT 0 COMMENT '版本号，用于乐观锁';

-- 2. 为大文本字段考虑分表存储
-- 如：sys_log 表的 params 字段可以考虑单独存储

-- 3. 添加软删除字段统一管理
-- ALTER TABLE sys_user ADD COLUMN deleted TINYINT DEFAULT 0 COMMENT '逻辑删除标记';

-- ===========================================
-- 分区建议（针对大数据量表）
-- ===========================================

-- 日志表按月分区（示例）
/*
ALTER TABLE sys_log 
PARTITION BY RANGE (MONTH(create_time)) (
    PARTITION p202401 VALUES LESS THAN (2),
    PARTITION p202402 VALUES LESS THAN (3),
    PARTITION p202403 VALUES LESS THAN (4),
    -- 继续添加其他月份分区
    PARTITION p_max VALUES LESS THAN MAXVALUE
);
*/

-- ===========================================
-- 性能监控查询
-- ===========================================

-- 查看索引使用情况
-- SHOW INDEX FROM sys_user;
-- SHOW INDEX FROM sys_log;

-- 查看表大小
/*
SELECT 
    table_name AS '表名',
    ROUND(((data_length + index_length) / 1024 / 1024), 2) AS '表大小(MB)',
    ROUND((data_length / 1024 / 1024), 2) AS '数据大小(MB)',
    ROUND((index_length / 1024 / 1024), 2) AS '索引大小(MB)',
    table_rows AS '记录数'
FROM information_schema.tables 
WHERE table_schema = 'db_light' 
ORDER BY (data_length + index_length) DESC;
*/

-- 查看慢查询
-- SHOW VARIABLES LIKE 'slow_query_log';
-- SHOW VARIABLES LIKE 'long_query_time';

-- ===========================================
-- 执行计划分析示例
-- ===========================================

-- 用户登录查询优化
-- EXPLAIN SELECT * FROM sys_user WHERE username = 'admin' AND state = 1;

-- 日志分页查询优化  
-- EXPLAIN SELECT * FROM sys_log WHERE username = 'admin' ORDER BY create_time DESC LIMIT 10, 20;

-- 权限查询优化
-- EXPLAIN SELECT p.* FROM sys_permission p 
-- JOIN sys_role_permission rp ON p.id = rp.permission_id 
-- JOIN sys_user_role ur ON rp.role_id = ur.role_id 
-- WHERE ur.user_id = 1;

-- ===========================================
-- 数据库配置优化建议
-- ===========================================

/*
-- MySQL 配置优化建议（在 my.cnf 中配置）

[mysqld]
# 基础配置
default-storage-engine = InnoDB
character-set-server = utf8mb4
collation-server = utf8mb4_unicode_ci

# 内存配置
innodb_buffer_pool_size = 1G          # 根据服务器内存调整
innodb_log_buffer_size = 32M
key_buffer_size = 32M
max_connections = 500
thread_cache_size = 50

# 日志配置
innodb_log_file_size = 256M
innodb_flush_log_at_trx_commit = 2     # 性能优化，但降低一些安全性
sync_binlog = 0                        # 性能优化

# 查询缓存
query_cache_type = 1
query_cache_size = 128M
query_cache_limit = 2M

# 临时表配置
tmp_table_size = 64M
max_heap_table_size = 64M

# 慢查询日志
slow_query_log = 1
slow_query_log_file = /var/log/mysql/slow.log
long_query_time = 2
log_queries_not_using_indexes = 1
*/