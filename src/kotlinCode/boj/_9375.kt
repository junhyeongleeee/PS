package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  패션왕 신해빈
 *
 *  0 <= n <= 30
 *
 */
private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    repeat(t) { i ->
        val n = readLine().toInt()
        val map = hashMapOf<String, Int>()
        repeat(n) {
            st = StringTokenizer(readLine())
            val name = st.nextToken()
            val figure = st.nextToken()
            map[figure] = map.getOrDefault(figure, 0) + 1
        }
        var result = 1
        map.forEach { t, u ->
            result *= (u + 1)
        }
        sb.append(result - 1).append("\n")
    }
    println(sb)
}