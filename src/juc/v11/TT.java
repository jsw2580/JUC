package juc.v11;

public class TT extends T {

    @Override
    synchronized void m() {
        System.out.println("Child m start");
        super.m();
        System.out.println("Child m end");
    }
}
