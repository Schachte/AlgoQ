package com.algoq.algoq;

import com.algoq.algoq.controller.AlgorithmController;
import com.algoq.algoq.models.Subscriber;
import com.algoq.algoq.services.AlgorithmService;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class ExampleTest extends AlgoQApplicationTests {

    @Autowired
    private AlgorithmService aService;


    @Test
    public void serializeJson() throws Exception {
        assertThat(1).isEqualTo(1);
        ArrayList<Subscriber> subs = (ArrayList<Subscriber>) aService.getSubscribers();
        assertThat(subs).isEmpty();
    }

}