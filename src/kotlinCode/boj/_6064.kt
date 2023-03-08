package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  카잉 달력
 *
 *  1 <= M, N <= 40,000
 *  1 <= x <= M, 1 <= y <= N
 */
private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){

    val k = readLine().toInt()

    repeat(k) {
        st = StringTokenizer(readLine())

        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()
        val x = st.nextToken().toInt() - 1
        val y = st.nextToken().toInt() - 1
        sb.append(solve6064(x, y, n, m)).append("\n")
    }
    println(sb)
}

fun solve6064(x: Int, y: Int, n: Int, m: Int): Int {
    var cnt = x
    while (cnt < n*m) {
        if (cnt % n == y) {
            return cnt + 1
        }
        cnt += m
    }
    return -1
}