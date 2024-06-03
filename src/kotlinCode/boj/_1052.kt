package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.pow

/**
 *  5 3
 *  1 3
 *  1 1
 *  4 1
 *
 *  1 1 1 1 -> k = 1  0
 *  1 1 1 1 1 -> k = 3
 *
 *  1 -> k = 2
 *  (5, 3) -> (1, 2)
 *  (N, K) -> (1, 1)
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()  // n <= 10_000_000
    var k = st.nextToken().toInt()  // k <= 1_000

    if (k >= n) {
        println(0)
        return@with
    }

    var num = n
    while (k > 1) {
        var j = 1

        if (k >= num) {
            println(0)
            return@with
        }

        while (2.0.pow(j) < num) { j++ }
        num -= 2.0.pow(j - 1).toInt()
//        println("num: $num")
        k--
    }

    var i = 0
    while (2.0.pow(i) < num) { i++ }

    println(2.0.pow(i).toInt() - num)
}