package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer

/**
 *  bfs -> O((N + (N(N  - 1) / 2)) * (M - 1)) -> O(N^2 * M) 약 40_000 * 1000 -> 1초 미만
 *
 *  union find -> union : O(m + a(n))
 */

private lateinit var st: StringTokenizer
private val queue = ArrayDeque<Int>()
private lateinit var visited: BooleanArray
private lateinit var graph : Array<MutableList<Int>>



fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    graph = Array(n + 1) { mutableListOf() }

    repeat(n) { i ->
        st = StringTokenizer(readLine())

        val v1 = i + 1

        repeat(n) { j ->
            val connection = st.nextToken().toInt()
            val v2 = j + 1

            if (connection == 1) {
                graph[v1].add(v2)
            }
        }
    }

    val plan = IntArray(m)

    st = StringTokenizer(readLine())
    repeat(m) {
        plan[it] = st.nextToken().toInt()
    }

    var result = true
    var start = plan[0]
    for (i in 1 until m) {
        visited = BooleanArray(n + 1)
        val end = plan[i]
        if (!solve1976(start, end)) {
            result = false
            break
        }
        start = end
    }

    println(if (result) "YES" else "NO")
}

fun solve1976(s: Int, e: Int) : Boolean {

    visited[s] = true
    queue.add(s)

    while (queue.isNotEmpty()) {
        val v = queue.poll()

        if (v == e) {
            return true
        }

        for (nv in graph[v]) {
            if (visited[nv]) continue
            visited[nv] = true
            queue.add(nv)
        }
    }

    return false
}