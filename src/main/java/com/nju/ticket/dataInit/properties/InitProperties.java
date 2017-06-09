package com.nju.ticket.dataInit.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Map;

/**
 * Created by sbin on 2017/6/9.
 */
@Configuration
@ConfigurationProperties("app")
public class InitProperties {

    public Map<String, String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Map<String, String> synonyms) {
        this.synonyms = synonyms;
    }

    Map<String,String> synonyms;

}
