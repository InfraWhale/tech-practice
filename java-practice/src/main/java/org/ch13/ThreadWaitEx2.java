package org.ch13;


import java.util.ArrayList;

// wait(), notify()
public class ThreadWaitEx2 {
    public static void main(String[] args) throws Exception{
        Table2 table2 = new Table2(); //여러 쓰레드가 공유하는 객체

        new Thread(new Cook2(table2), "COOK1").start();
        new Thread(new Customer2(table2, "donut"), "CUST1").start();
        new Thread(new Customer2(table2, "burger"), "CUST2").start();

        Thread.sleep(5000); // 5초 후 강제종료
        System.exit(0); // 프로그램 전체 종료(모든 쓰레드 종료)
    }
}

class Customer2 implements Runnable {
    private Table2 table2;
    private String food;

    public Customer2 (Table2 table2, String food) {
        this.table2 = table2;
        this.food = food;
    }

    public void run() {
        while(true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {}
            String name = Thread.currentThread().getName();

            if(eatFood()) {
                System.out.println(name + " ate a " + food);
            } else {
                System.out.println(name + " failed to eat. :( ");
            }
        }
    }

    boolean eatFood() { return table2.remove(food); }
}

class Cook2 implements Runnable {
    private Table2 table2;
    Cook2 (Table2 table2) {this.table2 = table2; }

    public void run() {
        while(true) {
            //임의의 요리를 하나 선택해서 table에 추가한다.
            int idx = (int) (Math.random() * table2.dishNum()); // 0, 1, 2
            table2.add(table2.dishNames[idx]);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }
}

class Table2 {
    String[] dishNames = { "donut", "donut", "burger"}; // 도넛이 더 자주나온다.
    final int MAX_FOOD = 6; // 테이블에 놓을 수 있는 최대 음식의 개수
    private ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) { // synchronized 추가
        //테이블에 음식이 가득찼으면, 테이블에 음식을 추가하지 않는다.
        if(dishes.size() >= MAX_FOOD)
            return;
        dishes.add(dish);
        System.out.println("Dishes : " + dishes.toString());
    }

    public boolean remove(String dishName) {
        synchronized (this) {
            while(dishes.size() == 0) {
                String name = Thread.currentThread().getName();
                System.out.println(name + " is waiting.");
                try {Thread.sleep(500);} catch (InterruptedException e) {}
            }
            for (int i = 0; i < dishes.size(); i++) {
                if( dishName.equals(dishes.get(i)) ) {
                    dishes.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    public int dishNum() { return dishNames.length; }
}
