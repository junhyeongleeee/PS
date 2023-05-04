package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var dp: Array<IntArray>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val m = st.nextToken().toInt()      // 세로
    val n = st.nextToken().toInt()      // 가로

    arr = Array(m) { IntArray(n) }
    dp = Array(m) { IntArray(n) { -1 } }

    repeat(m) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            val h = st.nextToken().toInt()
            arr[i][j] = h
        }
    }

    println(solve1520(0, 0, n, m))

}

fun solve1520(x: Int, y: Int, n: Int, m: Int): Int {

    if (x == n - 1 && y == m - 1) {
        return 1
    }
    if (dp[y][x] != -1) {
        return dp[y][x]
    }
    else {
        dp[y][x] = 0
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > n - 1 || ny > m - 1) continue

            if (arr[y][x] > arr[ny][nx]) {
                dp[y][x] += solve1520(nx, ny, n, m)
            }
        }
    }
    return dp[y][x]
}