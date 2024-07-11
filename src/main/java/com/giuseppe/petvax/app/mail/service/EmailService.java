package com.giuseppe.petvax.app.mail.service;

import com.giuseppe.petvax.app.mail.model.EmailTemplate;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    @Value("${spring.mail.username}")
    private String email;

    @Async
    public void sendRegisterConfirmation(String nombreUsuario, String email) throws MessagingException, UnsupportedEncodingException {
        String templatename = EmailTemplate.REGISTRATION_CONFIRMATION.getTemplateName();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, "UTF-8");
        helper.setSubject(EmailTemplate.REGISTRATION_CONFIRMATION.getSubject());
        //set the alias for email
        helper.setFrom(this.email, "PetVax");
        Map<String, Object> variables = new HashMap<>();
        variables.put("nombreUsuario", nombreUsuario);
        Context context = new Context();
        context.setVariables(variables);
        String html = templateEngine.process(templatename, context);
        helper.setText(html, true);
        helper.setTo(email);
        javaMailSender.send(message);
        log.info("Email sent successfully to {}", email);
    }
}
