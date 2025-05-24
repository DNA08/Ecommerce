package com.gupta.sarthak.emailservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gupta.sarthak.emailservice.dtos.SendEmailDto;
import com.gupta.sarthak.emailservice.utils.EmailUtil;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@Service
public class SendEmailEventConsumer {

    private final ObjectMapper objectMapper;

    public SendEmailEventConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "sendEmail",groupId = "emailServiceGroup")
    public void handleEmailEvent(String emailEvent) throws JsonProcessingException {
        SendEmailDto event = this.objectMapper.readValue(emailEvent,SendEmailDto.class);
        if (event == null) {
            System.out.println("Received null email event");
            return;
        }
        if (event.getTo() == null || event.getSubject() == null || event.getBody() == null) {
            System.out.println("Received incomplete email event: " + emailEvent);
            return;
        }
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("randomtestuser167@gmail.com",
                        "ocqkllermmzdehck");
            }
        };
        Session session = Session.getInstance(properties, authenticator);

        EmailUtil.sendEmail(session, event.getTo(), event.getSubject(), event.getBody());
    }
}
