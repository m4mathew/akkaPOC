package com.akka.actors;

import akka.actor.AbstractActor;
import akka.actor.UntypedActor;
import com.akka.messages.FindAddressReqMessage;
import com.akka.messages.Student;

import java.util.HashMap;


public class ListenerActor extends AbstractActor {

    private final HashMap<String,Student> addressMap = new HashMap();

    public ListenerActor() {
        this.addressMap.put("Max",new Student("Max","Address1 , Street 1"));
        this.addressMap.put("Mathew",new Student("Mathew","Address2 , Street 2"));


    }


    @Override
    public Receive createReceive() {

        return receiveBuilder()
                .match(FindAddressReqMessage.class,
                        findAddressReqMessage -> {
                            String name = findAddressReqMessage.getName();
                            System.out.println("At Listener finding address for: " + name );
                            Student student = this.addressMap.get(name);
                            Thread.sleep(10000);
                            getSender().tell(student,getSelf());
                        }).build();


    }
}
