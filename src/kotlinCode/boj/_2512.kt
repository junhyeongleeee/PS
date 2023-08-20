package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private var answer = Int.MIN_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()          // 3 <=  <= 10,000

    arr = IntArray(n)

    st = StringTokenizer(readLine())
    repeat(n) {
        val v = st.nextToken().toInt()  // 1 <=  <= 100,000
        arr[it] = v
    }
    val m = readLine().toInt()          // n <=  <= 1,000,000,000

    arr.sortDescending()

    val diff = arr.sum() - m
    if (diff <= 0) {
        println(arr[0])
    } else {
        for (i in 0 until n) {
            var sum = 0
            val min = arr[i]
            for (j in 0 .. i) {
                sum += arr[j]
            }
            val result = (sum - diff) / (i + 1)
            if (min < result) {
                break
            }
            answer = max(answer, result)
        }
        println(answer)
    }
}