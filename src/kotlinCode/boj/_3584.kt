package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  가장 가까운 공통 조상
 *  - 테스트 개수 T
 *  - 노드의 수 N ( 2<= N <= 10000)
 *  - 정점 V ( 1<=V <= N)
 *
 *  - LCA(Lowest Common Ancestor) 최소 공통 조상을 찾는 알고리즘
 *
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private val sb = StringBuilder()
private var cnt = 0
private var depth1 = -1
private var depth2 = -1
fun main() = with(br) {
    val t = readLine().toInt()
    repeat(t) {
        val n = readLine().toInt()
        arr = Array(n + 1) { mutableListOf() }

        repeat(n - 1) { i ->
            st = StringTokenizer(readLine())
            val v1 = st.nextToken().toInt()
            val v2 = st.nextToken().toInt()
            arr[v1].add(v2)
        }

        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()

        var minDepth = Integer.MAX_VALUE
        var result = 0

        for (i in 1..n) {
            cnt = 0
            dfs3584(i, 0, v1, v2)
            val depth = depth1 + depth2
            if (minDepth > depth && cnt == 2) {
                minDepth = depth
                result = i
            }
        }
        sb.append(result).append("\n")
    }
    println(sb)
}

fun dfs3584(s: Int, depth: Int, v1: Int, v2: Int) {

    if (s == v1) {
        cnt++
        depth1 = depth
    }
    if (s == v2) {
        cnt++
        depth2 = depth
    }
    arr[s].forEach {
        dfs3584(it, depth + 1, v1, v2)
    }
}