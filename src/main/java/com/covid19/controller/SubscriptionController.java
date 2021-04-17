package com.covid19.controller;

import com.covid19.mailNotification.NotificationService;
import com.covid19.model.ConfirmationToken;
import com.covid19.model.ConfirmationTokenRepo;
import com.covid19.model.Subscriber;
import com.covid19.model.SubscriberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
public class SubscriptionController {

    private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SubscriberRepo subscriberRepo;

    @Autowired
    private ConfirmationTokenRepo confirmationTokenRepo;

    @PostMapping(value = "/subscribe")
    public String subscriptionSuccess(@RequestParam(value = "fullName") String fullName,
                                      @RequestParam(value = "city") String city,
                                      @RequestParam(value = "emailAddress") String emailAddress,
                                      @RequestParam(value = "recieveDailyStats") boolean recieveDailyStats){

        if(!subscriberRepo.existsById(emailAddress)){
            Subscriber subscriber = new Subscriber();
            subscriber.setFullName(fullName);
            subscriber.setCity(city);
            subscriber.setEmailAddress(emailAddress);
            subscriber.setRecieveNotifications(recieveDailyStats);
            System.out.println("saving subscriber");
            subscriberRepo.save(subscriber);
            ConfirmationToken confirmationToken = new ConfirmationToken(subscriber);
            System.out.println("saving token");
            confirmationTokenRepo.save(confirmationToken);

            // send a notification
            try {
                System.out.println("sending email");
                notificationService.sendConfirmNotification(subscriber, confirmationToken);
            }catch( Exception e ){
                // catch error
                logger.info("Error Sending Email: " + e.getMessage());
            }

            return "Just one more step. Please check your email to confirm your subscription.";
        }
        else {

            return "You are already subscribed";
        }

    }
    @PostMapping(value = "/unsubscribe")
    public String unSubscriptionSuccess(@RequestParam(value = "emailAddressUnsub") String emailAddress){

        if(subscriberRepo.existsById(emailAddress)){
            Subscriber subscriber = subscriberRepo.getOne(emailAddress);
            ConfirmationToken confirmationToken = new ConfirmationToken(subscriber);
            confirmationTokenRepo.save(confirmationToken);
            // send a notification
            try {
                notificationService.sendUnsubscribNotification(subscriber, confirmationToken);
            }catch( Exception e ){
                // catch error
                logger.info("Error Sending Email: " + e.getMessage());
            }

            return "We sent you a confirmation link via email.";
        }
        else {
            return "The email you entered is unknown";
        }

    }

    @RequestMapping(value="/confirm-email", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserSubscription(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepo.findByConfirmationTokenIgnoreCase(confirmationToken);

        if(token != null)
        {
            Subscriber subscriber = subscriberRepo.findByEmailAddressIgnoreCase(token.getSubscriber().getEmailAddress());
            subscriber.setEnabled(true);
            confirmationTokenRepo.deleteBySubscriber(subscriber);
            subscriberRepo.save(subscriber);
        }
        else
        {
            return "The link is invalid or broken!";
        }

        return "Your account has been activated.";
    }
    @RequestMapping(value="/confirm-unsubscribe", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserUnsubscription(@RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepo.findByConfirmationTokenIgnoreCase(confirmationToken);

        if(token != null)
        {
            Subscriber subscriber = subscriberRepo.findByEmailAddressIgnoreCase(token.getSubscriber().getEmailAddress());
            confirmationTokenRepo.deleteBySubscriber(subscriber);

        }
        else
        {
            return "The link is invalid or broken!";
        }

        return "You have unsubscribed from our website. We wish you all the best. farewell!";
    }

}
