package com.kl.cardealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kl.cardealer.io.FileIOUtil;
import com.kl.cardealer.io.FileIOUtilImpl;
import com.kl.cardealer.util.validation.ValidatorUtil;
import com.kl.cardealer.util.validation.ValidatorUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public FileIOUtil fileIOUtil() throws IOException {
        return new FileIOUtilImpl();
    }

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
}
