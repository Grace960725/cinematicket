package com.nju.ticket.dataInit;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nju.ticket.dataInit.dataCreator.CinemaDataCreator;
import com.nju.ticket.dataInit.dataCreator.MovieDataCreator;
import com.nju.ticket.dataInit.dataCreator.ScreenDataCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sbin on 2017/6/8.
 */
@Component
public class DataInit {

    @Value("${app.initData}")
    private boolean initData;

    @Autowired
    private MovieDataCreator movieDataCreator;

    @Autowired
    private CinemaDataCreator cinemaDataCreator;

    @Autowired
    private ScreenDataCreator screenDataCreator;

    @PostConstruct
    public void init(){
        if(initData){
            try {
                initInterval();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initInterval() throws IOException {

        movieDataCreator.create();
        cinemaDataCreator.create();
        screenDataCreator.create();

    }

}
