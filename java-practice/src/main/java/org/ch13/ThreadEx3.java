package org.ch13;

// 메인 쓰레드
public class ThreadEx3 {
    public static void main(String[] args) throws Exception {
        ThreadEx3_1 t1 = new ThreadEx3_1();
        t1.run(); // run()을 쓰면 현재 쓰레드에서 호출됨
    }
}

class ThreadEx3_1 extends Thread {
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
