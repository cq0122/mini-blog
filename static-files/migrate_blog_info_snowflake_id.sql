-- 将现有文章 ID 清洗为雪花 ID，并同步评论表、标签关系表中的 blog_id。
-- 执行前请先备份数据库；该脚本适用于当前项目 SQL 结构中无外键约束的表。

START TRANSACTION;

CREATE TEMPORARY TABLE tmp_blog_id_mapping (
  old_blog_id BIGINT(20) NOT NULL PRIMARY KEY,
  new_blog_id BIGINT(20) NOT NULL UNIQUE
) ENGINE = MEMORY;

SET @rownum := 0;
SET @snowflake_base := CAST((UNIX_TIMESTAMP(CURRENT_TIMESTAMP(3)) * 1000 - 1288834974657) AS UNSIGNED) << 22;

INSERT INTO tmp_blog_id_mapping (old_blog_id, new_blog_id)
SELECT
  blog_id,
  @snowflake_base + (@rownum := @rownum + 1)
FROM tb_blog_info
ORDER BY blog_id;

UPDATE tb_blog_comment c
JOIN tmp_blog_id_mapping m ON c.blog_id = m.old_blog_id
SET c.blog_id = m.new_blog_id;

UPDATE tb_blog_tag_relation r
JOIN tmp_blog_id_mapping m ON r.blog_id = m.old_blog_id
SET r.blog_id = m.new_blog_id;

UPDATE tb_blog_info b
JOIN tmp_blog_id_mapping m ON b.blog_id = m.old_blog_id
SET b.blog_id = m.new_blog_id;

ALTER TABLE tb_blog_info
  MODIFY COLUMN blog_id BIGINT(20) NOT NULL COMMENT '博客表主键id';

COMMIT;
