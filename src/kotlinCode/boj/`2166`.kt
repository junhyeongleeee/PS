package kotlinCode.boj

import java.util.*
import kotlin.math.abs
import kotlin.math.roundToLong

fun main() {
    `2166`().solution()
}

class `2166` {
    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()      // <= 10_000

        val arr = Array(n) { 0.0 to 0.0 }

        repeat(n) {
            st = StringTokenizer(readLine())

            val x = st.nextToken().toDouble()
            val y = st.nextToken().toDouble()

            arr[it] = x to y
        }

        // 기준점 설정
        val p = arr[0]

        val list = arr.map { it.first - p.first to it.second - p.second }

        var answer = 0.0

        for (i in 1 until n - 1) {
            val a = list[i]
            val b = list[i + 1]
            answer += abs(a.first * b.second - b.first * a.second) * 0.5
        }

//        for (i in 1 until n - 1) {
//            val a = list[i]
//            val b = list[i + 1]
//            answer += a.first * b.second
//            answer -= b.first * a.second
//        }
//
//        answer = abs(answer) * 0.5

        println(String.format("%.1f", (answer * 10.0).roundToLong() / 10.0 ))
    }
}