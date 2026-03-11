package com.rookies5.myspringbootlab.runner;

import com.rookies5.myspringbootlab.config.MyEnvironment;
import com.rookies5.myspringbootlab.property.MyPropProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class MyPropRunner implements ApplicationRunner {

    @Value("${myprop.username}")
    private String username;

    @Value("${myprop.port}")
    private int port;

    @Autowired
    private Environment environment;

    @Autowired
    private MyPropProperties properties;

    @Autowired
    private MyEnvironment myEnvironment;

    // Logger 객체 생성
    private Logger logger = LoggerFactory.getLogger(MyPropRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {

        logger.info("현재 실행 환경 = {}", myEnvironment.getMode());

        logger.info("MyPropProperties getUsername() = {}", properties.getUsername());
        logger.info("MyPropProperties getPort() = {}", properties.getPort());

        logger.info("${myprop.username} = {}", username);
        logger.info("${myprop.port} = {}", port);

        logger.debug(">>> environment username = {}", environment.getProperty("myprop.username"));

    }
}