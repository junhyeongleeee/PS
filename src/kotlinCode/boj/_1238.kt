package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max

/**
 *  파티
 *
 *  - 시작점과 끝점이 같은 도로는 없다.
 *  - 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
 *
 *  다익스트라
 *
 *
 *  1 <= N <= 1,000
 *  1 <= M <= 10,000
 *  1 <= T <= 100
 */
private lateinit var st: StringTokenizer
private lateinit var graph: Array<MutableList<Pair<Int, Int>>>
private lateinit var rGraph: Array<MutableList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
private lateinit var time: IntArray
private lateinit var rTime: IntArray
private lateinit var pq : PriorityQueue<Pair<Int, Int>>

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val x = st.nextToken().toInt()

    graph = Array(n + 1) { mutableListOf() }
    rGraph = Array(n + 1) { mutableListOf() }
    time = IntArray(n + 1) { Integer.MAX_VALUE }
    rTime = IntArray(n + 1) { Integer.MAX_VALUE }

    repeat(m) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val t = st.nextToken().toInt()
        graph[s].add(Pair(e, t))
        rGraph[e].add(Pair(s, t))
    }

    solve1238(n, x, graph, time)
    solve1238(n, x, rGraph, rTime)

    var result = 0
    for (i in 1..n) {
        result = max(result, time[i] + rTime[i])
    }
    println(result)
}


fun solve1238(n: Int, x: Int, graph: Array<MutableList<Pair<Int, Int>>>, time: IntArray) {
    visited = BooleanArray(n + 1)
    pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
        o1.second - o2.second
    }
    pq.add(Pair(x, 0))
    time[x] = 0

    while (!pq.isEmpty()) {
        val node = pq.poll()
        val v = node.first
        val t = node.second

        if (visited[v]) continue
        visited[v] = true

        for (nn in graph[v]) {
            val nv = nn.first
            val nt = nn.second
            if (time[nv] > t + nt) {
                time[nv] = t + nt
                pq.add(Pair(nv, t + nt))
            }
        }
    }
}