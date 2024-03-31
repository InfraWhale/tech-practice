package org.ch13;


// synchronized 블럭
public class ThreadEx21 {
    public static void main(String[] args) {
        Runnable r = new RunnableEx21();
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc 대상이 아니다.
        new Thread(r).start(); // ThreadGroup에 의해 참조되므로 gc 대상이 아니다.
    }
}

class Account {
    private int balance = 1000; // private으로 해야 동기화가 의미가 있다.
    public int getBalance() {
        return balance;
    }

    // 동기화 없으면 하나의 객체에 여러 쓰레드가 달라붙을 수 있음 -> Transaction 위배
    
/*    public void withdraw(int money) { // synchronized 붙혀서 동기화시킴
        if(balance >= money) {
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
            balance -= money;
        }
    } // withdraw*/
    
    public synchronized void withdraw(int money) { // synchronized 붙혀서 동기화시킴
        if(balance >= money) {
            try {Thread.sleep(1000);} catch (InterruptedException e) {}
            balance -= money;
        }
    } // withdraw

/*    public void withdraw(int money) { // synchronized 붙혀서 동기화시킴
        synchronized(this) {
            if(balance >= money) {
                try {Thread.sleep(1000);} catch (InterruptedException e) {}
                balance -= money;
            }
        }
    } // withdraw*/
}

class RunnableEx21 implements Runnable {
    Account acc = new Account();

    public void run() {
        while(acc.getBalance() > 0) {
            // 100, 200, 300 중의 한 값을 임의로 선택하여 출금(withdraw)
            int money = (int) (Math.random() * 3 + 1) * 100;
            acc.withdraw(money);
            System.out.println("balance : " + acc.getBalance());
        }
    }
}
