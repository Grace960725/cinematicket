package com.nju.ticket.dataInit.dataCreator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nju.ticket.dataInit.BriefMovieXmlData;
import com.nju.ticket.dataInit.MovieXmlData;
import com.nju.ticket.dataInit.ScreeningXmlData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbin on 2017/6/9.
 */
public class XmlReader {

    public static List<BriefMovieXmlData> getBriefMovieData(String filename){
        return readData(filename,new TypeReference<List<BriefMovieXmlData>>() { });
    }

    public static List<MovieXmlData> getMovieData(String filename){
        return readData(filename, new TypeReference<List<MovieXmlData>>() {});
    }

    private static <T> List<T> readData(String filename,TypeReference<List<T>> typeReference){

        File file = new File(filename);
        XmlMapper mapper = new XmlMapper();

        List<T> list = new ArrayList<>();
        try {
            list.addAll(mapper.readValue
                    (file, typeReference));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;

    }

}
