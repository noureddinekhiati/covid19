package com.covid19.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationTokenIgnoreCase(String confirmationToken);
    @Transactional
    long deleteBySubscriber(Subscriber subscriber);
}
