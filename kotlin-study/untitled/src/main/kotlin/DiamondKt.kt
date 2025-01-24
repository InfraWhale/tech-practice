import java.util.*

object DiamondKt {
    @JvmStatic
    fun main(args: Array<String>) {
        val scanner = Scanner(System.`in`)

        // 높이 입력 받기 (양의 홀수)
        print("Enter the height of the diamond (odd number): ")
        val height = scanner.nextInt()

        // 홀수인지 확인
        if (height % 2 == 0) {
            println("Please enter an odd number.")
            return
        }
        val mid = height / 2 // 다이아몬드의 중간 위치

        // 다이아몬드 상단 (중간 포함)
        for (i in 0..mid) {
            // 공백 출력
            for (j in 0 until mid - i) {
                print(" ")
            }
            // 별 출력
            for (j in 0 until 2 * i + 1) {
                print("*")
            }
            println()
        }

        // 다이아몬드 하단
        for (i in mid - 1 downTo 0) {
            // 공백 출력
            for (j in 0 until mid - i) {
                print(" ")
            }
            // 별 출력
            for (j in 0 until 2 * i + 1) {
                print("*")
            }
            println()
        }
        scanner.close()
    }
}
