package kotlinCode.boj

/**
 *  1 <= n <= 1,000,000
 *  2N + 1 <= M <= 1,000,000
 */
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val m = readLine().toInt()
    val s = readLine()
    val dp = IntArray(s.length)
    var answer = 0
    for (i in 1 until s.length - 1) {
        if (s[i] == 'O' && s[i + 1] == 'I') {
            dp[i + 1] = dp[i - 1] + 1
            val pi = i - 2 * n + 1
            if (pi >= 0 && s[pi] == 'I' && dp[i + 1] >= n) {
                answer++
            }
        }
    }
    println(answer)
}