package com.vbhoomidi.EmailService;

import com.vbhoomidi.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by vikramreddy on 7/13/2017.
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    @Override
    @JmsListener(destination = "highalertemailqueue")
    public void receiveMessage(Email email) {
        SendEmail(email);
    }

    @Override
    public void SendEmail(Email email) {
        simpleMailMessage.setFrom(email.getSenderEmail());
        simpleMailMessage.setTo(email.getReceiverEmail());
        simpleMailMessage.setText(email.getEmailMessage());
        mailSender.send(simpleMailMessage);
    }
}
