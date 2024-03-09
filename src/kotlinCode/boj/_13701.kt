package kotlinCode.boj

import java.util.StringTokenizer


/**
 *  -2^15 < short < 2^15    2byte
 *  -2^31 < int < 2^31      4byte
 *
 *  1KB = 1024 Byte
 *  1MB = 1024 KB
 *  128MB = 128 * 1024 * 1024 / 4 (Int 형) = 33554232
 *
 *
 *  0 <= Ai < 2^25 = 33554232
 *
 *  1 <= N <= 5_000_000
 *
 *  5_000_000 > 2_097_152 -> 정수 A들을 배열에 담을 수 없음.
 */

private lateinit var st: StringTokenizer
private val set = linkedSetOf<Int>()
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())

    while (st.hasMoreTokens()) {
        val n = st.nextToken().toInt()
        set.add(n)
    }

    set.forEach {
        sb.append("$it ")
    }
    println(sb)
}