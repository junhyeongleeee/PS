package kotlinCode.softeer

import java.util.*

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer

fun main() = with(br) {
    val (k, p, n) = readLine().split(" ").map { it.toInt() }

    var result = k.toLong()
    repeat(n) {
        result *= p
    }
    println(result % 1000000007)
}