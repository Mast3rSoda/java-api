package pl.wi.xd.api.db.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wi.xd.api.db.controller.DTO.EmailDTO;
import pl.wi.xd.api.db.services.EmailService;
import pl.wi.xd.api.db.services.interfaces.IEmailService;

@RestController
@RequestMapping("v1/Email/TESTANDPREVIEWONLY")
public class EmailController {

    private final IEmailService emailService;
    Logger logger = LogManager.getLogger(EmailController.class);

    @Autowired
    public EmailController(IEmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public void sendEmail(@RequestBody EmailDTO emailDTO) {
        try {
            emailService.SendEmail(emailDTO.emailTo, emailDTO.subject, emailDTO.content);
            logger.info("Successfully sent email to {} with subject {}", emailDTO.emailTo, emailDTO.subject);
        }catch (Exception ex) {
            logger.error("Error while sending email to {}", emailDTO.emailTo);
            logger.error(ex.getMessage());
        }
    }
}
