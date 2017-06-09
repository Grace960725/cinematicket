package com.nju.ticket.dataInit.dataCreator;

import com.nju.ticket.data.Repository.CinemaRepository;
import com.nju.ticket.data.Repository.MovieRepository;
import com.nju.ticket.data.Repository.ScreeningRepository;
import com.nju.ticket.data.entity.CinemaPo;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.data.entity.ScreeningPo;
import com.nju.ticket.dataInit.MovieXmlData;
import com.nju.ticket.dataInit.ScreeningXmlData;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by sbin on 2017/6/9.
 */
@Component
@Log
public class ScreenDataCreator {

    @Autowired
    MovieIdentifierReader identifierReader;

    @Autowired
    ScreeningRepository screeningRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    Set<String> identifierSet;
    List<CinemaPo> cinemaPos;

    public boolean create(){

        log.info("creating screen data");

        identifierSet = identifierReader.getIdentifierSet();
        cinemaPos = cinemaRepository.findAll();
        cinemaPos.size();
        screeningRepository.deleteAll();

        return false;
    }

    private void saveOneFileData
            (String filename,
             BiConsumer<ScreeningPo,ScreeningXmlData> callbackWhenExist){

        List<MovieXmlData> screeningXmlDatas = XmlReader.getMovieData("movieData/gewara.xml");

        screeningXmlDatas.forEach( m->saveOneMovie(m,callbackWhenExist) );

    }

    private void saveOneMovie
            (MovieXmlData movieXmlData,
             BiConsumer<ScreeningPo,ScreeningXmlData> callbackWhenExist){

        String identifier = identifierReader.getIdentifier(movieXmlData.getName());
        MoviePo moviePo = movieRepository.findByIdentifier(identifier);
        if(moviePo==null){
            return;
        }


    }

}
