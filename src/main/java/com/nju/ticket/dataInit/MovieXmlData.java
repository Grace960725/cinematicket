package com.nju.ticket.dataInit;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * Created by sbin on 2017/6/8.
 */
@Data
public class MovieXmlData {

    String name;
    String cinema;
    Date date;
    List<ScreeningXmlData> screenings;

}
