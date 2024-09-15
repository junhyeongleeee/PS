package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    st = StringTokenizer(readLine())

    val arr = IntArray(n)

    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    var s = 0
    var e = n - 1
    var result = Int.MAX_VALUE
    var a = 0
    var b = 0

    while (s < e) {
        val sum = arr[s] + arr[e]

        if(result > abs(sum)) {
            a = arr[s]
            b = arr[e]
            result = abs(sum)
        }

        if(sum > 0) {
            e--
        }else {
            s++
        }

    }

    println("$a $b")
}