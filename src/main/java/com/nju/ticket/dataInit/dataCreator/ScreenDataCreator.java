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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.util.HashSet;
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

        saveOneFileData("movieData/gewara.xml",(po,xml)->{/*do nothing*/});

        saveOneFileData("movieData/meituan.xml",(po,xml)->{
            po.setType(xml.getType());
            screeningRepository.save(po);
        });
        saveOneFileData("movieData/nuomi.xml",(po,xml)->{
            po.setRemain(xml.getRemain());
            setLowestPrice(po,xml);
            screeningRepository.save(po);
        });

        return false;
    }

    private void setLowestPrice(ScreeningPo po,ScreeningXmlData xmlData){

        if(xmlData.getPrice()==0){
            return;
        }

        if( po.getPrice()==0 || xmlData.getPrice()<po.getPrice() ){
            po.setPrice(xmlData.getPrice());
        }

    }

    private void saveOneFileData
            (String filename,
             BiConsumer<ScreeningPo,ScreeningXmlData> callbackWhenExist){

        List<MovieXmlData> screeningXmlDatas = XmlReader.getMovieData(filename);

        screeningXmlDatas.forEach( m->saveOneMovie(m,callbackWhenExist) );

    }

    private void saveOneMovie
            (MovieXmlData movieXmlData,
             BiConsumer<ScreeningPo,ScreeningXmlData> callbackWhenExist){

        String identifier = identifierReader.getIdentifier(movieXmlData.getName());

        //根据identifier找出电影
        MoviePo moviePo = movieRepository.findByIdentifier(identifier);
        if(moviePo==null){
            return;
        }

        //找出电影院
        CinemaPo cinemaPo = findSameCinema(movieXmlData);
        if(cinemaPo==null){
            return;
        }

        Date date = movieXmlData.getDate();

        //判断同一场次是否已经存在,不存在则存入,存在则调用回调
        movieXmlData.getScreenings().forEach( s->{

            ScreeningPo screeningPo = screeningRepository
                    .findByMovieAndCinemaAndDateAndTime(moviePo,cinemaPo,date,s.getTime());

            if(screeningPo==null){
                saveOneScreen(cinemaPo,moviePo,s,date);
            }else{
                callbackWhenExist.accept(screeningPo,s);
            }

        } );

    }

    private void saveOneScreen
            (CinemaPo cinemaPo,MoviePo moviePo,ScreeningXmlData xmlData,Date date){

        ScreeningPo screeningPo = new ScreeningPo();
        BeanUtils.copyProperties(xmlData,screeningPo);
        screeningPo.setMovie(moviePo);
        screeningPo.setCinema(cinemaPo);
        screeningPo.setDate(date);
        screeningRepository.save(screeningPo);

    }

    private CinemaPo findSameCinema(MovieXmlData movieXmlData){

        for(CinemaPo cinemaPo:cinemaPos){
            if(isSameCinema(cinemaPo,movieXmlData)){
                return cinemaPo;
            }
        }
        return null;
    }

    private boolean isSameCinema(CinemaPo cinemaPo,MovieXmlData movieXmlData){
        String name = movieXmlData.getCinema();

        boolean same = name.contains(cinemaPo.getShortName());

        if( ! StringUtils.isEmpty(cinemaPo.getLocation()) ){
            same = same&&name.contains(cinemaPo.getLocation());
        }

        return same;
    }

}
