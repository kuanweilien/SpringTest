package com.example.demo.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "webconfig")
public class WebConfig {
    @Value("${services.protocol}")
    private String protocol;
    @Value("${services.host}")
    private String host;
    @Value("${server.port}")
    private Integer port;
}
