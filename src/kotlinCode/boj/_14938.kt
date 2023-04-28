package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var itemList: IntArray
private lateinit var graph: Array<MutableList<Pair<Int, Int>>>

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val r = st.nextToken().toInt()

    graph = Array(n + 1) { mutableListOf() }
    itemList = IntArray(n + 1)

    st = StringTokenizer(readLine())
    repeat(n) {
        itemList[it + 1] = st.nextToken().toInt()
    }

    repeat(r) {
        st = StringTokenizer(readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val l = st.nextToken().toInt()

        graph[a].add(Pair(b, l))
        graph[b].add(Pair(a, l))
    }

    var result = Integer.MIN_VALUE
    for (i in 1..n) {
        result = max(result, solve14938(i, n, m))
    }

    println(result)
}

fun solve14938(s: Int, n: Int, m: Int): Int {
    val queue = java.util.ArrayDeque<Pair<Int, Int>>()
    val visited = BooleanArray(n + 1)
    queue.add(Pair(s, 0))
    visited[s] = true

    var total = itemList[s]

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val v = q.first
        val d = q.second

        for (node in graph[v]) {
            val nv = node.first
            val nd = node.second

            if (d + nd > m) continue

            if (!visited[nv]) {
                total += itemList[nv]
            }
            queue.add(Pair(nv, d + nd))
            visited[nv] = true
        }
    }

    return total
}