package juc.v19;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T03_ReentrantLock3 {
    Lock lock = new ReentrantLock();

    void m1() {
        try {
            lock.lock();
            for(int i = 0; i < 10; i ++) {
                TimeUnit.SECONDS.sleep(1);

                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void m2() {
        boolean locked = false;

        try {
            locked = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println("m2 ..." + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(locked) lock.unlock();
        }
    }

    public static void main(String[] args) {
        T03_ReentrantLock3 reentrantLock3 = new T03_ReentrantLock3();
        new Thread(reentrantLock3::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentrantLock3::m2).start();
    }
}
