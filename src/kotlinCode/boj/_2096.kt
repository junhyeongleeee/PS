package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/**
 *  내려가기
 *
 *  1 <= N <= 100,000
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var score: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(3) }
    score = Array(3) { IntArray(2) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(3) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    for (i in 0 until 3) {
        score[i][0] = arr[0][i]
        score[i][1] = arr[0][i]
    }

    for (i in 1 until n) {
        var ps1 = score[0][0]
        var ps2 = score[1][0]
        score[0][0] = min(score[1][0], score[0][0]) + arr[i][0]
        score[1][0] = min(score[2][0], min(score[1][0], ps1)) + arr[i][1]
        score[2][0] = min(ps2, score[2][0]) + arr[i][2]

//        println("1: ${score[0][0]} 2: ${score[1][0]} 3: ${score[2][0]}")

        ps1 = score[0][1]
        ps2 = score[1][1]
        score[0][1] = max(score[1][1], score[0][1]) + arr[i][0]
        score[1][1] = max(score[2][1], max(score[1][1], ps1)) + arr[i][1]
        score[2][1] = max(score[2][1], ps2) + arr[i][2]

//        println("1: ${score[0][1]} 2: ${score[1][1]} 3: ${score[2][1]}")
//        println()
    }

    var max = Integer.MIN_VALUE
    var min = Integer.MAX_VALUE
    for (i in 0 until 3) {
        min = min(min, score[i][0])
        max = max(max, score[i][1])
    }

    println("$max $min")
}