package org.ch12.generic;

import org.ch12.generic.domain.Apple;
import org.ch12.generic.domain.Box;
import org.ch12.generic.domain.Fruit;
import org.ch12.generic.domain.Toy;

// 지네릭 클래스의 객체 생성과 사용
public class FruitBoxEx1 {

    public static void main(String[] args) {
        Box<Fruit> fruitBox = new Box<Fruit>();
        Box<Apple> appleBox = new Box<Apple>();
        Box<Toy> toyBox = new Box<Toy>();
//        Box<Grape> grapeBox = new Box<Apple>(); //  타입 불일치

        fruitBox.add(new Fruit());
        fruitBox.add(new Apple()); // 가능
        
        appleBox.add(new Apple());
        appleBox.add(new Apple());
//        appleBox.add(new Toy()); // 에러

        toyBox.add(new Toy());
//        toyBox.add(new Apple()); // 에러

        System.out.println("fruitBox = " + fruitBox);
        System.out.println("appleBox = " + appleBox);
        System.out.println("toyBox = " + toyBox);
    }
}

