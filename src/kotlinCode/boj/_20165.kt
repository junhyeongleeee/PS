package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, r) = readLine().split(" ").map { it.toInt() }

    val arr = Array(n) { IntArray(m) }
    val check = Array(n) { CharArray(m) { 'S' } }        // false : S , true : F

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    var cnt = 0

    for (i in 0 until r * 2) {
        st = StringTokenizer(readLine())
        val y = st.nextToken().toInt() - 1
        val x = st.nextToken().toInt() - 1
        if (i % 2 == 0) {
            val z = st.nextToken()          // E, W, S, N

            val idx = when (z) {
                "E" -> 1
                "W" -> 0
                "S" -> 3
                "N" -> 2
                else -> 0
            }
            var d = arr[y][x]
            var nx = x
            var ny = y

            // 넘어져 있으면 아무일도 일어나지 않음
            if (check[ny][nx] == 'F') continue

            // 높이가 1일 경우 넘어뜨리고 종료
            if (d == 1) {
                cnt++
                check[ny][nx] = 'F'
                continue
            }


            // 도미노 쓰러뜨리기
            while (--d > 0 && nx >= 0 && nx < m && ny >= 0 && ny < n) {
                // 서있을 경우에만 높이 다시 설정
                if (check[ny][nx] == 'S') {
                    cnt++
                    check[ny][nx] = 'F'
                    d = max(d, arr[ny][nx])
                }
                nx += dx[idx]
                ny += dy[idx]
            }
        }
        // 도미노 세우기
        else {
            check[y][x] = 'S'
        }
    }

    println(cnt)
    check.forEach {
        println(it.joinToString(" "))
    }
}