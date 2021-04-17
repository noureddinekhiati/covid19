package com.covid19.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepo extends JpaRepository<Subscriber, String> {
    Subscriber findByEmailAddressIgnoreCase(String emailAddress);


}
