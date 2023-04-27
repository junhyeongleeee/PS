package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *
 */
private lateinit var st: StringTokenizer
private lateinit var dist: IntArray
private lateinit var result: IntArray
private lateinit var visited: BooleanArray
private lateinit var graph: Array<MutableList<P11779>>
private val sb = StringBuilder()
private val pq = PriorityQueue<P11779> { o1, o2 ->
    o1.d - o2.d
}

data class P11779(val v: Int, val d: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    graph = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)
    dist = IntArray(n + 1) { Integer.MAX_VALUE }
    result = IntArray(n + 1)

    repeat(m) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val d = st.nextToken().toInt()

        graph[s].add(P11779(e, d))
    }

    st = StringTokenizer(readLine())
    val s = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    solve11779(s)

    sb.append(dist[e]).append("\n")

    var idx = e
    var size = 0
    val stack = java.util.ArrayDeque<Int>()
    stack.push(idx)
    while (result[idx] != 0) {
        idx = result[idx]
        stack.push(idx)
        size++
    }

    sb.append(stack.size).append("\n")

    while (!stack.isEmpty()) {
        sb.append(stack.pop()).append(" ")
    }

    println(sb)
}

fun solve11779(s: Int) {
    pq.add(P11779(s, 0))
    dist[s] = 0

    while (!pq.isEmpty()) {
        val q = pq.poll()
        val v = q.v
        val d = q.d

        if (visited[v]) continue
        visited[v] = true

        for (p in graph[v]) {
            val nv = p.v
            val nd = p.d

            if (dist[nv] > d + nd) {
                dist[nv] = d + nd
                result[nv] = v
                pq.add(P11779(nv, d + nd))
            }
        }
    }
}