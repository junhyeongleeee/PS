package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer

class `21736` {

    lateinit var st: StringTokenizer

    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()

        val arr = Array(n) { CharArray(m) }
        val queue = ArrayDeque<Pair<Int, Int>>()
        val visited = Array(n) { BooleanArray(m) }
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)

        repeat(n) { i ->
            val line = readLine()
            line.forEachIndexed { j, c ->
                arr[i][j] = c
                if (c == 'I') {
                    queue.add(i to j)
                    visited[i][j] = true
                }
            }
        }

        var answer = 0

        while (queue.isNotEmpty()) {
            val q = queue.poll()
            val x = q.second
            val y = q.first

            for (i in 0 until 4) {
                val nx = x + dx[i]
                val ny = y + dy[i]

                if (nx < 0 || nx > m - 1 || ny < 0 || ny > n - 1) continue
                if (visited[ny][nx] || arr[ny][nx] == 'X') continue
                if (arr[ny][nx] == 'P') answer++

                visited[ny][nx] = true
                queue.add(ny to nx)
            }
        }

        println(if (answer == 0) "TT" else answer)
    }
}

fun main() {
    `21736`().solution()
}