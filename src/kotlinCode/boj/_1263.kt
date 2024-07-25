package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  풀이 : [이분탐색]
 *      (T0, S0), (T1, S1) ... (Tn, Sn) -> S 순으로 정렬
 *
 *      Tn-1, Sn-1 이 불가능하다면 Tn, Sn도 불가능 하기 때문
 *
 *      가장 늦은 시간의 최대 <= 1_000_000 - 1_000
 *      n <= 1_000
 *
 *      1_000_000 * 1_000 -> 시간 초과
 *      따라서
 *
 *      0 부터 최대시간에서 이분탐색으로 가능한 시간대를 찾는다.
 */

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()                      // <= 1_000

    val arr = Array(n) { Pair(0, 0) }

    repeat(n) {
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()              // <= 1_000
        val s = st.nextToken().toInt()              // <= 1_000_000

        arr[it] = t to s
    }

    arr.sortBy { it.second }

    var l = 0
    var r = arr[0].second - arr[0].first

    while (l <= r) {
        val mid = (l + r) / 2
        var sum = mid
        var flag = true
        for (i in 0 until n) {
            sum += arr[i].first
            if (arr[i].second < sum) {
                flag = false
                break
            }
        }
        if (flag) {
            l = mid + 1
        } else {
            r = mid
        }
    }
    println(l - 1)
}