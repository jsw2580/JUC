package juc.v20;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T04_NotifyFreeLock {
    volatile List lists = new ArrayList();

    public void add(Object object) {
        lists.add(object);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        T04_NotifyFreeLock notifyFreeLock = new T04_NotifyFreeLock();

        final Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动");
                if(notifyFreeLock.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
                System.out.println("t2 结束");
                lock.notify();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        new Thread(() -> {
            System.out.println("t1启动");
            synchronized (lock) {
                for(int i = 0; i < 10; i ++) {
                    notifyFreeLock.add(new Object());
                    System.out.println("add " + i);

                    if(notifyFreeLock.size() == 5) {
                        lock.notify();

                        try {
                            lock.wait();
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
