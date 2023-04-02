package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  구간 합 구하기5
 *
 *  1 <= N <= 1024
 *  1 <= M <= 100,000
 *
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var sumArr: Array<IntArray>
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt() + 1
    val m = st.nextToken().toInt()

    arr = Array(n) { IntArray(n) }
    sumArr = Array(n) { IntArray(n) }

    repeat(n - 1) { i ->
        st = StringTokenizer(readLine())
        repeat(n - 1) { j ->
            arr[i + 1][j + 1] = st.nextToken().toInt()
        }
    }

    solve11660(n)

    repeat(m) {
        st = StringTokenizer(readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()

        sb.append(
            sumArr[x2][y2] - sumArr[x1 - 1][y2] - sumArr[x2][y1 - 1] + sumArr[x1 - 1][y1 - 1]
        ).append("\n")
    }

    println(sb)
}

fun solve11660(n: Int) {
    for (i in 1 until n) {
        for (j in 1 until n) {
            sumArr[i][j] = arr[i][j] + sumArr[i - 1][j] +
                    sumArr[i][j - 1] - sumArr[i - 1][j - 1]
        }
    }
}