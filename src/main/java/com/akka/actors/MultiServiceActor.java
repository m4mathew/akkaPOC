package com.akka.actors;


import akka.actor.AbstractActor;
import akka.actor.Actor;

public class MultiServiceActor extends AbstractActor{

    AbstractActor.Receive service1;

    AbstractActor.Receive service2;

    public MultiServiceActor(){
        service1 = receiveBuilder().matchEquals("service1",s -> {
            System.out.println("Service 1");
        }).build();

        service2 = receiveBuilder().matchEquals("service2",s -> {
            System.out.println("Service 2");
        }).build();
    }



    @Override
    public Receive createReceive(){

        return receiveBuilder()
                .matchEquals("service1",msg-> {getContext().become(service1);
                    System.out.println("Changed to service1");})
                .matchEquals("service2",msg->{getContext().become(service2);
                    System.out.println("Changed to service2");
                })
                .build();
    }

}
