package com.microservices.gateway;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("routes")
@PropertySource(factory =YamlPropertySourceFactory.class ,value="classpath:blocked-routes.yml")
@Data
public class Config {
    private List<String> blocked;
}