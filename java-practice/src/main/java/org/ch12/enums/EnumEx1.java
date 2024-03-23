package org.ch12.enums;

// Enum 기초
enum Direction1 {
    EAST, SOUTH, WEST, NORTH
}
public class EnumEx1 {
    public static void main(String[] args) {
        Direction1 d1 = Direction1.EAST;
        Direction1 d2 = Direction1.valueOf("WEST");
        Direction1 d3 = Enum.valueOf(Direction1.class, "EAST");

        System.out.println("d1 = " + d1);
        System.out.println("d2 = " + d2);
        System.out.println("d3 = " + d3);

        System.out.println("d1==d2 ? "+ (d1==d2));
        System.out.println("d1==d3 ? "+ (d1==d3));
        System.out.println("d1.equals(d3) = " + d1.equals(d3));
    //    System.out.println("(d1 > d3) = " + (d1 > d3)); // error: bad operand types for binary operator '>'
        System.out.println("d1.compareTo(d3) ? = " + d1.compareTo(d3));
        System.out.println("d1.compareTo(d3) ? = " + d1.compareTo(d2));

        switch (d1) {
            case EAST:
                System.out.println("direction은 EAST임");
                break;
/*            case direction1.EAST: // 이런식으로 쓸 수 없음
                System.out.println("direction은 EAST임");
                break;*/ 
            case WEST:
                System.out.println("direction은 WEST임");
                break;
            case SOUTH:
                System.out.println("direction은 SOUTH임");
                break;
            case NORTH:
                System.out.println("direction은 NORTH임");
                break;
            default:
                System.out.println("Invalid direction");
        }

        Direction1[] dArr = Direction1.values();

        for (Direction1 d : dArr) {
            System.out.printf("%s = %d %n", d.name(), d.ordinal());
        }
        //ordinal()로 나온 값의 경우 사용 안하는게 좋음(내부적인 용도로만 사용되기 때문)
    }

}
