package com.akka.messages;

/**
 * Created by a-8847 on 25/09/19.
 */
public class FindAddressReqMessage {
    private final String name;

    public FindAddressReqMessage(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
