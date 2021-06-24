package juc.v07;

public class T implements Runnable {

    private volatile int count = 10;

    public synchronized void run() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i ++) {
            T t = new T();
            new Thread(t, "THREAD" + i).start();
        }
    }
}
