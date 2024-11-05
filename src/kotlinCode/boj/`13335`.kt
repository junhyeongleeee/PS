package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer

class `13335` {

    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()
        val w = st.nextToken().toInt()
        val l = st.nextToken().toInt()

        val arr = IntArray(n)
        val ad = ArrayDeque<Int>()

        st = StringTokenizer(readLine())
        repeat(n) {
            arr[it] = st.nextToken().toInt()
            ad.addLast(arr[it])
        }

        val bridge = ArrayDeque<Int>()
        var answer = 0

        // 현재 다리에 있는 트럭의 총 무게
        var sum = 0

        // 다리 초기화
        repeat(w) { bridge.add(0) }

        while (bridge.isNotEmpty()) {
            answer++
            sum -= bridge.removeFirst()

            // 새로운 트럭 추가
            if (ad.isEmpty()) continue

            if (sum + ad.peekFirst() <= l) {
                val track = ad.removeFirst()
                sum += track
                bridge.addLast(track)
            }else {
                bridge.addLast(0)
            }
        }

        println(answer)
    }
}

fun main() {
    `13335`().solution()
}