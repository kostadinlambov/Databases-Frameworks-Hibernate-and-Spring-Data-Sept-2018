package kl.gamestore.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ModelMapper mapper = new ModelMapper();

    @Bean
    public BufferedReader bufferedReader() {
        return reader;
    }

    @Bean
    public ModelMapper modelMapper() {
        return mapper;
    }

}
