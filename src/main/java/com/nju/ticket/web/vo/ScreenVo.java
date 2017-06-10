package com.nju.ticket.web.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by sbin on 2017/6/10.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScreenVo {

    String cinema;
    String time;
    double price;
    String remain;
    String type;

}
