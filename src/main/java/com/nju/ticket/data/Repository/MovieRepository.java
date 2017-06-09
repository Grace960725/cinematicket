package com.nju.ticket.data.Repository;

import com.nju.ticket.data.entity.MoviePo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * Created by sbin on 2017/6/8.
 */
public interface MovieRepository extends JpaRepository<MoviePo,Integer> {

    @Query("select m.identifier from MoviePo m")
    List<String> findIdentifier();

}
