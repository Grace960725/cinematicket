package com.nju.ticket.data.Repository;

import com.nju.ticket.data.entity.CinemaPo;
import com.nju.ticket.data.entity.MoviePo;
import com.nju.ticket.data.entity.ScreeningPo;
import com.nju.ticket.web.vo.MovieVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

/**
 * Created by sbin on 2017/6/8.
 */
public interface ScreeningRepository extends JpaRepository<ScreeningPo,Integer> {
    ScreeningPo findByMovieAndCinemaAndDateAndTime
            (MoviePo moviePo, CinemaPo cinemaPo, Date date,String time);

    List<ScreeningPo> findByMovieAndDate(MoviePo moviePo,Date date);

    @Query("select distinct s.date from ScreeningPo s where s.movie = ?1 order by s.date")
    List<Date> findMovieDate(MoviePo moviePo);

}
