package com.akka;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.akka.actors.MultiServiceActor;
import com.akka.messages.FindAddressReqMessage;

/**
 * Created by a-8847 on 26/09/19.
 */
public class AkkaBecomeTest {
    public static void main(String args[]){
        ActorSystem system = ActorSystem.create("MyAkkaSystem");
        // create the listener
        final ActorRef service = system.actorOf(Props.create(MultiServiceActor.class),"serviceActor");
        service.tell("service1",ActorRef.noSender());
        service.tell("service1",ActorRef.noSender());
    }
}
