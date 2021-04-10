package com.covid19.mailNotification;

import com.covid19.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendNotification(Subscriber subscriber) throws MailException, InterruptedException {

        System.out.println(subscriber.getFullName());
        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(subscriber.getEmailAddress());
        mail.setFrom("no-reply@covid.com");
        mail.setSubject("Your subscription");
        mail.setText("You have successfully subscribed to receive covid related notifications");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }

}
