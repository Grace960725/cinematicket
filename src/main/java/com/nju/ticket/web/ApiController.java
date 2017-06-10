package com.nju.ticket.web;

import com.nju.ticket.data.Repository.MovieRepository;
import com.nju.ticket.data.Repository.ScreeningRepository;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.data.entity.ScreeningPo;
import com.nju.ticket.web.vo.MovieVo;
import com.nju.ticket.web.vo.ScreenVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sbin on 2017/6/10.
 */
@RestController
@RequestMapping(value = "api",produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @RequestMapping("movie")
    public List<MovieVo> getMovieList(){
        List<MoviePo> moviePos = movieRepository.findAll();
        return moviePos.stream()
                .map(po->{
                    MovieVo movieVo = new MovieVo();
                    BeanUtils.copyProperties(po,movieVo);
                    return movieVo;
                }).collect(Collectors.toList());
    }

    @RequestMapping("movie/{id}")
    public MovieVo getMovie(@PathVariable int id){
        MovieVo movieVo = new MovieVo();
        BeanUtils.copyProperties(movieRepository.findOne(id),movieVo);
        return movieVo;
    }

    @RequestMapping("movie/{id}/date")
    @Transactional
    public List<Date> getMovieDateList(@PathVariable int id){
        MoviePo moviePo = movieRepository.findOne(id);
        return screeningRepository.findMovieDate(moviePo);
    }

    @RequestMapping("movie/{id}/date/{date}")
    public MovieVo getMovieScreen(@PathVariable int id,@PathVariable Date date){
        MoviePo moviePo = movieRepository.findOne(id);
        List<ScreeningPo> screeningPos = screeningRepository
                .findByMovieAndDate(moviePo,date);

        MovieVo movieVo = new MovieVo();
        BeanUtils.copyProperties(moviePo,movieVo);
        movieVo.setDate(date);

        List<ScreenVo> screenVos = screeningPos.stream()
                .map(po ->{
                    ScreenVo screenVo = new ScreenVo();
                    screenVo.setCinema(po.getCinema().getName());
                    BeanUtils.copyProperties(po,screenVo);
                    return screenVo;
                }).collect(Collectors.toList());
        movieVo.setScreens(screenVos);
        return movieVo;
    }

}
