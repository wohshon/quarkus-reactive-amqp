package com.redhat.app.amqp;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.reactivestreams.Publisher;

@Path("/rest")
public class DataResource {
    @Inject
    @Channel("web-data-stream")
    Publisher<Integer> data;

    @Inject
    @Channel("messages")    
    Emitter<String> emitter;

    @Inject
    @Channel("web-converted-data-stream")
    Publisher<Double> convertedData;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream")
    @SseElementType("text/plain") 
    public Publisher<Integer> stream() {
        return data;
    }
    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Path("/stream/convert")
    @SseElementType("text/plain") 
    public Publisher<Double> convert() {
        return convertedData;
    }        

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/amqp/send/{message}")
    public String send(@PathParam String message) {
        emitter.send(message);
        return "";
    }          
}