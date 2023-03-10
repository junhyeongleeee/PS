package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  구간 합 구하기4
 */

private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private lateinit var sum: IntArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    arr = IntArray(n + 1)
    sum = IntArray(n + 1)
    st = StringTokenizer(readLine())
    for (i in 1 .. n) {
        val n = st.nextToken().toInt()
        arr[i] = n
        sum[i] += sum[i - 1] + n
    }
    repeat(m) {
        st = StringTokenizer(readLine())
        val i = st.nextToken().toInt()
        val j = st.nextToken().toInt()
        sb.append(sum[j] - sum[i - 1]).append("\n")
    }
    println(sb)
}