package kotlinCode.boj

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    val dp = IntArray(n + 1)

    if (n % 2 == 1) {
        println(0)
    }else {
        dp[2] = 3

        for (i in 4..n step 2) {
            dp[i] += dp[i - 2] * 3 + 2
            for (j in i - 4 downTo 0 step 2) {
                dp[i] += dp[j] * 2
            }
        }
        println(dp[n])
    }
}