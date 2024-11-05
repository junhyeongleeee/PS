package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer

class `2477` {
    lateinit var st: StringTokenizer


    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val ad = ArrayDeque<Pair<Int, Int>>()
        val count = IntArray(4)

        repeat(6) {
            st = StringTokenizer(readLine())
            val v = st.nextToken().toInt()
            val d = st.nextToken().toInt()

            ad.addLast(v - 1 to d)
            count[v - 1]++
        }

        while (true) {
            val data = ad.peekFirst()

            if(count[data.first] == 1) break

            // shift
            ad.addLast(ad.removeFirst())
        }

        val arr = ad.toList()

        var area = 1
        val longDistance = mutableListOf<Int>()
        for (i in 0 until 6) {
            if (count[arr[i].first] == 1) {
                area *= arr[i].second
                longDistance.add(arr[i].first)
            }
        }

//        println(area)

        val list = arr.filter { it.first !in longDistance }

        area -= (list[1].second * list[2].second)

        println(area * n)
    }
}

fun main() {
    `2477`().solution()
}