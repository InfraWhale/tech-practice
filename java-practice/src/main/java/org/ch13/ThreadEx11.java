package org.ch13;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

// 현재 실행중인 모든 데몬쓰레드 확인
class ThreadEx11 {
    public static void main(String[] args) throws Exception {
        ThreadEx11_1 t1 = new ThreadEx11_1("Thread1");
        ThreadEx11_2 t2 = new ThreadEx11_2("Thread2");

        t1.start();
        t2.start();
    }
}

class ThreadEx11_1 extends Thread {
    public ThreadEx11_1(String name) {
        super(name);
    }

    public void run() {
        try {
            sleep(5 * 1000);
        } catch (InterruptedException e) {}
    }
    // Interrupt : 스레드의 작업의 실행을 그만하고 리소스들을 정리한 다음 종료하라고 알려줄 때 사용
    // InterruptedException : 스레드들이 인터럽트를 주고 받을 때, 인터럽트를 받는 스레드가 블로킹될 수 있는
    // 메서드를 실행 시 발생
    // Blocking : 스레드가 작업을 기다리는 방식(반대는 논블로킹)
}

class ThreadEx11_2 extends Thread {
    public ThreadEx11_2(String name) {
        super(name);
    }

    public void run() {
        Map map = getAllStackTraces();
        Iterator it = map.keySet().iterator();

        int x = 0;
        while(it.hasNext()) {
            Object obj = it.next();
            Thread t = (Thread)obj; // key값 정보
            StackTraceElement[] ste = (StackTraceElement[]) (map.get(obj)); // value 값 정보
            System.out.println("[" + ++x + "]name : " + t.getName()
                                + ", group : " + t.getThreadGroup().getName()
                                + ", daemon : " + t.isDaemon()); 
            for (int i = 0; i < ste.length; i++) {
                System.out.println(ste[i]);
            }

            System.out.println();
        }
    }

}