package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  트리의 부모 찾기
 *
 *  - 루트 없는 트리가 주어진다.
 *  - 트리의 루트를 1이라 했을 때, 각 노드의 부모를 구한다.
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var visited: BooleanArray
private lateinit var result: IntArray
private val sb = StringBuilder()

fun main() = with(br) {
    val n = readLine().toInt()

    arr = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)
    result = IntArray(n + 1)

    repeat(n - 1) {
        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        arr[v1].add(v2)
        arr[v2].add(v1)
    }

    dfs11725(1)
    for (i in 2 .. n) {
        sb.append(result[i]).append('\n')
    }
    println(sb)
}
fun dfs11725(s: Int) {
    visited[s] = true
    arr[s].forEach { v ->
        if (!visited[v]) {
            result[v] = s
            dfs11725(v)
        }
    }
}