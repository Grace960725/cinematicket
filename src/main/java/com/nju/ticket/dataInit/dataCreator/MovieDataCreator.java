package com.nju.ticket.dataInit.dataCreator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.nju.ticket.data.Repository.MovieRepository;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.dataInit.BriefMovieXmlData;
import com.nju.ticket.dataInit.MovieXmlData;
import com.nju.ticket.util.MovieUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by sbin on 2017/6/8.
 */
@Component
public class MovieDataCreator {

    @Autowired
    private MovieRepository movieRepository;

    public boolean create(){
        movieRepository.deleteAll();
        List<MoviePo> gewaraList =
                getMoviePo("movieData/gewara_score.xml");

        movieRepository.save(gewaraList);

        saveIfNotExist(getMoviePo("movieData/meituan_score.xml"));
        saveIfNotExist(getMoviePo("movieData/nuomi_score.xml"));



        return true;
    }

    private void saveIfNotExist(List<MoviePo> moviePos){

        Set<String> identifierSet = new HashSet<>(movieRepository.findIdentifier());

        moviePos.stream()
                .filter( m -> !identifierSet.contains(m.getIdentifier()) )
                .forEach( m -> movieRepository.save(m));

    }

    private List<MoviePo> getMoviePo(String filename){

        return getBriefMovieData(filename).stream().map(xmlData->{
            MoviePo po = new MoviePo();
            BeanUtils.copyProperties(xmlData,po);
            po.setIdentifier(MovieUtil.movieIdentifier(po.getName()));
            return po;
        }).collect(Collectors.toList());

    }

    private List<BriefMovieXmlData> getBriefMovieData(String filename){

        File file = new File(filename);
        XmlMapper mapper = new XmlMapper();

        List<BriefMovieXmlData> briefMovieList = new ArrayList<>();

        try {
            briefMovieList.addAll(mapper.readValue
                    (file, new TypeReference<List<BriefMovieXmlData>>() { }));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return briefMovieList;
    }

}
