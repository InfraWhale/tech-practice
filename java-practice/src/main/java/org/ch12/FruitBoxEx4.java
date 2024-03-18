package org.ch12;

import org.ch12.domain.*;

import java.util.Collections;

// 와일드 카드 사용
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

        System.out.println("appleBox = " + appleBox);
        System.out.println("grapeBox = " + grapeBox);
    }
}

