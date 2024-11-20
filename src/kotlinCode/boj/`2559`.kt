package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

class `2559` {

    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())

        val n = st.nextToken().toInt()          // <= 100_000
        val k = st.nextToken().toInt()

        val arr = IntArray(n)

        st = StringTokenizer(readLine())

        repeat(n) {
            arr[it] = st.nextToken().toInt()
        }

        var answer = 0
        var sum = 0

        for (i in 0 until k) {
            sum += arr[i]
        }

        answer = sum
        for (i in k until n) {
            sum += arr[i] - arr[i - k]
            answer = max(answer, sum)
        }

        println(answer)
    }
}

fun main() {
    `2559`().solution()
}