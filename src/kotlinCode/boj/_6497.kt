package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var parent: IntArray
private val sb = StringBuilder()

data class Edge6497(val from: Int, val to: Int, val weight: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    while (st.hasMoreTokens()) {
        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()

        parent = IntArray(m) { it }
        val edges = mutableListOf<Edge6497>()
        var sum = 0

        repeat(n) {
            st = StringTokenizer(readLine())

            // x번 집과 y번 집 사이에 양방향 도로가 있으며 거리는 z미터
            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()
            val z = st.nextToken().toInt()
            sum += z

            edges.add(Edge6497(x, y, z))
        }

        edges.sortBy { it.weight }
        var result = 0

        for (e in edges) {
            if (find6497(e.to) == find6497(e.from)) continue
            result += e.weight
            union6497(e.to, e.from)
        }
        if (m == 0 && n == 0) break
        st = StringTokenizer(readLine())
        sb.append(sum - result).appendLine()
    }
    println(sb)
}

fun union6497(to: Int, from: Int) {
    val a = find6497(to)
    val b = find6497(from)
    parent[a] = b
}

fun find6497(v: Int) : Int {
    if (parent[v] == v) return v
    parent[v] = find6497(parent[v])
    return parent[v]
}