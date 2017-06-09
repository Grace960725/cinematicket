package com.nju.ticket.data.entity;

import javax.persistence.*;

/**
 * Created by sbin on 2017/6/8.
 */
@Entity
@Table(name = "screen")
public class ScreeningPo {

    @Id
    @GeneratedValue
    @Column
    int id;

    @Column
    String time;

    @Column
    String type;

    @Column
    String remain;

    @Column
    int price;

    @ManyToOne
    @JoinColumn(name = "movieId",referencedColumnName = "id")
    MoviePo movie;

    @ManyToOne
    @JoinColumn(name = "cinemaId",referencedColumnName = "id")
    MoviePo cinema;

}
