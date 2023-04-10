package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *  최소비용 구하기
 */
private lateinit var st: StringTokenizer
private lateinit var graph: Array<MutableList<Pair<Int, Int>>>
private val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
    o1.second - o2.second
}
private lateinit var visited: BooleanArray
private lateinit var dist: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()

    graph = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)
    dist = IntArray(n + 1) { Integer.MAX_VALUE }

    repeat(m) {
        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        val d = st.nextToken().toInt()

        graph[v1].add(Pair(v2, d))
    }

    st = StringTokenizer(readLine())
    val s = st.nextToken().toInt()
    val e = st.nextToken().toInt()

    solve1916(s)

    println(dist[e])
}

fun solve1916(s: Int) {
    pq.add(Pair(s, 0))
    dist[s] = 0
    while (!pq.isEmpty()) {
        val q = pq.poll()
        val v = q.first
        val d = q.second

        if (visited[v]) continue
        visited[v] = true

        for (nn in graph[v]) {
            val nv = nn.first
            val nd = nn.second + d
            if (dist[nv] > nd) {
                dist[nv] = nd
                pq.add(Pair(nv, nd))
            }
        }
    }
}