package com.tripathiji;

public class Main {
    public static void main(String[] args) {
        System.out.println("Main Thread starts { ");
        System.out.println(" Network Process Starts...");
        System.out.println(networkProcess(5));
        //when we call a network process (on a server) it is obvious that its time of response is not fixed,
        //it may provide us with desired resource very quickly or it takes some time.
        //here, i've tried to simulate a similar situation
        //in this case unless and until the network process completes, no other process after it could run
        //that may be independent of output of this network process

        //The main thread gets 'blocked' in this way making the application a 'synchronized' one,

        System.out.println(" Another process A");
        System.out.println(" Another process B");
        System.out.println(" All processes finish");

        System.out.println("} Main thread ends");

    }

    public static int networkProcess(int num){
       try{
           Thread.sleep(3000);
       }
       catch (Exception e){
           System.out.println(e.getCause()+" "+e.getMessage());
       }

       return num * 10;
    }
}