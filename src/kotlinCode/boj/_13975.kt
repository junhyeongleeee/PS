package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()

    repeat(t) {
        val k = readLine().toInt()
        st = StringTokenizer(readLine())
        val pq = PriorityQueue<Long>()
        var result = 0L
        repeat(k) {
            pq.add(st.nextToken().toLong())
        }
        while (pq.size > 1) {
            val sum = pq.poll() + pq.poll()
            result += sum
            pq.add(sum)
        }
        sb.append("$result\n")
    }

    println(sb)
}