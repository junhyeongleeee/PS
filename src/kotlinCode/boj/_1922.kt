package kotlinCode.boj

import java.util.*

private lateinit var st: StringTokenizer
private val pq = PriorityQueue<Node1922> { a, b -> a.w - b.w }
private lateinit var parent: IntArray
private var answer = 0

data class Node1922(val a: Int, val b: Int, val w: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()      // <= 1000
    val m = readLine().toInt()      // <= 100,000

    parent = IntArray(n + 1) { i -> i }

    repeat(m) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()      // 비용

        pq.add(Node1922(a, b, c))
    }

    while (pq.isNotEmpty()) {
        val node = pq.poll()

        // 그룹인지 확인
        if (isSameParent(node.a, node.b)) continue

        // 그룹 매핑
        grouping(node.a, node.b)

        answer += node.w
    }
    println(answer)
}

fun isSameParent(a: Int, b: Int) = findParent1922(a) == findParent1922(b)

fun findParent1922(a: Int): Int {
    if (a != parent[a]) parent[a] = findParent1922(parent[a])
    return parent[a]
}

fun grouping(a: Int, b: Int) {
    val pa = findParent1922(a)
    val pb = findParent1922(b)
    if (pa != pb) {
        parent[pb] = pa
    }
}
