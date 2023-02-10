package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  가장 가까운 공통 조상
 *  - 테스트 개수 T
 *  - 노드의 수 N ( 2<= N <= 10000)
 *  - 정점 V ( 1<=V <= N)
 *
 *  - LCA(Lowest Common Ancestor) 최소 공통 조상을 찾는 알고리즘
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private val sb = StringBuilder()
private lateinit var parent: IntArray
private lateinit var depth: IntArray
private lateinit var rootCheck: BooleanArray

fun main() = with(br) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        arr = Array(n + 1) { mutableListOf() }
        parent = IntArray(n + 1)
        depth = IntArray(n + 1)
        rootCheck = BooleanArray(n + 1)
        repeat(n - 1) {
            st = StringTokenizer(readLine())
            val v1 = st.nextToken().toInt()
            val v2 = st.nextToken().toInt()
            arr[v1].add(v2)
            rootCheck[v2] = true
            parent[v2] = v1
        }
        val root = findRoot()
        init(root, 0)

        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()

        sb.append(LCA(v1, v2)).append("\n")
    }
    println(sb)
}

fun findRoot(): Int {
    for (i in 1 until rootCheck.size) {
        if (!rootCheck[i]) {
            return i
        }
    }
    return -1
}

fun init(r: Int, d: Int) {
    depth[r] = d
    arr[r].forEach {
        init(it, d + 1)
    }
}

fun LCA(a: Int, b: Int): Int {
    var ah = depth[a]
    var bh = depth[b]
    var tA = a
    var tB = b
    while (ah > bh) {
        tA = parent[tA]
        ah--
    }
    while (bh > ah) {
        tB = parent[tB]
        bh--
    }
    while (tA != tB) {
        tA = parent[tA]
        tB = parent[tB]
    }
    return tA
}

