package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  좌표 압축
 */
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(br){
    val n = readLine().toInt()
    val map = mutableMapOf<Int, Int>()
    val input = IntArray(n)
    st = StringTokenizer(readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        input[it] = num
    }
    val sorted = input.sorted()
    var index = 0
    sorted.forEach { i ->
        if (!map.containsKey(i)) {
            map[i] = index++
        }
    }
    input.forEach {
        sb.append(map[it]!!).append(" ")
    }
    println(sb)
}