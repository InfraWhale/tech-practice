package org.ch13;

// 쓰레드 생성하는 2가지 방법
public class ThreadEx1 {
    public static void main(String[] args) {
        ThreadEx1_1 t1 = new ThreadEx1_1();

        Runnable r = new ThreadEx1_2();
        Thread t2 = new Thread(r, "TigerThread"); // 생성자 Thread(Runnable target)

        t1.start();
        t2.start();
    }
}

class ThreadEx1_1 extends Thread {
    public void run() {
        for(int i=0; i < 5; i++) {
            System.out.println("getName() = " + getName()); // 조상인 Thread의 getName()을 호출
        }
    }
}

class ThreadEx1_2 implements Runnable {
    public void run() {
        for(int i=0; i < 5; i++) {
            // Thread.currentThread() - 현재 실행중인 Thread를 반환.
            System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        }
    }
}
