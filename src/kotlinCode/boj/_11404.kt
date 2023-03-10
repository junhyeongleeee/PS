package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

/**
 *  플로이드
 *
 *  2 <= n <= 100
 *  1 <= m <= 100,000
 *
 *  i 번째 줄에 출력하는 j번째 숫자는
 *  i 에서 j로 가는데 필요한 최소 비용
 *
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    arr = Array(n) { IntArray(n) }
    repeat(m) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()
        val v = arr[a - 1][b - 1]
        arr[a - 1][b - 1] = if (v != 0) min(v, c) else c
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j || arr[i][k] == 0 || arr[k][j] == 0) continue
                arr[i][j] = if (arr[i][j] == 0) {
                    arr[i][k] + arr[k][j]
                } else {
                    min(arr[i][j], arr[i][k] + arr[k][j])
                }
            }
        }
    }

    arr.forEach {
        it.forEach {
            sb.append(it).append(" ")
        }
        sb.append("\n")
    }
    println(sb)
}