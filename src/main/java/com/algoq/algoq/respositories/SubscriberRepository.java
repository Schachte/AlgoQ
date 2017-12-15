package com.algoq.algoq.respositories;

import com.algoq.algoq.models.Subscriber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends CrudRepository<Subscriber, String> {

    //Find someone just by the email alone (Not the PKEY)
    List<Subscriber> findByEmailAddress(String emailAddress);
}
