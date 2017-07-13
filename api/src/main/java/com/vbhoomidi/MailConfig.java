package com.vbhoomidi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by vikramreddy on 7/13/2017.
 */
@Configuration
@PropertySource(value = "classpath:application.properties")
public class MailConfig {

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty("javamail.host"));
        mailSender.setUsername(env.getProperty("javamail.username"));
        mailSender.setPort(Integer.parseInt(env.getProperty("javamail.port")));
        mailSender.setPassword(env.getProperty("javamail.password"));
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.ssl.trust",env.getProperty("javamail.ssl.trust"));
        properties.setProperty("mail.smtp.starttls.enable",env.getProperty("javamail.tls.enable"));
        properties.setProperty("mail.smtp.auth",env.getProperty("javamail.auth"));
        mailSender.setJavaMailProperties(properties);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage simpleMailMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        return simpleMailMessage;
    }
}
