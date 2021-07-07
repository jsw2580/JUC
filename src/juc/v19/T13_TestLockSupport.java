package juc.v19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T13_TestLockSupport {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 10; i ++) {
                System.out.println(i);
                if(i == 5) {
                    LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        });

        thread.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("after 8 seconds!");

        LockSupport.unpark(thread);
    }
}
