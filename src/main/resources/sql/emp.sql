-- =============================================================
-- 员工表 emp 和员工经历表 emp_expr 建表语句
-- 说明：
--   1. dept_id / emp_id 采用「逻辑外键」（仅加索引 + 注释说明），
--      不创建物理 FOREIGN KEY 约束，避免影响已有 dept 表结构；
--      关联完整性由应用层校验保证（删除部门/员工前先校验）。
--   2. 执行前请确认 tlias 库已存在，且 dept 表已创建。
-- =============================================================

-- 员工表
CREATE TABLE IF NOT EXISTS `emp` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '员工ID',
    `username`    VARCHAR(50)  NOT NULL COMMENT '登录用户名',
    `password`    VARCHAR(100) NOT NULL COMMENT '密码',
    `name`        VARCHAR(50)  NOT NULL COMMENT '姓名',
    `gender`      TINYINT      NOT NULL DEFAULT 1 COMMENT '性别：1男 2女',
    `phone`       VARCHAR(20)   DEFAULT NULL COMMENT '手机号',
    `image`       VARCHAR(255)  DEFAULT NULL COMMENT '头像URL',
    `job`         VARCHAR(50)   DEFAULT NULL COMMENT '职位',
    `entry_date`  DATE          DEFAULT NULL COMMENT '入职日期',
    `dept_id`     INT           DEFAULT NULL COMMENT '所属部门ID（逻辑外键，关联 dept.id）',
    `create_time` DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_emp_dept_id` (`dept_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '员工表';

-- 员工经历表
CREATE TABLE IF NOT EXISTS `emp_expr` (
    `id`          INT          NOT NULL AUTO_INCREMENT COMMENT '经历ID',
    `emp_id`      INT          NOT NULL COMMENT '员工ID（逻辑外键，关联 emp.id）',
    `company`     VARCHAR(100)  DEFAULT NULL COMMENT '公司/单位',
    `position`    VARCHAR(100)  DEFAULT NULL COMMENT '职位',
    `start_date`  DATE          DEFAULT NULL COMMENT '开始日期',
    `end_date`    DATE          DEFAULT NULL COMMENT '结束日期',
    `description` VARCHAR(500)  DEFAULT NULL COMMENT '经历描述',
    `create_time` DATETIME      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `idx_emp_expr_emp_id` (`emp_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '员工经历表';

-- =============================================================
-- 示例数据（供学习测试用）
-- 说明：
--   1. 员工 id 显式指定为 1~4，方便下方 emp_expr 用 emp_id 关联；
--   2. dept_id 请按你 dept 表中实际存在的部门 id 调整（下方假设有 1/2/3 号部门）；
--   3. password 为明文示例，真实项目中应存 MD5/BCrypt 加密后的值。
-- =============================================================

-- 员工表数据
INSERT INTO `emp` (`id`, `username`, `password`, `name`, `gender`, `phone`, `image`, `job`, `entry_date`, `dept_id`) VALUES
(1, 'zhangsan', '123456', '张三', 1, '13800000001', NULL, '讲师',      '2020-09-01', 1),
(2, 'lisi',     '123456', '李四', 2, '13800000002', NULL, '班主任',    '2021-03-01', 1),
(3, 'wangwu',   '123456', '王五', 1, '13800000003', NULL, '学工主管',  '2019-07-01', 2),
(4, 'zhaoliu',  '123456', '赵六', 2, '13800000004', NULL, '就业指导',  '2022-06-01', 3);

-- 员工经历表数据（end_date 为 NULL 表示至今）
INSERT INTO `emp_expr` (`emp_id`, `company`, `position`, `start_date`, `end_date`, `description`) VALUES
(1, '杭州某科技公司',   'Java开发工程师', '2017-07-01', '2020-08-31', '负责后端接口开发与维护'),
(1, '某高校',           '讲师',           '2020-09-01', NULL,          '主讲 Java 与 Spring 相关课程'),
(2, '某教育集团',       '教务专员',       '2018-06-01', '2021-02-28', '负责教务管理与学员沟通'),
(3, '某互联网公司',     '技术主管',       '2015-07-01', '2019-06-30', '带领团队完成多个项目交付'),
(4, '某职业培训机构',   '就业指导老师',   '2020-07-01', '2022-05-31', '负责学员就业推荐与辅导');