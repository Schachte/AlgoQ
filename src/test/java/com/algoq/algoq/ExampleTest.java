package com.algoq.algoq;

import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.respositories.SubscriberRepository;
import com.algoq.algoq.services.AlgorithmService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestConfiguration
public class ExampleTest extends AlgoQApplicationTests {

    @Autowired
    private AlgorithmService aService;

//    @MockBean
//    private SubscriberRepository employeeRepository;

    @Bean
    public AlgorithmService aService() {
        return new AlgorithmService();
    }


    @Test
    public void subscriberListNull() throws Exception {
        ArrayList<Subscriber> subs = aService.getSubscribers();
        assertThat(subs).hasSize(1);
    }

}