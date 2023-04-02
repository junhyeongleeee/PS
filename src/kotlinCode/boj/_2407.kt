package kotlinCode.boj

import java.math.BigInteger
import java.util.StringTokenizer

/**
 *  조합
 *
 *
 */
private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    var rp = BigInteger.ONE
    for (i in 1 .. m) {
        rp = rp.multiply(BigInteger(i.toString()))
    }

    var result = BigInteger.ONE
    for (i in (n - m + 1) .. n) {
        result = result.multiply(BigInteger(i.toString()))
    }

    println(result.divide(rp))
}