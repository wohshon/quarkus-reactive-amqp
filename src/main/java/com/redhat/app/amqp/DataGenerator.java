package com.redhat.app.amqp;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.reactivex.Flowable;

public class DataGenerator {
    private Random random = new Random();
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Outgoing("generated-data")                        
    public Flowable<Integer> generate() {               
        return Flowable.interval(2, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(100));
    }
}