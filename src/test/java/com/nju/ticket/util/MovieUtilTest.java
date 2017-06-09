package com.nju.ticket.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2017/6/8.
 */
public class MovieUtilTest {
    @Test
    public void movieIdentifier() throws Exception {
        System.out.println(MovieUtil.movieIdentifier("“吃吃”的爱"));
        System.out.println(MovieUtil.movieIdentifier("李雷和韩梅梅——昨日重现"));

    }

}