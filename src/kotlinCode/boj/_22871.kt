package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 *  징검다리 건너기 (large)
 *  - N 개 돌의 나열
 *  - 가장 왼쪽부터 오른쪽까지 돌로 건너가려 한다.
 *  - i 번쩨 돌에서 j(i<j)번쨰 돌로 이동할 때 (j-i) * (1 + |Ai-Aj|)만큼 힘을 쓸 수 있다.
 *  - 돌을 한번 건너갈 때마다 쓸 수 있는 힘은 최대 K
 *
 *  - 모든 경우 중 K의 최솟값
 *  - 2<= N <= 5,000
 *  - 1 <= Ai <= 1,000,000
 *
 *
 *  -
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: LongArray
private lateinit var dp: LongArray
fun main() = with(br) {
    val n = readLine().toInt()
    arr = LongArray(n)
    dp = LongArray(n) { -1L }
    st = StringTokenizer(readLine())
    repeat(n) {
        arr[it] = st.nextToken().toLong()
    }
    println(solve(0, n))
}

fun solve(index: Int, n: Int): Long {
    if (index == n - 1) return 0L
    var v = dp[index]
    if (v != -1L) return v
    v = Long.MAX_VALUE
    for (i in index + 1 until  n) {
        v = min(v, max(solve(i, n), power(i, index)))
    }
    return v
}

fun power(i: Int, j: Int) =
    (i - j) * (1 + abs(arr[j] - arr[i]))