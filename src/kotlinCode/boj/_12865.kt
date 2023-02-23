package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

/**
 *  평범한 배낭 ( 0/1 Knapsack Problem )
 *  - 짐을 쪼갤 수 없는 배낭 문제 (DP)
 *
 *  1 <= N <= 100
 *  1 <= K <= 100,000
 */

private val br = System.`in`.bufferedReader()
private lateinit var dp: IntArray
private lateinit var st: StringTokenizer
private data class Item(val w: Int, val v: Int)
private val list = mutableListOf<Item>()
fun main() = with(br){
    val (n, k) = readLine().split(" ").map { it.toInt() }

    dp = IntArray(k + 1)
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        list.add(Item(st.nextToken().toInt(), st.nextToken().toInt()))
    }
    solve1(n, k)

    println(dp[k])
}

fun solve1(n: Int, k: Int) {
    for (i in 0 until n) {
        var j = k
        while (j >= list[i].w) {
            dp[j] = max(dp[j], dp[j - list[i].w] + list[i].v)
            j--
        }
    }
}