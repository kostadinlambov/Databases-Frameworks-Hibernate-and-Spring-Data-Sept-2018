package com.kl.productshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.kl.productshop.io.xmlParser.XmlParser;
import com.kl.productshop.io.xmlParser.XmlParserImpl;
import com.kl.productshop.util.validation.ValidatorUtil;
import com.kl.productshop.util.validation.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtilImpl();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
