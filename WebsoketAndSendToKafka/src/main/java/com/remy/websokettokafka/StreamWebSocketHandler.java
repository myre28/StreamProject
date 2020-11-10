package com.remy.websokettokafka;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class StreamWebSocketHandler extends AbstractWebSocketHandler {

    private static final Logger logger =
            Logger.getLogger(StreamWebSocketHandler.class.getName());
    private final StreamKafkaProducer streamKafkaProducer;


    public StreamWebSocketHandler(StreamKafkaProducer streamKafkaProducer) {
        this.streamKafkaProducer = streamKafkaProducer;
    }

    @Override
    public void handleMessage(WebSocketSession session,
                              WebSocketMessage<?> message) {
        logger.log(Level.INFO, "New RSVP:\n {0}", message.getPayload());
        streamKafkaProducer.sendRsvpMessage(message);
    }
}
