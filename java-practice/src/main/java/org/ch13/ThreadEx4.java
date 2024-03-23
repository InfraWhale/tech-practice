package org.ch13;

// 하나의 쓰레드로 진행
public class ThreadEx4 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("-"));
        }

        System.out.print("소요시간1:" + (System.currentTimeMillis() - startTime));

        for (int i = 0; i < 300; i++) {
            System.out.printf("%s", new String("|"));
        }

        System.out.print("소요시간2:" + (System.currentTimeMillis() - startTime));
    }
}
