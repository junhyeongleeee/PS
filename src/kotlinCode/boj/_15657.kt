package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  N과 M (8)
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
        arr[it] = st.nextToken().toInt()
    }

    arr.sort()

    solve15657(0, 0, n, m)

    println(sb)
}
fun solve15657(s: Int, cnt: Int, n: Int, m: Int) {
    if (cnt == m) {
        result.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
        return
    }

    for (i in s until n) {
        result[cnt] = arr[i]
        solve15657(i, cnt + 1, n, m)
    }
}