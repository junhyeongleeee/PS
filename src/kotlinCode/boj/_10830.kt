package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  행렬 제곱
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var result: Array<IntArray>
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val b = st.nextToken().toLong()

    arr = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt() % 1000
        }
    }

    result = solve10830(b, arr, n)

    for (i in 0 until n) {
        for (j in 0 until n) {
            sb.append(result[i][j]).append(" ")
        }
        sb.append("\n")
    }
    println(sb)
}

fun multiplyMatrix(a: Array<IntArray>, b: Array<IntArray>, n: Int): Array<IntArray> {
    val result = Array(n) { IntArray(n) }

    for (i in 0 until n) {
        for (j in 0 until n) {
            for (k in 0 until n) {
                result[i][j] += a[i][k] * b[k][j]
                result[i][j] %= 1000
            }
        }
    }

//    result.forEach { println(it.joinToString(" ")) }

    return result
}

fun solve10830(ex: Long, a: Array<IntArray>, n: Int): Array<IntArray> {

    if (ex == 1L) {
        return a
    }

    var result = solve10830(ex / 2, a, n)

    // 제곱
    result = multiplyMatrix(result, result, n)

    if (ex % 2 == 1L) {
        result = multiplyMatrix(result, arr, n)
    }

    return result
}