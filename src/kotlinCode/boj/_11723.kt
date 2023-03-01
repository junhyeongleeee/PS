package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  집합
 */
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(br){
    val m = readLine().toInt()
    val map = mutableMapOf<Int, Boolean>()
    repeat(m) {
        st = StringTokenizer(readLine())
        val op = st.nextToken()

        when(op) {
            "add" -> {
                map[st.nextToken().toInt()] = true
            }
            "check" -> {
                val answer = if (map.containsKey(st.nextToken().toInt())) {
                    1
                }else {
                    0
                }
                sb.append(answer).append("\n")
            }
            "remove" -> map.remove(st.nextToken().toInt())
            "toggle" -> {
                val x = st.nextToken().toInt()
                if (map.containsKey(x)) {
                    map.remove(x)
                }
                else {
                    map[x] = true
                }
            }
            "all" -> all(map)
            "empty" -> map.clear()
        }
    }
    println(sb)
}
fun all(map: MutableMap<Int, Boolean>) {
    repeat(20) {
        map[it + 1] = true
    }
}