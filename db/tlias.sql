-- =====================================================
-- tlias 数据库初始化脚本
-- 学习用：创建数据库 tlias + 部门表 dept + 初始 6 个部门
-- 可重复执行（幂等）：存在则跳过建库建表，部门已存在则不重复插入
-- =====================================================

-- 1. 创建数据库（若不存在）
CREATE DATABASE IF NOT EXISTS tlias
    DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 2. 切换到该库
USE tlias;

-- 3. 创建部门表 dept
CREATE TABLE IF NOT EXISTS dept (
    id          INT          NOT NULL AUTO_INCREMENT COMMENT '部门 id，主键自增',
    name        VARCHAR(50)  NOT NULL                COMMENT '部门名称（非空）',
    create_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = '部门表';

-- 4. 插入 6 个部门（已存在的不重复插入）
INSERT INTO dept (name)
SELECT tmp.name
FROM (
    SELECT '学工部' AS name
    UNION ALL SELECT '教研部'
    UNION ALL SELECT '咨询部'
    UNION ALL SELECT '就业部'
    UNION ALL SELECT '人事部'
    UNION ALL SELECT '行政部'
) tmp
WHERE NOT EXISTS (SELECT 1 FROM dept);