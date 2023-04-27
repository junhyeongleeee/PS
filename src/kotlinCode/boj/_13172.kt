package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private const val P = 1_000_000_007
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val m = readLine().toInt()
    var S = 0L
    var N = 1L
    repeat(m) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val s = st.nextToken().toInt()
        S = s * N + S * n
        N *= n
        S %= P
        N %= P
    }

    if (S % N != 0L) {
        sb.append((solve13172(N, P - 2) * S) % P)
    }
    else{
        sb.append(S/N)
    }
    println(sb)
}

fun solve13172(N: Long, idx: Int): Long {
    if(idx == 1) {
        return N
    }
    val temp = solve13172(N, idx / 2)
    return if(idx % 2 == 1) {
        temp * temp % P * N % P
    }else {
        temp * temp % P
    }
}