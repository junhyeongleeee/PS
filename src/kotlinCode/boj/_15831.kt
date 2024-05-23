package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max


private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()
    val b = st.nextToken().toInt()
    val w = st.nextToken().toInt()

    val l = readLine()
    var h = 0           // head
    var t = 0           // tail

    var bCount = 0
    var wCount = 0
    var result = 0

    while (t < n) {
        when (l[t]) {
            'B' -> bCount++
            'W' -> wCount++
        }

        if (bCount > b) {
            when (l[h]) {
                'B' -> bCount--
                'W' -> wCount--
            }
            h++
        } else {
            if (wCount >= w) {
                result = max(result, t - h + 1)
            }
        }
        t++
    }
    println(result)
}