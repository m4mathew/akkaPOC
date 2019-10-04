package com.akka;

import akka.actor.*;
import com.akka.actors.ListenerActor;
import com.akka.actors.MasterActor;
import com.akka.messages.FindAddressReqMessage;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class AkkaBootStrap {
    public static void main(String args[]){
        ActorSystem system = ActorSystem.create("MyAkkaSampleSystem");
        // create the listener
        final ActorRef listener = system.actorOf(Props.create(ListenerActor.class),"listener");

        // create the master
        ActorRef master = system.actorOf( Props.create(MasterActor.class,listener), "master");

        System.out.println("Actor Ref path: " + master.path().toString());
        master.tell(new FindAddressReqMessage("Mathew"), ActorRef.noSender());

        //using selection
        ActorSelection selection = system.actorSelection("/user/master");

        //System.out.println("## Using Selection ## path " + selection.pathString() );

        //selection.tell(new FindAddressReqMessage("Max"),ActorRef.noSender());


        //thenAccept use
        checkIfActorExists(system,"/user/master").thenAccept(actorRef -> actorRef.tell(new FindAddressReqMessage("Max"),ActorRef.noSender()));

        // exceptionally use
        checkIfActorExists(system,"/user/InValidActor");


    }

    private static CompletionStage<ActorRef> checkIfActorExists(ActorSystem system, String path){
        System.out.println("Check if Actor Exists");
            ActorSelection selection = system.actorSelection(path);

            //returns Actor Ref if Actor exists.
            return selection.resolveOne(Duration.ofMillis(1000))
                    .exceptionally(e->{
                        System.out.println("Exception Occured  " + e.getMessage());
                        return null;
                    });

    }

}
