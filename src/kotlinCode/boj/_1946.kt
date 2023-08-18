package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()              // T <= 20

    repeat(t) {
        val n = readLine().toInt()          // n <= 100,000

        val list = mutableListOf<Pair<Int, Int>>()
        var result = 1

        repeat(n) {
            st = StringTokenizer(readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()

            list.add(Pair(a, b))
        }

        list.sortBy { it.first }
        var min = list[0].second

        for (i in 1 until list.size) {
            if (min > list[i].second) {
                result++
                min = list[i].second
            }
        }
        sb.append(result).append("\n")
    }

    println(sb)
}