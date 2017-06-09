package com.nju.ticket.util;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by sbin on 2017/6/9.
 */
public class StringSimilarity {

    @Test
    public void testSimilarity(){

        System.out.println
                (StringUtils.getLevenshteinDistance
                        ("潜艇总动员5时光宝盒","爱情开始的地方之遇见"));

    }

}
