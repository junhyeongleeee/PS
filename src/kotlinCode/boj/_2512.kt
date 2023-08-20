package kotlinCode.boj

import java.util.*

private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()          // 3 <=  <= 10,000

    arr = IntArray(n)

    st = StringTokenizer(readLine())

    repeat(n) {
        arr[it] = st.nextToken().toInt()  // 1 <=  <= 100,000
    }
    val m = readLine().toInt()          // n <=  <= 1,000,000,000

    var left = 0
    var right = arr.maxOf { it }

    if (arr.sum() <= m) {
        println(right)
    }
    else {
        // lowBound
        while (left + 1< right) {
            val mid = (left + right) / 2
            var sum = 0
            for (i in 0 until n) {
                sum += if (arr[i] < mid) arr[i] else mid
            }
            if (m >= sum) {
                left = mid
            }else {
                right = mid
            }
        }
        println(left)
    }
}