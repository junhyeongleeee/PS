package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  곱셈
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val a = st.nextToken().toLong()
    val b = st.nextToken().toLong()
    val c = st.nextToken().toLong()
    println(solve1629(a, b, c))
}

fun solve1629(a: Long, ex: Long, c: Long): Long {
    if (ex == 1L) {
        return a % c
    }
    val t = solve1629(a, ex / 2, c)

    if (ex % 2 == 1L) {
        return (t * t % c) * a % c
    }
    return t * t % c
}