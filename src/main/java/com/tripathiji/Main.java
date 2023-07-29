package com.tripathiji;

import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main Thread starts { ");
        System.out.println(" Network Process Starts...");

//        //The solution to this problem is  to make the network method a 'CompletableFuture'
//        //meaning that a process that will be done sometime in the future, but for that it
//        //doesn't need to block other method in the main thread, or in the current thread in which
//        //it is called.
//
//        CompletableFuture.supplyAsync(()-> networkProcess(5))
//                .thenAccept(value -> System.out.println("network output received : "+value));
//        //in this case if you try to run the program the whole program will end immediately as, the whole
//        //program finishes (that contains the network process) before the network process finishes it's work,
//        //in case of industrial application the application keeps running on the server hence waiting for the
//        // output that a network process provides.
//        //to stimulate above conditions, let's make the main thread sleep(delay ending) for 6 seconds i.e. it will end three
//
//
        //but if suppose you have a network process that is very long and doesn't give you an output,
        //for example you are uploading a file to a server. in that case instead of using 'supplyAsync()'
        //we use "runAsync()" method of CompletableFuture.

        CompletableFuture.runAsync(()-> veryLongProcess());

        System.out.println(" Another process A");
        System.out.println(" Another process B");
        System.out.println(" All processes finish");


        System.out.println("delaying main thread to wait for network process response");
        sleepForAWhile(6);


        System.out.println("} Main thread ends");

    }

    public static void veryLongProcess(){
        sleepForAWhile(4);
        System.out.println("Very long process execution completed!!!!!");
    }

    public static int networkProcess(int num){
       sleepForAWhile(3);
       return num * 10;
    }

    public static void sleepForAWhile(int sec){
        try{
            Thread.sleep(sec* 1000L);
        }
        catch (Exception e){
            System.out.println(e.getCause()+" "+e.getMessage());
        }
    }
}