import java.io.BufferedReader
import java.io.StringReader

class Collection {

}

fun main(args : Array<String>) {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set)
    println(list)
    println(map)
    println("-------------------------------------")
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    println("-------------------------------------")
    val strings = listOf("first", "second", "fourteenth")
    println(strings.last()) // list strings의 마지막 원소를 가져온다.
    val numbers = setOf(1, 14, 2)
    println(numbers.max()) // set numbers의 최댓값을 가져온다.

    for (i in 1..100 step -3) {
        println("$i ")
    }
}



//fun readNumber(reader: BufferedReader) {
//    val number = try{
//        // try의 값을 변수에 대입 가능
//        // try의 본문을 반드시 중괄호로 둘러싸야함
//        // 마지막 식의 값이 전체 결과 값
//        Integer.parseInt(reader.readLine())
//    }
//    catch(e: NumberFormatException){
//        // 예외가 발생한 경우 catch 블록 다음의 코드는 실행되지 않음
//        // 하지만, 계속 진행하고 싶다면, catch 블록도 값을 만들면 됨
//        // 마지막 식의 값이 전체 결과 값
//        null
//    }
//
//    reader.close()
//
//    return number
//}
//
//fun main(){
//    val reader = BufferedReader(StringReader("1234"))
//    println(readNumber(reader))
//}