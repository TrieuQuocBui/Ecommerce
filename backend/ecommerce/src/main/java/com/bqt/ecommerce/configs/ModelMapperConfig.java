package com.bqt.ecommerce.configs;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

// Annotation @Configuration show the spring know this class is place define Beans
@Configuration
public class ModelMapperConfig {

    // Annotation @Bean(only lie in config) tick on method allow the spring know this Bean and will carry out put Bean into Context
    @Bean
    public ModelMapper modelMapper(){
        // Init object and Config test
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }

}
