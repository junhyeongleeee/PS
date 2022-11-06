package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.max

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var visited: BooleanArray
private lateinit var arr: Array<MutableList<Int>>
private lateinit var cntArray: IntArray
private lateinit var st: StringTokenizer

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n + 1) { mutableListOf() }

    repeat(m) {
        st = StringTokenizer(readLine())
        val v1 = st.nextToken().toInt()
        val v2 = st.nextToken().toInt()
        arr[v2].add(v1)
    }

    cntArray = IntArray(n + 1)

    var max = 0
    repeat(m) {
        visited = BooleanArray(n + 1)
        cntArray[it + 1] = dfs(it + 1, 0)
        max = max(cntArray[it + 1], max)
    }

    cntArray.forEachIndexed { index, i ->
        if (max == i) {
            bw.write("$index ")
        }
    }

    bw.flush()
    bw.close()
}

fun dfs(s: Int, sum: Int): Int {
    var result = sum
    visited[s] = true
    arr[s].forEach {
        if (!visited[it]) {
            result = dfs(it, sum + 1)
        }
    }
    return result
}