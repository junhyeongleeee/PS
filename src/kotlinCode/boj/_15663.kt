package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  N과 M(9)
 *
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
    arr = IntArray(n)
    result = IntArray(m)
    visited = BooleanArray(n)

    st = StringTokenizer(readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        arr[it] = num
    }
    arr.sort()

    solve15663(0, n, m)

    println(sb)
}

// 1 7 9 9
fun solve15663(cnt: Int, n: Int, m: Int) {

    if (cnt == m) {
        result.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
        return
    }

    var prev = 0
    for (i in 0 until n) {
        if (visited[i]) continue
        if (prev == arr[i]) continue

        result[cnt] = arr[i]
        prev = arr[i]
        visited[i] = true
        solve15663(cnt + 1, n, m)
        visited[i] = false
    }
}