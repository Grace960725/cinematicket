package com.nju.ticket.dataInit.dataCreator;

import com.nju.ticket.dataInit.MovieXmlData;
import com.nju.ticket.dataInit.ScreeningXmlData;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2017/6/10.
 */
public class XmlReaderTest {
    @Test
    public void getMovieData() throws Exception {
        List<MovieXmlData> movieXmlDatas = XmlReader.getMovieData("movieData/gewara.xml");
        int screenCount = 0;

        for(MovieXmlData m : movieXmlDatas){
            screenCount += m.getScreenings().size();
        }

        System.out.println(screenCount);

    }

}