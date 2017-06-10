package com.nju.ticket.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbin on 2017/6/8.
 */
@Entity
@Table(name = "movie")
@Data
public class MoviePo {

    @Id
    @GeneratedValue
    @Column
    int id;

    @Column
    double score;

    @Column
    String name;

    @Column
    String img;

    @Column
    String identifier;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
    List<ScreeningPo> screeningList;

}
