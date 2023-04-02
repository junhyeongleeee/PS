package kotlinCode.boj

import java.lang.Math.pow

/**
 *  피보나치 수 6
 */

const val MOD = 1000000007L
private var init = arrayOf(longArrayOf(1, 1), longArrayOf(1, 0))
private var A = arrayOf(longArrayOf(1, 0), longArrayOf(0, 1))
fun main() = with(System.`in`.bufferedReader()) {
    var N = readLine().toLong()

    if (N == 1L || N == 0L) {
        println(N)
        return@with
    }

    N--

    while (N > 0) {
        if (N%2 == 1L) {
            A = multiply(A, init)
        }
        init = multiply(init, init)

        N /= 2
    }

    println(A[0][0])
}

fun multiply(o1: Array<LongArray>, o2: Array<LongArray>)
    = arrayOf(
        longArrayOf(
            ((o1[0][0] * o2[0][0]) + (o1[0][1] * o2[1][0])) % MOD,
            ((o1[0][0] * o2[0][1]) + (o1[0][1] * o2[1][1])) % MOD),
        longArrayOf(
            ((o1[1][0] * o2[0][0]) + (o1[1][1] * o2[1][0])) % MOD,
            ((o1[1][0] * o2[0][1]) + (o1[1][1] * o2[1][1])) % MOD)
    )