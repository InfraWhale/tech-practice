package javaCd;

import java.util.*;

public class CollectionJv {
    public static void main(String[] args) {
        Set<Integer> set = Set.of(1, 7, 53);
        List<Integer> list = List.of(1, 7, 53);
        Map<Integer, String> map = Map.of(1, "one", 7, "seven", 53, "fifty-three");

        for (int item : set) {
            System.out.println(item);
        }
        for (int item : list) {
            System.out.println(item);
        }
        // 요소 출력 시 삽입순서가 보장 안된다. -> 보장하고 싶으면 LinkedHashMap 사용할 것
        for (int key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }


}
