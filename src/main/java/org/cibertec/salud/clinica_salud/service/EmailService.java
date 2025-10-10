package org.cibertec.salud.clinica_salud.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;


@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine  templateEngine;

    public EmailService(JavaMailSender javaMailSender,
                        SpringTemplateEngine templateEngine) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
    }

    public void enviarEmail (String emailDestino, String mensaje) {
        try {
            final String asunto = "Security App Salud y Vida";
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            String[] emails = {emailDestino};
            helper.setTo(emails);
            helper.setSubject(asunto);
            Context context = new Context();
            context.setVariable("subject", asunto);
            context.setVariable("message", mensaje);
            String htmlContent = templateEngine.process("email-template",context);
            helper.setText(htmlContent, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
