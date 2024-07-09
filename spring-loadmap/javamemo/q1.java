import java.util.*;

public class q1 {
    public static void main(String[] args) {
        int answer;
        int score;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 0);
        map.put("b", 0);
        map.put("c", 0);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int findIndex = input.indexOf("answer");

        while (findIndex >= 0) {
            answer = input.charAt(findIndex + 9) - '0';
            score = input.charAt(findIndex + 20) - '0';
            switch(answer) {
                case 1:
                    map.replace("a", map.get("a")+score);
                    break;
                case 3:
                    map.replace("b", map.get("b")+score);
                    break;
                case 5:
                    map.replace("c", map.get("c")+score);
                    break;
                default:
                    break;
            }

            findIndex = input.indexOf("answer", findIndex + 1);
        }

        Integer maxValue = Collections.max(map.values());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxValue) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }

    }
}
