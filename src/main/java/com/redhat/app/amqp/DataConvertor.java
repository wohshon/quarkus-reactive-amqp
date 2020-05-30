package com.redhat.app.amqp;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.smallrye.reactive.messaging.annotations.Broadcast;

public class DataConvertor {
    Logger log = LoggerFactory.getLogger(this.getClass());
    //@Incoming("received-data")
    @Incoming("data-topic")
    //after receiving, send out to another channel
    @Outgoing("web-data-stream")
    @Broadcast                               
    public Integer process(int data) {
        log.info("Processing:"+data);
        return data;
    }

    //2nd method that changes output type
    @Incoming("web-data-stream") // took from the another in memory stream
    //after receiving, send out to another channel
    @Outgoing("web-converted-data-stream")
    @Broadcast      
    public Double convert(int data) {
        log.info("Converting:"+data);
        return data * 2.1; //random decimal number to generate Double
    }    
}