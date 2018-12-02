package bookshopsystemapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    @Bean
    public BufferedReader bufferedReader() {
        return reader;
    }
}
