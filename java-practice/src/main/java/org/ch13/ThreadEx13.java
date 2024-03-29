package org.ch13;

import javax.swing.*;

public class ThreadEx13 {
    public static void main(String[] args) {
        ThreadEx14_1 th1 = new ThreadEx14_1();
        th1.start();
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt(); // interrupt()를 호출하면, interrupted 상태가 true가 된다.
        System.out.println("th1.isInterrupted() = " + th1.isInterrupted()); // true
    }
}

class ThreadEx13_1 extends Thread {
    public void run() {
        int i = 10;

        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long x = 0; x < 250000L; x++) {
                for (long y = 0; y < 250000L; y++) {
                }; // 시간 지연
            }; // 시간 지연
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}