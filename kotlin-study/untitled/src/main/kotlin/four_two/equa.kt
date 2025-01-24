package four_two

class Point(val x: Int, val y: Int) {
    override fun equals(obj: Any?): Boolean { // Any에 정의된 메소드를 오버라이딩 한다.
        if (obj === this) return true // 파라미터가 "this"와 같은 객체인지 살펴본다. 같으면 true
        // 같은 객체가 아니라면,
        if (obj !is Point) return false // 파라미터 타입을 검사한다. 다르면 false
        return obj.x == x && obj.y == y // Point로 스마트캐스트 해서 x와 y프로퍼티에 접근한다. 값이 같으면 true
    }


}

class People(val firstName: String, val lastName: String) : Comparable<People> {
    override fun compareTo(other: People): Int {
        return compareValuesBy(this, other, People::lastName, People::firstName)
    }

}

class People(val firstName: String, val lastName: String) : Comparable<People> {
    override fun compareTo(other: People): Int {
        // lastName을 먼저 비교하고, 동일하면 firstName을 비교
        val lastNameComparison = this.lastName.compareTo(other.lastName)
        return if (lastNameComparison != 0) {
            lastNameComparison
        } else {
            this.firstName.compareTo(other.firstName)
        }
    }
}

fun main() {
    println("=== equals test ===")
    println(Point(10, 20) == Point(10, 20))
    println(Point(10, 20) != Point(5, 5))
    println(null == Point(1,2))

    println("=== compare to test ===")
    val p1 = People("Alice", "Smith")
    val p2 = People("Bob", "Johnson")
    println(p1 < p2)
    println(p1 >= p2)

}

