package kotlinCode.boj

import java.util.PriorityQueue

private val br = System.`in`.bufferedReader()
private val heap = PriorityQueue<Int>()
private val sb = StringBuilder()
fun main() = with(br){
    val n = readLine().toInt()
    repeat(n) {
        val input = readLine().toInt()
        when(input) {
            0 -> {
                if (!heap.isEmpty()) {
                    sb.append(heap.poll()).append("\n")
                }
                else {
                    sb.append(0).append("\n")
                }
            }
            else -> {
                heap.add(input)
            }
        }
    }
    println(sb)
}