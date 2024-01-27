package kotlinCode.boj


fun main() = with(System.`in`.bufferedReader()) {
    val (y1, m1, d1) = readLine().split(" ").map { it.toInt() }
    val (y2, m2, d2) = readLine().split(" ").map { it.toInt() }

    var result1 = 0
    var result2 = 1
    var result3 = 0

    result1 = y2 - y1 + if (m2 > m1) 0 else if(m2 == m1) { if (d2 >= d1) 0 else -1 } else -1
    result2 += y2 - y1
    result3 = y2 - y1

    println(result1)
    println(result2)
    println(result3)
}