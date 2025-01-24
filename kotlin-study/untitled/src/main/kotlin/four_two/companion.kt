package four_two

import Person

class OuterClass {
    private val outerSecret = "Outer Secret"

    class NestedClass {
        fun nestedFunction() {
            // outerSecret에 접근 불가 (외부 클래스 멤버)
            //println(outerSecret) // 에러 발생
        }
    }

    inner class InnerClass {
        fun innerFunction() {
            println(outerSecret) // 내부 클래스에서 외부 클래스의 멤버 접근 가능
        }
    }
}

class A {
    private val aSecret = "A Secret"
    companion object {
        fun bar() {
            println("Companion Object called")
            //println(aSecret)
        }
    }
}

/*class User {
    val nickname: String
    constructor(email: String) {
        nickname = email.substringBefore('@')
    }
    constructor(facebookAccountId: Int) {
        nickname = getFacebookName(facebookAccountId)
    }
}*/
//class User private constructor(val nickname: String){ // 주 생성자를 비공개로 만든다.
//    companion object {
//        fun newSubscribingUser(email: String) =
//            User(email.substringBefore('@'))
//        fun newFacebookUSer(accountId: Int) =
//            User(getFacebookName(accountId))
//    }
//}

interface JSONFactory<T> {
    fun fromJSON(jsonText: String) : T
}

//class Person(val name: String) {
//    companion object : JSONFactory<Person> {
//        override fun fromJSON(jsonText: String) : Person = ...
//    }
//}
//
//class PrivateUser(override val nickname:String) : User

fun main() {

    A.bar()

/*    // 중첩 클래스 호출
    val nested = OuterClass.NestedClass()
    nested.nestedFunction()

    // 내부 클래스 호출
    val outer = OuterClass()
    val inner = outer.InnerClass()
    inner.innerFunction()*/
}

