package com.remy.websokettokafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
public class WebsoketAndSendToKafkaApplication {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(WebsoketAndSendToKafkaApplication.class, args);
    }

    @Bean
    public ApplicationRunner initializeConnection(
            StreamWebSocketHandler streamWebSocketHandler) {
        return args -> {
            WebSocketClient rsvpsSocketClient = new StandardWebSocketClient();

            rsvpsSocketClient.doHandshake(
                    streamWebSocketHandler, environment.getProperty("MEETUP_ENDPOINT"));
        };
    }
}
