package com.covid19.mailNotification;

import com.covid19.model.ConfirmationToken;
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
    public void sendConfirmNotification(Subscriber subscriber, ConfirmationToken confirmationToken) throws MailException, InterruptedException {


        System.out.println(subscriber.getFullName());
        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(subscriber.getEmailAddress());
        mail.setFrom("no-reply@dailycovid19stats.com");
        mail.setSubject("Your subscription");
        mail.setText("Hi "+subscriber.getFullName()+", just one more step before subscribing to our website to get daily covid-related information.\n" +
                " Click on the link below to activate your account:\n" +
                "https://covidweb2019.herokuapp.com/confirm-email?token="+confirmationToken.getConfirmationToken()+"\n\n"+
                "Please do not reply directly to this email. If you have any questions or comments regading this email, please visit our website where you can get our support contact.");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }
    @Async
    public void sendUnsubscribNotification(Subscriber subscriber, ConfirmationToken confirmationToken) throws MailException, InterruptedException {


        System.out.println(subscriber.getFullName());
        System.out.println("Sending email...");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(subscriber.getEmailAddress());
        mail.setFrom("no-reply@dailycovid19stats.com");
        mail.setSubject("Your subscription");
        mail.setText("Hi "+subscriber.getFullName()+", you are about to unsubscribe from our website.\n" +
                " Click on the link below to confirm your decision:\n" +
                "https://covidweb2019.herokuapp.com/confirm-unsubscribe?token="+confirmationToken.getConfirmationToken()+"\n\n"+
                "If it wasn't you, please report to our support team. We will assist you at anytime.\n\n"+
                "Please do not reply directly to this email. You can visit our website to get in touche with our support team.");
        javaMailSender.send(mail);

        System.out.println("Email Sent!");
    }

}
