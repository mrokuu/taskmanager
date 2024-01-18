package com.example.taskmanager.login.component.email;

import com.example.taskmanager.application.domain.User;
import com.example.taskmanager.login.component.email.config.EmailProperties;
import com.example.taskmanager.login.component.email.config.EmailResetPasswordProperties;
import com.example.taskmanager.login.dto.response.EmailResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;

@Component
public class EmailSender {

    private static final Logger LOG = LoggerFactory.getLogger(EmailSender.class);

    private JavaMailSender mailSender;
    private EmailProperties emailProperties;
    private EmailResetPasswordProperties emailResetPasswordProperties;




    public EmailResponseDTO sendResetPasswordEmail(User user) {
//        String emailBody = MessageFormat.format(emailResetPasswordProperties.getBody(), user.getToken());
//        String emailSubject = emailResetPasswordProperties.getSubject();

//        sendMail(emailProperties.getFrom(), user.getEmail(), emailSubject, emailBody);

        EmailResponseDTO emailDto = new EmailResponseDTO("sent");

        return emailDto;
    }


    public void sendMail(String from, String to, String subject, String body) {

        Instant startTime = Instant.now();
        LOG.info("sendMail - start");

        try {
            MimeMessage message = message(from, to, subject, body);
            mailSender.send(message);
        } catch (MessagingException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            Instant endTime = Instant.now();
            LOG.info("[METRICS] --------------------> sendMail, time: {} {} ",
                    Duration.between(startTime, endTime).getSeconds(), "sec.");
        }
    }
}
