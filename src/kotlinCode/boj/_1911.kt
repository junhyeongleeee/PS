package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.ceil


private lateinit var st: StringTokenizer

data class Hole(val s: Int, val e: Int)

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()          // <= 10_000
    val l = st.nextToken().toInt()          // <= 1_000_000

    val list = mutableListOf<Hole>()

    repeat(n) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()      // <= 10^9
        val e = st.nextToken().toInt()

        list.add(Hole(s, e))
    }

    list.sortBy { it.s }

    var ns = list[0].s
    var count = 0

    for (h in list) {
        var s = h.s
        val e = h.e
        if (ns > s) {
            s = ns
        }
//        println("s: $s e: $e count: $count")

        val c = ceil((e - s).toDouble() / l).toInt()

        count += c
        ns = s + (l * c)
    }

    println(count)
}