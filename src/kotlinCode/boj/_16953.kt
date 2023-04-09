package kotlinCode.boj

import kotlin.math.min

/**
 *  A -> B
 *  - 2를 곱한다.
 *  - 1을 수의 가장 오른쪽에 추가한다.
 *  Greedy
 */
fun main() = with(System.`in`.bufferedReader()) {
    var (a, b) = readLine().split(" ").map { it.toInt() }
    var result = 0
    while (true) {

        if (a == b) {
            break
        }
        if (a > b) {
            println(-1)
            return@with
        }

        if (b % 10 == 1) {
            b /= 10
        } else if (b % 2 == 0) {
            b /= 2
        }
        else {
            println(-1)
            return@with
        }
        result++
    }

    println(result + 1)

}