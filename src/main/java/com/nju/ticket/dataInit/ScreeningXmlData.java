package com.nju.ticket.dataInit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * Created by sbin on 2017/6/8.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ScreeningXmlData {

    String time;
    double price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String remain;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String type;

}
