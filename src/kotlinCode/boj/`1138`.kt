package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer

class `1138` {
    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()              // <= 10
        st = StringTokenizer(readLine())

        val arr = IntArray(n)
        val list = LinkedList<Int>()

        repeat(n) {
            arr[it] = st.nextToken().toInt()
        }

        for (i in n - 1 downTo  0) {
            list.add(arr[i], i + 1)
        }

        println(list.joinToString(" "))
    }
}

fun main() {
    `1138`().solution()
}