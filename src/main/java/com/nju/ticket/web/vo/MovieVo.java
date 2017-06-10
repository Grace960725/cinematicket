package com.nju.ticket.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * Created by sbin on 2017/6/10.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieVo {

    int id;
    String name;
    double score;
    String img;

    Date date;
    List<ScreenVo> screens;

}
