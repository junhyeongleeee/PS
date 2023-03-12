package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *  최단 경로
 *  - 다익스트라, 한 노드에서 모든 노드로 가는 최단거리리 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Node>>
private lateinit var dist: IntArray
private val sb = StringBuilder()

private class Node(val v: Int, val w: Int)

private val pq = PriorityQueue<Node> { a, b -> a.w - b.w }
private const val INF = Integer.MAX_VALUE
private lateinit var visited: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val v = st.nextToken().toInt()
    val e = st.nextToken().toInt()
    val k = readLine().toInt()
    arr = Array(v + 1) { mutableListOf() }
    visited = BooleanArray(v + 1)
    dist = IntArray(v + 1) { INF }
    repeat(e) {
        st = StringTokenizer(readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        val w = st.nextToken().toInt()

        arr[u].add(Node(v, w))
    }

    dijkstra(k)

    for (i in 1..v) {
        if (dist[i] == INF) {
            sb.append("INF").append("\n")
        } else {
            sb.append(dist[i]).append("\n")
        }
    }

    println(sb)
}

private fun dijkstra(k: Int) {
    pq.add(Node(k, 0))
    dist[k] = 0

    while (!pq.isEmpty()) {
        val n = pq.poll()

        if (visited[n.v]) continue
        visited[n.v] = true

        arr[n.v].forEach { nn ->
            if (dist[nn.v] > dist[n.v] + nn.w) {
                dist[nn.v] = dist[n.v] + nn.w
                pq.add(Node(nn.v, dist[nn.v]))
            }
        }
    }
}