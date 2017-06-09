package com.nju.ticket.data.Repository;

import com.nju.ticket.data.entity.CinemaPo;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.data.entity.ScreeningPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

/**
 * Created by sbin on 2017/6/8.
 */
public interface ScreeningRepository extends JpaRepository<ScreeningPo,Integer> {
    ScreeningPo findByMovieAndCinemaAndDateAndTime
            (MoviePo moviePo, CinemaPo cinemaPo, Date date,String time);
}
