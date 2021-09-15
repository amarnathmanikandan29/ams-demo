package com.retailbanking.ams.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class AmsAutoConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
