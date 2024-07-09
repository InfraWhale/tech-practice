import java.util.*;

public class q2 {
    public static void main(String[] args) {
        int ret = 0;
        List<Integer> list = new ArrayList<Integer>();
        String temp = "";

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ('0' <= ch && ch <= '9') {
                temp += ch;
            }
            if (ch == ',' || i == input.length()-1) {
                list.add(Integer.valueOf(temp));
                temp = "";
            }
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                ret += getLCM(list.get(i), list.get(j));
            }
        }

        System.out.println(ret);
    }

    public static Integer getGCD(Integer num1, Integer num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return getGCD(num2, num1 % num2);
    }

    public static Integer getLCM(Integer num1, Integer num2) {
        return num1 * num2 / getGCD(num1, num2);
    }
}
