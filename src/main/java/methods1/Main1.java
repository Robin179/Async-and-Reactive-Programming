package methods1;

import java.util.concurrent.CompletableFuture;

public class Main1 {
    public static void main(String[] args) {

        //suppose we have a network process and we want to accept its
        //value after modifying in some way
        System.out.println("Main thread starts Executing...");

        CompletableFuture.supplyAsync(() -> networkProcess(5))
                .thenApply(Main1::eveOddIncrease)
                .thenApply(Main1::eveOddIncrease)                   //you can use thenApply() mukltiple times
                .thenAccept(System.out::println)
                //now suppose you want to run another Async process with no return value (like a background process (data upload or anyother))
                // after you receive a response from this method,
                //in that case you use thenRun() method
                // remember ----> supplyAsync() takes Supplier<U> objects whereas runAsync() takes Runnable<U> objects
                .thenRun(Main1::veryLongProcess);

        System.out.println("Other methods in main thread");
        System.out.println("Main process delayed to wait for network processes response..");
        sleepForAWhile(9);
        System.out.println("Main process ends");
    }
    public static void veryLongProcess(){
        sleepForAWhile(3);
        System.out.println("Very long process execution completed!!!!!");
    }

    public static int networkProcess(int num){
        System.out.println(Thread.currentThread().getName());
        sleepForAWhile(3);
        return num * 10;
    }

    public static int eveOddIncrease(int num){
        sleepForAWhile(1);
        System.out.println(Thread.currentThread().getName());
        if(num % 2 == 0) {
            num = num+1;
        }
        else{
            num = num+3;
        }
        return num;
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
