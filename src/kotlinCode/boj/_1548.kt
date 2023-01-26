package kotlinCode.boj

import util.combination
import java.util.Collections.max
import java.util.StringTokenizer

/**
 *
 *   2 3 4 1 3 4 5
 *   - 1 2 3 4 5
 */

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()
private lateinit var arr: IntArray
private lateinit var st: StringTokenizer

fun main() = with(br) {
    val n = readLine().toInt()

    st = StringTokenizer(readLine())
    arr = IntArray(n)

    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    arr.sort()

    var result = 1

    for (first in 0 until n - 1) {
        for (third in n - 1 downTo 0) {
            if (first + 1 == third) break
            if (arr[first] + arr[first + 1] > arr[third]) {
                result = result.coerceAtLeast(third - first + 1)
                break
            }
        }
    }
    if (result == 1 && n >= 2) {
        result = 2;
    }
    println(result)
}