package org.ch12.generic;

import org.ch12.generic.domain.*;

import java.util.Collections;

// 와일드 카드 하한 제한 (Collection.sort)
public class FruitBoxEx4 {

    public static void main(String[] args) {
        FruitBox<Grape> grapeBox = new FruitBox<Grape>();
        FruitBox<Apple> appleBox = new FruitBox<Apple>();

        appleBox.add(new Apple("GreenApple", 300));
        appleBox.add(new Apple("GreenApple", 100));
        appleBox.add(new Apple("GreenApple", 200));

        grapeBox.add(new Grape("GreenGrape", 400));
        grapeBox.add(new Grape("GreenGrape", 300));
        grapeBox.add(new Grape("GreenGrape", 200));

        Collections.sort(appleBox.getList(), new AppleComp());
        Collections.sort(grapeBox.getList(), new GrapeComp());

        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);
        System.out.println(" ==================== ");

        Collections.sort(appleBox.getList(), new FruitComp());
        Collections.sort(grapeBox.getList(), new FruitComp());

        // public static <T> void sort(List<T> list, Comparator<? super T> c)
        // 해당 와일드카드의 정의에 따라, FruitBox<Apple>.getList()가 첫 파라미터로 오면, Apple의 부모 클래스가 와일드카드에 올 수 있다.
        // Comparator<Fruit> 인터페이스를 구현한 FruitComp가 오고, 해당 클래스의 sort는 자식 타입인
        // FruitBox<Apple>.getList(), FruitBox<Grape>.getList() 모두 sort 가능하다.

        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);
    }
}

