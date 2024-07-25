package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  풀이 :
 *      슬라이딩 윈도우
 *
 *      100_000 * 100_000 -> 브루트 포스 x
 *
 *      - 누적 합으로 100_000 번 확인해야 하기 때문에 투 포인트를 활용
 */

private lateinit var st: StringTokenizer
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    repeat(t) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()              // <= 100_000
        val m = st.nextToken().toInt()              // <= N
        val k = st.nextToken().toInt()              // <= 1_000_000_000

        val arr = IntArray(n)
        st = StringTokenizer(readLine())
        repeat(n) { i ->
            arr[i] = st.nextToken().toInt()         // 1 <= <= 10_000
        }

        var sum = arr.slice(0..m - 1).sum()
        var s = 0
        var e = m - 1
        var count = 0

        // 코너 케이스
        if (n == m && sum < k) {
            sb.appendLine(1)
            return@repeat
        }

        for (i in 0 until  n) {
            if (sum < k) count++
            e = (e + 1) % n
            sum += (arr[e] - arr[s++])
        }
        sb.appendLine(count)
    }
    println(sb)
}