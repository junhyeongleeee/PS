package kotlinCode

import java.util.StringTokenizer

/**
 *  N과 M (5)
 *
 *  1 <= M <= N <= 8
 */

private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private lateinit var result: IntArray
private lateinit var visited: BooleanArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    result = IntArray(m)
    arr = IntArray(n)
    visited = BooleanArray(n)

    st = StringTokenizer(readLine())
    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }
    arr.sort()
    solve15654(0, n, m)
    println(sb)
}

fun solve15654(cnt: Int, n: Int, m: Int) {
    if (cnt == m) {
        result.forEach { sb.append("$it ") }
        sb.append("\n")
        return
    }
    for (i in 0 until n) {
        if (!visited[i]) {
            visited[i] = true
            result[cnt] = arr[i]
            solve15654(cnt + 1, n, m)
            visited[i] = false
        }
    }
}