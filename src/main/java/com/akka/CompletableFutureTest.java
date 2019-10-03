package com.akka;


import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) {
        try {
            CompletableFuture<String> completableFuture = new CompletableFuture<String>();

            task(completableFuture);
            //after calling task() the next step(sysout) is executed. Once tast() is completed result will be available in completableFuture.get()
            System.out.println("Go to Task 2");
            System.out.println("Task 1 Completed :: result= " + completableFuture.get());

        }catch (Exception e){

        }
    }

    private static  void task(CompletableFuture completableFuture) throws Exception{
        Thread.sleep(1000);
        //completes the completableFuture.
        completableFuture.complete("Task 1 Result");
    }

}
