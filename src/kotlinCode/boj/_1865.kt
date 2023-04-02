package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  웜홀
 *  1 <= TC <= 5
 *  1 <= N <= 500
 *  1 <= M <= 2500
 *  1 <= W <= 200
 *  0 <= T <= 10000
 */
private lateinit var st: StringTokenizer
private lateinit var dist: IntArray
private val sb = StringBuilder()

data class Edge(val s: Int, val e: Int, val d: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val tc = readLine().toInt()
    repeat(tc) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val m = st.nextToken().toInt()
        val w = st.nextToken().toInt()

        dist = IntArray(n + 1) { 10_000 * 500 }
        val edges = mutableListOf<Edge>()
        // s, e: 연결된 지점의 번호
        // t: 도로를 이동하는데 걸리는 시간
        // 도로의 정보
        repeat(m) {
            st = StringTokenizer(readLine())
            val s = st.nextToken().toInt()
            val e = st.nextToken().toInt()
            val t = st.nextToken().toInt()
            edges.add(Edge(s, e, t))
            edges.add(Edge(e, s, t))
        }
        // 웜홀의 정보
        repeat(w) {
            st = StringTokenizer(readLine())
            val s = st.nextToken().toInt()
            val e = st.nextToken().toInt()
            val t = st.nextToken().toInt()
            edges.add(Edge(s, e, -t))
        }

        if (solve1865(n, edges)) {
            sb.append("YES").append("\n")
        } else {
            sb.append("NO").append("\n")
        }
    }
    println(sb)
}

fun solve1865(v: Int, edges: MutableList<Edge>): Boolean {
    dist[1] = 0
    repeat(v) {
        edges.forEach { e ->
            if (dist[e.s] != Integer.MAX_VALUE &&
                dist[e.e] > dist[e.s] + e.d
            ) {
                dist[e.e] = dist[e.s] + e.d
                if (it == v - 1) {
                    return true
                }
            }
        }
    }
    return false
}
