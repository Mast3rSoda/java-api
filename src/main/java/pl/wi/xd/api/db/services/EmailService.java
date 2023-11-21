package pl.wi.xd.api.db.services;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.engine.internal.ImmutableEntityEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.wi.xd.api.db.controller.EmailController;
import pl.wi.xd.api.db.services.interfaces.IEmailService;

@Service
@AllArgsConstructor
public class EmailService implements IEmailService {

    private JavaMailSender emailSender;

    @Override
    public void SendEmail(String emailTo, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("partify@spoko.pl");
        message.setTo(emailTo);
        message.setSubject(subject);
        message.setText(content);

        emailSender.send(message);
    }
}
