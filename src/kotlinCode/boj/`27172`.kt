package kotlinCode.boj

import java.util.StringTokenizer

fun main() {
    `27172`().solution()
}
class `27172` {
    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        st = StringTokenizer(readLine())
        val dp = IntArray(1_000_001)
        val set = mutableSetOf<Int>()
        val arr = IntArray(n)

        repeat(n) {
            arr[it] = st.nextToken().toInt()
            set.add(arr[it])
        }

        for (v in arr) {
            var i = 2
            while (v * i <= 1_000_000) {
                if (set.contains(v * i)) {
                    dp[v]++
                    dp[v*i]--
                }
                i++
            }
        }

        for (v in arr) {
            print("${dp[v]} ")
        }
    }
}