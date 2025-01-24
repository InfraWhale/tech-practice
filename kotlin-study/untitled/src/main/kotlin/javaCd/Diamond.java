package javaCd;

import java.util.Scanner;

public class Diamond {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 높이 입력 받기 (양의 홀수)
        System.out.print("Enter the height of the diamond (odd number): ");
        int height = scanner.nextInt();

        // 홀수인지 확인
        if (height % 2 == 0) {
            System.out.println("Please enter an odd number.");
            return;
        }

        int mid = height / 2;  // 다이아몬드의 중간 위치

        // 다이아몬드 상단 (중간 포함)
        for (int i = 0; i <= mid; i++) {
            // 공백 출력
            for (int j = 0; j < mid - i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        // 다이아몬드 하단
        for (int i = mid - 1; i >= 0; i--) {
            // 공백 출력
            for (int j = 0; j < mid - i; j++) {
                System.out.print(" ");
            }
            // 별 출력
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }

        scanner.close();
    }
}

