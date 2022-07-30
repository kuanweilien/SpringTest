package com.example.demo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class EmailService implements EmailSender{

    private final static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private final GmailSender gmailSender;

    @Override
    @Async
    public void send(String to,String content)
    {
        try
        {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            helper.setText(content,true);
            helper.setTo(to);
            helper.setSubject("Spring Confirm Email");
            
            gmailSender.getJavaMailSender().send(message);

        }
        catch(MessagingException ex)
        {
            LOGGER.error("failed to end eamil", ex);
            throw new IllegalStateException("failed to end email");

        }
    }
}
