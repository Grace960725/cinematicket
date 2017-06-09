package com.nju.ticket.data.Repository;

import com.nju.ticket.data.entity.CinemaPo;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.data.entity.ScreeningPo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by sbin on 2017/6/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ScreeningRepositoryTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    ScreeningRepository screeningRepository;

    @Test
    @Transactional
    public void findByMovieAndCinemaAndDateAndTime() throws Exception {

        MoviePo moviePo = movieRepository.findAll().get(0);
        CinemaPo cinemaPo = cinemaRepository.findAll().get(0);

        ScreeningPo screeningPo = screeningRepository
                .findByMovieAndCinemaAndDateAndTime
                        (moviePo,cinemaPo,new Date(System.currentTimeMillis()),"19:00");

        Assert.assertEquals(screeningPo,null);

        System.out.println("movie:"+moviePo.getId());
        System.out.println("cinema:"+cinemaPo.getId());

        screeningPo = new ScreeningPo();

        screeningPo.setMovie(moviePo);
        screeningPo.setCinema(cinemaPo);
        screeningPo.setDate(new Date(System.currentTimeMillis()));
        screeningPo.setTime("19:00");

        screeningRepository.save(screeningPo);

        ScreeningPo po = screeningRepository
                .findByMovieAndCinemaAndDateAndTime
                        (moviePo,cinemaPo,new Date(System.currentTimeMillis()),"19:00");

        System.out.println(po.getCinema());
        System.out.println(po.getMovie());
    }

}