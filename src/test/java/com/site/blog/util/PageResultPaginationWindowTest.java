package com.site.blog.util;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class PageResultPaginationWindowTest {

    @Test
    public void paginationWindowShowsAllPagesWhenTotalPageIsFive() {
        PageResult pageResult = new PageResult(Collections.emptyList(), 40, 8, 5);

        assertEquals(1, pageResult.getPageStart());
        assertEquals(5, pageResult.getPageEnd());
    }

    @Test
    public void paginationWindowKeepsFivePagesNearTheEnd() {
        PageResult pageResult = new PageResult(Collections.emptyList(), 80, 8, 9);

        assertEquals(6, pageResult.getPageStart());
        assertEquals(10, pageResult.getPageEnd());
    }

    @Test
    public void paginationWindowCentersCurrentPageInTheMiddle() {
        PageResult pageResult = new PageResult(Collections.emptyList(), 80, 8, 5);

        assertEquals(3, pageResult.getPageStart());
        assertEquals(7, pageResult.getPageEnd());
    }

    @Test
    public void currentPageDoesNotExceedTotalPage() {
        PageResult pageResult = new PageResult(Collections.emptyList(), 16, 8, 5);

        assertEquals(2, pageResult.getCurrPage());
        assertEquals(1, pageResult.getPageStart());
        assertEquals(2, pageResult.getPageEnd());
    }
}
