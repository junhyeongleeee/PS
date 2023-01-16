
data class Test(val a: Int, val b: String)

class Test2(a: Int, b: String)

fun main() {
    val test1 = Test(1, "1")
    val test2 = Test(1, "1")


    val test3 = Test2(1, "1")
    val test4 = Test2(1, "1")

    println("test1 == test2 : ${test1 == test2}")
    println("test1 === test2 : ${test1 === test2}")
    println("test1.hashCode() === test2.hashCode() : ${test1.hashCode() == test2.hashCode()}")

    val hashMap = mutableMapOf<Int, Int>()

    hashMap[1] = 1

    var i = 0
    i++

    println("test3 == test4 : ${test3 == test4}")
    println("test3 === test4 : ${test3 === test4}")
    println("test3.hashCode() === test4.hashCode() : ${test3.hashCode() == test4.hashCode()}")
}