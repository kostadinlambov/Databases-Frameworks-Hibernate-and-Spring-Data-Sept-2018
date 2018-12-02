package com.kl.cardealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kl.cardealer.io.xmlParser.XmlParser;
import com.kl.cardealer.io.xmlParser.XmlParserImpl;
import com.kl.cardealer.util.validation.ValidatorUtil;
import com.kl.cardealer.util.validation.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidatorUtil validatorUtil(){
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
