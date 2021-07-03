package juc.v16;

import java.util.concurrent.TimeUnit;

public class T {
    Object object = new Object();

    void m() {
        synchronized (object) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(t::m, "t2");

        t.object = new Object();

        thread.start();
    }
}
