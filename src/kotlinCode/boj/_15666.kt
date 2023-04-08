package kotlinCode.boj

import java.util.*

/**
 *  N과 M (12)
 */
private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private lateinit var result: IntArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = IntArray(n)
    result = IntArray(m)

    st = StringTokenizer(readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        arr[it] = num
    }
    arr.sort()

    solve15666(0, 0, n, m)

    println(sb)
}
fun solve15666(s: Int, cnt: Int, n: Int, m: Int) {
    if (cnt == m) {
        result.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
        return
    }

    var prev = 0
    for (i in s until n) {
        if (prev == arr[i]) continue

        result[cnt] = arr[i]
        prev = arr[i]
        solve15666(i, cnt + 1, n, m)
    }
}