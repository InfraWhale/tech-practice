package org.ch13;

// 메인 쓰레드
public class ThreadEx2 {
    public static void main(String[] args) throws Exception {
        ThreadEx2_1 t1 = new ThreadEx2_1();
        t1.start(); // start를 쓰면 메인쓰레드가 아닌 새로운 쓰레드를 생성
    }
}

class ThreadEx2_1 extends Thread {
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
