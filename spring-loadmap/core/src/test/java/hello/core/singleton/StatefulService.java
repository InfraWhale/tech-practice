package hello.core.singleton;

public class StatefulService {

/*    private int price; // 상태를 유지하는 필드 -> 싱글톤 객체에 전역변수 설정하지 말자!!!

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " +price);
        this.price = price; // 문제!!!
    }*/

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        // this.price = price; // 문제!!!
        return price;
    }

/*    public int getPrice() {
        return price;
    }*/
}
