package kotlinCode.boj

import java.util.StringTokenizer

fun main() {
    `12015`().solution()
}
class `12015` {
    lateinit var st: StringTokenizer
    lateinit var LIS: IntArray
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()                  // <= 1_000_000

        st = StringTokenizer(readLine())

        val arr = IntArray(n)
        LIS = IntArray(n) { Int.MAX_VALUE }

        repeat(n) {
            arr[it] = st.nextToken().toInt()
        }

        var answer = 0

        for (i in 0 until n) {

            val low = func1(0, answer, arr[i])
            if (low == -1) continue
            if (low == answer) answer++

            LIS[low] = arr[i]
        }

        println(answer)
    }

    fun func1(l : Int, h: Int, target: Int) : Int {
        var low = l
        var high = h
        while (low <= high) {
            val mid = (low + high) / 2

            if (LIS[mid] < target) {
                low = mid + 1
            }else if(LIS[mid] > target) {
                high = mid - 1
            }else {
                return -1
            }
        }

        return low
    }
}