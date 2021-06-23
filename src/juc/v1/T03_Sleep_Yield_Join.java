package juc.v1;

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                System.out.println("A" + i);
                if(i % 10 == 0) Thread.yield();
            }
        }).start();

        new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                System.out.println("B" + i);
                if(i % 10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread thread1 = new Thread(() -> {
            for(int i = 0; i < 100; i ++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i = 0; i < 100; i ++) {
                System.out.println("B" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
