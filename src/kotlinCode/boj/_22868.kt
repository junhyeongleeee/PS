package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  산책(small)
 *
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var route: IntArray
private lateinit var visited: BooleanArray
private val queue = java.util.ArrayDeque<Pair<Int, Int>>()
private var answer = 0
fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)
    route = IntArray(n + 1)
    repeat(m) {
        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        arr[v1].add(v2)
        arr[v2].add(v1)
    }
    st = StringTokenizer(readLine())
    val s = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    for (i in arr.indices) {
        arr[i].sort()
    }

    bfs22868(s, e)
    visited = BooleanArray(n + 1)

    var v = route[e]
    while (true) {
        if (v == s) {
            break
        }
        visited[v] = true
        v = route[v]
    }

    bfs22868(e, s)
    println(answer)
}
fun bfs22868(s: Int, e: Int) {
    queue.clear()
    queue.add(Pair(s, 0))
    visited[s] = true
    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (q.first == e) {
            answer += q.second
            break
        }

        arr[q.first].forEach { v->
            if (!visited[v]) {
                queue.add(Pair(v, q.second + 1))
                visited[v] = true
                route[v] = q.first
            }
        }
    }
}