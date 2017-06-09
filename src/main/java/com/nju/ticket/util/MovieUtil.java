package com.nju.ticket.util;

/**
 * Created by sbin on 2017/6/8.
 */
public class MovieUtil {

    public static String movieIdentifier(String movieName){
        return movieName.replaceAll("[\\pP‘’“”]", "");
    }

}
