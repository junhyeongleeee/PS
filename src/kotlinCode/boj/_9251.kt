package kotlinCode.boj

import kotlin.math.max

/**
 *  LCS
 *   - 최장 공통 부분 수열, 두 수열이 주어졌을 때 모두의 부분 수열이 되는 가장 긴 수열.
 */
fun main() = with(System.`in`.bufferedReader()) {
    val a = readLine()
    val b = readLine()

    val arr = Array(a.length + 1) { IntArray(b.length + 1) }

    for (i in 1..a.length) {
        for (j in 1..b.length) {
            arr[i][j] = if (a[i - 1] == b[j - 1]) {
                arr[i - 1][j - 1] + 1
            } else {
                max(arr[i - 1][j], arr[i][j - 1])
            }
        }
    }
    println(arr[a.length][b.length])
}