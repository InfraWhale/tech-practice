package org.ch13;

// interrupt(), interrupted() 2
import javax.swing.*;

public class ThreadEx14 {
    public static void main(String[] args) {
        ThreadEx14_1 th1 = new ThreadEx14_1();
        th1.start();

        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요.");
        System.out.println("입력하신 값은 " + input + "입니다.");
        th1.interrupt(); // interrupt()를 호출하면, interrupted 상태가 true가 된다.
        System.out.println("th1.isInterrupted() = " + th1.isInterrupted());
    }
}

class ThreadEx14_1 extends Thread {
    public void run() {
        int i = 10;

        while(i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000); // 1초 지연
            } catch (InterruptedException e) {
                interrupt(); // InterruptedException 발생 시, interrupted 상태가 false로 초기화되므로 다시 true로 바꿔줌
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}