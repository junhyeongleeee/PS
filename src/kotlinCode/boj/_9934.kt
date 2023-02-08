package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.pow

/**
 *  완전 이진 트리
 *  - 입력 : 순회 번호
 *  - 출력 : 트리
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<StringBuilder>
private lateinit var graph: IntArray

fun main() = with(br) {
    val k = readLine().toInt()
    val size = (2.0.pow(k.toDouble()) - 1).toInt()

    arr = Array(k) { StringBuilder() }
    st = StringTokenizer(readLine())
    graph = IntArray(size)
    repeat(size) {
        val v = st.nextToken().toInt()
        graph[it] = v
    }

    fun dfs(l: Int, r: Int, depth: Int) {
        if (depth == k) {
            return
        }
        val mid = (l + r) / 2
        arr[depth].append(graph[mid]).append(" ")
        dfs(l, mid, depth + 1)
        dfs(mid + 1, r, depth + 1)
    }

    dfs(0, size - 1, 0)

    arr.forEach { println(it) }
}
