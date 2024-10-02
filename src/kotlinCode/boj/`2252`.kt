package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer

fun main() {
    `2252`().solution()
}
class `2252` {
    lateinit var st: StringTokenizer
    lateinit var graph: Array<MutableList<Int>>

    lateinit var enter : IntArray
    lateinit var exit : IntArray
    val sb = StringBuilder()
    val queue = ArrayDeque<Int>()

    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())

        val n = st.nextToken().toInt()              // <= 32_000
        val m = st.nextToken().toInt()              // <= 100_000

        graph = Array(n + 1) { mutableListOf() }
        enter = IntArray(n + 1)

        repeat(m) {
            st = StringTokenizer(readLine())

            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()

            graph[a].add(b)
            enter[b]++
        }

        for (i in 1 .. n) {
            if (enter[i] == 0) queue.add(i)
        }

        while (queue.isNotEmpty()) {
            val q = queue.poll()

            sb.append("$q ")

            for (v in graph[q]) {
                if (--enter[v] == 0) {
                    queue.add(v)
                }
            }
        }

        println(sb)
    }
}