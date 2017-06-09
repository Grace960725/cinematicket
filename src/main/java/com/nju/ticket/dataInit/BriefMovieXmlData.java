package com.nju.ticket.dataInit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by sbin on 2017/6/8.
 */
@Data
public class BriefMovieXmlData {

    String name;
    double score;

    @JsonProperty("url")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String img = "noImage.jpg";

}
