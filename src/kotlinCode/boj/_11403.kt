package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  경로 찾기
 */
private lateinit var arr: Array<IntArray>
private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    arr = Array(n) { IntArray(n) }
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (arr[i][k] == 1 && arr[k][j] == 1) {
                    arr[i][j] = 1
                }
            }
        }
    }

    arr.forEach { println(it.joinToString(" ")) }
}