package kg.itacademy.stomservice.service.impl;

import kg.itacademy.stomservice.models.MailSendModel;
import kg.itacademy.stomservice.service.MailSendService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class MailSendServiceImpl implements MailSendService {
    final JavaMailSender javaMailSender;
    final Environment environment;

    @Override
    public boolean mailSend(MailSendModel mailSendModel) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            mimeMessage.setSubject(mailSendModel.getTitle(), "UTF-8");

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(Objects.requireNonNull(environment.getProperty("spring.mail.username")));
            mimeMessageHelper.setTo(mailSendModel.getReceiverEmail());
            mimeMessageHelper.setText(mailSendModel.getText(), false);
            javaMailSender.send(mimeMessage);
            log.info("send success to email " + mailSendModel.getReceiverEmail());
            return true;
        } catch (Exception ignored) {
            log.error(ignored.getMessage(), ignored);
            return false;
        }
    }
}

