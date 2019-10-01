package com.akka.messages;

public class Student {

    private final String name;
    private final String address;

    public Student(String name,String address){
        this.name = name;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public String getAddress(){
        return this.address;
    }

}
