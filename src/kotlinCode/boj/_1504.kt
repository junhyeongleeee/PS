package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/**
 *  특정한 최단 경로
 *
 *  2 <= N <= 800
 *  0 <= E <= 200,000
 *
 */
private lateinit var st: StringTokenizer
private lateinit var graph: Array<MutableList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
private lateinit var dist: IntArray
private lateinit var pq: PriorityQueue<Pair<Int, Int>>
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    graph = Array(n + 1) { mutableListOf() }

    repeat(e) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        graph[a].add(Pair(b, c))
        graph[b].add(Pair(a, c))
    }
    st = StringTokenizer(readLine())
    val v1 = st.nextToken().toInt()
    val v2 = st.nextToken().toInt()

    if (e == 0) {
        println(-1)
        return@with
    }
    // v1 -> v2
    val mid = solve1504(n, v1, v2)


    // 1 -> v1
    val a = solve1504(n, 1, v1)
    // v2 -> n
    val b = solve1504(n, v2, n)
    // 1 -> v2
    val c = solve1504(n, 1, v2)
    // v1 -> n
    val d = solve1504(n, v1, n)

    val result = min(a + b, c + d) + mid

    if (result >= Integer.MAX_VALUE) {
        println(-1)
    }
    else {
        println(result)
    }
}

fun solve1504(n: Int, s: Int, e: Int): Int {
    pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
        o1.second - o2.second
    }
    visited = BooleanArray(n + 1)
    dist = IntArray(n + 1) { Integer.MAX_VALUE }
    pq.add(Pair(s, 0))
    dist[s] = 0

    while (!pq.isEmpty()) {
        val p = pq.poll()
        val v = p.first
        val d = p.second

        if (visited[v]) continue
        visited[v] = true

        for (nn in graph[v]) {
            val nv = nn.first
            val nd = nn.second
            if (dist[nv] > d + nd) {
                dist[nv] = d + nd
                pq.add(Pair(nv, d + nd))
            }
        }
    }
    return dist[e]
}