package com.covid19.controller;



import com.covid19.mailNotification.NotificationService;
import com.covid19.model.Subscriber;
import com.covid19.model.SubscriberRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SubscriptionController {

    private Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SubscriberRepo subscriberRepo;

    @PostMapping(value = "/subscribe")
    public String subscriptionSuccess(@RequestParam(value = "fullName") String fullName, @RequestParam(value = "city") String city, @RequestParam(value = "emailAddress") String emailAddress){
        //model.addAttribute(subscriber);
        // create subscriber
        //model.addAttribute(subscriber);
        if(!subscriberRepo.existsById(emailAddress)){
            Subscriber subscriber = new Subscriber();
            subscriber.setFullName(fullName);
            subscriber.setCity(city);
            subscriber.setEmailAddress(emailAddress);
            subscriberRepo.save(subscriber);

            // send a notification
            try {
                notificationService.sendNotification(subscriber);
            }catch( Exception e ){
                // catch error
                logger.info("Error Sending Email: " + e.getMessage());
            }

            return "Thank you for subscribing with us.";
        }
        else {
            return "You are already subscribed";
        }

    }

}
