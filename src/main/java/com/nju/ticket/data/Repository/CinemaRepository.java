package com.nju.ticket.data.Repository;

import com.nju.ticket.data.entity.CinemaPo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sbin on 2017/6/8.
 */
public interface CinemaRepository extends JpaRepository<CinemaPo,Integer> {
}
