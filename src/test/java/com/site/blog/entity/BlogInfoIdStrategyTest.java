package com.site.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class BlogInfoIdStrategyTest {

    @Test
    public void blogIdUsesSnowflakeIdStrategy() throws NoSuchFieldException {
        Field blogIdField = BlogInfo.class.getDeclaredField("blogId");
        TableId tableId = blogIdField.getAnnotation(TableId.class);

        assertEquals(IdType.ID_WORKER, tableId.type());
    }
}
