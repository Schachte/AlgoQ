package com.algoq.algoq.respositories;

import com.algoq.algoq.models.Subscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<Subscriber, String> {

    //Find someone just by the email alone (Not the PKEY)
    List<Subscriber> findByEmailAddress(String emailAddress);
}
