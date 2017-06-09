package com.nju.ticket.util;

import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.CosineSimilarity;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

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

    @Test
    public void cosSimilarity(){

        //幸福蓝海IMAX影城常发广场店
        //"幸福蓝海国际影城常发IMAX店"

        //新街口
        //万达新街口店

        Map<CharSequence,Integer> left = new TreeMap<>();
        ToAnalysis.parse("幸福蓝海IMAX常发广场").forEach(t -> {
            left.put(t.getName(),1);
        });


        Map<CharSequence,Integer> right = new TreeMap<>();
        ToAnalysis.parse("幸福蓝海常发IMAX").forEach(t -> {
            right.put(t.getName(),1);
        });

        CosineSimilarity cosineSimilarity = new CosineSimilarity();
        double result = cosineSimilarity.cosineSimilarity(left,right);
        System.out.println(result);
    }

}
