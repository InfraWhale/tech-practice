package org.ch13;

import javax.swing.*;

// 하나의 쓰레드 사용
public class ThreadEx6 {
    public static void main(String[] args) throws Exception{
        // JOptionPane : 사용자에게 값을 묻거나 정보를 알려주는 표준 대화 상자를 쉽게 팝업해서 이용할 수 있게 해줌
        // showInputDialog : 사용자에게 직접 입력을 받아 올 수 있음
        String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
        System.out.println("입력하신 값은 " + input + "입니다.");

        for (int i = 10; i > 0 ; i--) {
            System.out.println("i = " + i);
            try {
                Thread.sleep(1000);
            } catch (Exception e ) {}
        }
    }
}
