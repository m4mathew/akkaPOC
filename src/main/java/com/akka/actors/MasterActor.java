package com.akka.actors;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import akka.actor.UntypedActor;

import com.akka.messages.FindAddressReqMessage;
import com.akka.messages.Student;


public class MasterActor extends AbstractActor{
    private AbstractActor.Receive reqAddress;
    private AbstractActor.Receive displayAddress;
    private final ActorRef listener;
    public MasterActor(ActorRef listener) {
    this.listener = listener;

    }

    @Override
    public Receive createReceive() {

        return receiveBuilder()
                .match(FindAddressReqMessage.class,
                        findAddressReqMessage -> {
                    //FindAddressReqMessage findAddressReqMessage = (FindAddressReqMessage)findReqmessage;
                     System.out.println("At Master : sending msg to Listener");
                     listener.tell(findAddressReqMessage, getSelf());
                })
                .match(Student.class,
                        student ->{
                            System.out.println("At Master : Received address for " + student.getName() + " Address:"+student.getAddress());
                        }).build();

    }





}
