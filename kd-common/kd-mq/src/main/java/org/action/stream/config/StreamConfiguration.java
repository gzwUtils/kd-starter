package org.action.stream.config;

import org.action.stream.producer.StreamProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class StreamConfiguration {


    @Bean
    public StreamProducer streamProducer() {
        return new StreamProducer();
    }
}
