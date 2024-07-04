package kotlinCode.boj

import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }

    var i = 1
    var total = 0L
    var numSum = 9L
    var remain = k.toLong()
    while (k > i * numSum) {
        remain -= (i*numSum)
        total += numSum
        numSum *= 10
        i++
    }
    val result = (total + 1) + ((remain - 1) / i)

    if (result > n) {
        println(-1)
    } else {
        println(result.toString().toCharArray()[((remain - 1) % i).toInt()])
    }
}