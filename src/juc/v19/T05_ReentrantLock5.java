package juc.v19;

import java.util.concurrent.locks.ReentrantLock;

public class T05_ReentrantLock5 extends Thread {
    private static ReentrantLock lock = new ReentrantLock(true);
    public void run() {
        for(int i = 0; i < 100; i ++) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁");
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        T05_ReentrantLock5 reentrantLock5 = new T05_ReentrantLock5();
        Thread thread1 = new Thread(reentrantLock5);
        Thread thread2 = new Thread(reentrantLock5);
        thread1.start();
        thread2.start();
    }
}
