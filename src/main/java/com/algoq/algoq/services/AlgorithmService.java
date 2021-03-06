package com.algoq.algoq.services;

import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.respositories.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlgorithmService {

    @Autowired
    private SubscriberRepository subRep;

    /**
     * Gets a list of subscribers to return to the API
     * @return
     */
    public ArrayList<Subscriber> getSubscribers() {
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        subRep.findAll()
                .forEach(subscribers::add);
        return subscribers;
    }

    public String getTest() {
        return "test";
    }

    /**
     * Adds a new subscriber to the database
     * @param sub
     * @return
     */
    public void addSubscriber(Subscriber sub) {
        subRep.save(sub);
    }

    /**
     * Finds a single user id
     * @param email
     * @return
     */
    public List<Subscriber> getSubscriber(String email) {
        return subRep.findByEmailAddress(email);
    }
}
