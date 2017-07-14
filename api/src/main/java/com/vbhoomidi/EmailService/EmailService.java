package com.vbhoomidi.EmailService;

import com.vbhoomidi.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by vikramreddy on 7/13/2017.
 */
@Service
public class EmailService {

    private class SendEmail implements Runnable{

        private final Email email;

        public SendEmail(Email email) {
            this.email = email;
        }

        @Override
        public void run(){
            EmailService.this.simpleMailMessage.setFrom(email.getSenderEmail());
            EmailService.this.simpleMailMessage.setTo(email.getReceiverEmail());
            EmailService.this.simpleMailMessage.setText(email.getEmailMessage());
            EmailService.this.mailSender.send(simpleMailMessage);
        }
    }

    @Autowired
    private MailSender mailSender;

    @Autowired
    private SimpleMailMessage simpleMailMessage;

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @JmsListener(destination = "highalertemailqueue")
    public void receiveMessage(Email email) {
        emailTask(email);
    }

    public void emailTask(Email email) {
       executor.execute(new SendEmail(email));
    }


}
