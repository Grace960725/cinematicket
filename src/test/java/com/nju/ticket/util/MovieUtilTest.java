package com.nju.ticket.util;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2017/6/8.
 */
public class MovieUtilTest {
    @Test
    public void movieIdentifier() throws Exception {
//        System.out.println(MovieUtil.movieIdentifier("“吃吃”的爱"));
//        System.out.println(MovieUtil.movieIdentifier("李雷和韩梅梅——昨日重现"));

    }

    @Test
    public void stringSplit(){
        for(String s: ("新街口国际影城".split("[（）]"))) {
            System.out.println("s:"+s);
        }
    }

}