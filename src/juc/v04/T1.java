package juc.v04;

public class T1 {

    private int count = 10;

    public synchronized void m() {
        count --;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public void n() {
        count ++;
    }
}
