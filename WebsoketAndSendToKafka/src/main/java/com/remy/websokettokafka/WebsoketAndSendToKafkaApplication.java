package com.remy.websokettokafka;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@SpringBootApplication
public class WebsoketAndSendToKafkaApplication {

    private static final String MEETUP_RSVPS_ENDPOINT = "ws://stream.meetup.com/2/rsvps";

    public static void main(String[] args) {
        SpringApplication.run(WebsoketAndSendToKafkaApplication.class, args);
    }

    @Bean
    public ApplicationRunner initializeConnection(
            StreamWebSocketHandler streamWebSocketHandler) {
        return args -> {
            WebSocketClient rsvpsSocketClient = new StandardWebSocketClient();

            rsvpsSocketClient.doHandshake(
                    streamWebSocketHandler, MEETUP_RSVPS_ENDPOINT);
        };
    }
}
