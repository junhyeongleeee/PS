package kotlinCode.boj

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val w = st.nextToken().toDouble()

    val depth = IntArray(n + 1)

    repeat(n - 1) {
        st = StringTokenizer(readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        ++depth[u]
        ++depth[v]
    }

    var result = 0
    for (i in 2..n) {
        if (depth[i] == 1) {
            result++
        }
    }

    print(w / result)
}