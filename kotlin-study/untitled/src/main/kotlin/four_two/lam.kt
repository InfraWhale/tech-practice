package four_two

data class Person(val name: String, val age: Int)

fun findTheOldest (people: List<Person>) {
    var maxAge = 0 // 가장 많은 나이 저장
    var theOldest : Person? = null // 가장 연장자 저장
    for (person in people) {
        if(person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach { // 각 원소에 대해 수행할 작업을 람다로 받는다.
        println("$prefix $it") // 람다 안에서 함수의 "prefix" 파라미터를 사용한다.
    }
}

fun printProblemCounts(responses: Collection<String>) {
    var clientErrors = 0 // 람다에서 사용할 변수를 정의한다.
    var serveErrors = 0 // 람다에서 사용할 변수를 정의한다.
    responses.forEach {
        if(it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serveErrors++
        }
    }
    println("$clientErrors client error, $serveErrors server errors")
}

fun main() {

    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    //findTheOldest(people)

    //println(people.maxBy { it.age }) // 나이 프로퍼티를 비교해서 값이 가장 큰 원소를 찾는다.
    //println(people.maxBy (Person::age))
    println(people.maxBy() { p -> p.age })
//    val sum = {x: Int, y: Int -> x + y}
//    println(sum(1, 2))

    val sum = {x: Int, y: Int ->
        println("$x + $y 계산")
        x + y
    }
    println(sum(1, 2))

    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessageWithPrefix(errors, "Error:")

    val responses = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCounts(responses)
}

