package com.nju.ticket.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by sbin on 2017/6/8.
 */
@Entity
@Table(name = "screen")
@Data
public class ScreeningPo {

    @Id
    @GeneratedValue
    @Column
    int id;

    @Column
    Date date;

    @Column
    String time;

    @Column
    String type;

    @Column
    String remain;

    @Column
    int price;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "movieId",referencedColumnName = "id")
    MoviePo movie;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "cinemaId",referencedColumnName = "id")
    CinemaPo cinema;

    @Override
    public String toString() {
        return "ScreeningPo{" +
                "id=" + id +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", remain='" + remain + '\'' +
                ", price=" + price +
                '}';
    }
}
