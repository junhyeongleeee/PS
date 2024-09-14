package kotlinCode.boj


fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    val dp = Array(3) { LongArray(n) }

    dp[0][0] = 1
    dp[1][0] = 1
    dp[2][0] = 1

    for (i in 1 until n) {
        dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % 9901
        dp[1][i] = (dp[2][i - 1] + dp[0][i - 1]) % 9901
        dp[2][i] = (dp[1][i] + dp[1][i - 1]) % 9901
    }

    var result = 0L

    for(i in 0 until 3) {
        result = (result + dp[i][n - 1]) % 9901
    }

    print(result)
}