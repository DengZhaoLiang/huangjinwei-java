-- SQL操作数据库结构示例

-- 基本原则
--
-- 1. 第一个字段必须叫id，主键，自增
-- 2. 常用查询数字、字符串字段加上索引
-- 3. 一个表的索引不超7个
-- 4. 如果索引字段是唯一的，用unique索引
-- 5. 关联其他表的关系字段取名 xxx_id ，xxx表示其他表表名，加索引
-- 6. 不加外键限制条件
-- 7. 一般表都要加 created_at 字段
-- 8. updated_at 字段具体再定
-- 9. 非整数数字一律使用decimal字段
-- 10. 时间字段使用 timestamp 字段
-- 11. NOT NULL + DEFAULT
-- 12. 字段定义都加上COMMENT

--
-- 这是注释的写法
--

-- 删表
DROP TABLE IF EXISTS `table_name`;

-- 建表
CREATE TABLE `table_name`
(
    -- 必须有 主键 自增 id 字段
    `id`                bigint(20) unsigned                     NOT NULL AUTO_INCREMENT,
    -- 定义string类型，默认长度是255
    -- 如果需要建索引，则限制长度为64
    `string_field1`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    `string_field2`     varchar(64) COLLATE utf8mb4_unicode_ci  NOT NULL,
    -- 定义整型字段，可以加 unsigned
    `integer_field1`    bigint(20)                              NOT NULL,
    `integer_field2`    int(10) unsigned                        NOT NULL,
    `integer_field3`    smallint(6)                             NOT NULL,
    `integer_field4`    tinyint(3)                              NOT NULL,
    `table_name2_id`    bigint(20)                              NOT NULL,
    -- 定义带小数的数字字段
    `longitude`         decimal(10, 6)                          NOT NULL,
    `latitude`          decimal(10, 6)                          NOT NULL,
    `price`             decimal(10, 2)                          NOT NULL,
    -- 定义大文本字段
    `text_content`      text                                    NOT NULL,
    `long_text_content` longtext                                NOT NULL,
    -- 定义时间字段
    `created_at`        timestamp                               NULL DEFAULT NULL,
    `updated_at`        timestamp                               NULL DEFAULT NULL,
    -- 定义主键，主键已经是索引，不用额外加索引
    PRIMARY KEY (`id`),
    -- 定义索引
    KEY `table_name_table_name2_id_index` (`table_name2_id`),
    KEY `table_name_longitude_index` (`longitude`),
    KEY `table_name_latitude_index` (`latitude`),
    -- 定义唯一索引
    UNIQUE KEY `table_name_string_field1_unique` (`string_field1`)
    -- 数据库引擎和字符集定义
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci

-- 操作字段

-- 修改表字段名和定义
ALTER TABLE `table_name`
    change `old_col_name` `new_col_name` column_definition;

-- 修改表字段定义
ALTER TABLE `table_name`
    modify `column_name` column_definition;

-- 增加表字段
ALTER TABLE `table_name`
    add `column_name` column_definition;

-- 删除表字段
ALTER TABLE `table_name`
    drop `column_name`;

-- 增加索引
ALTER TABLE `table_name`
    add KEY `key_name` (`column_name`);

-- 删除索引
ALTER TABLE `table_name`
    drop index `index_name`;
