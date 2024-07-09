package kotlinCode.boj
import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()                  // <= 100
    val k = st.nextToken().toInt()                  // <= 100_000

    val dp = Array(n + 1) { IntArray(k + 1) }

    for (i in 1..n) {
        st = StringTokenizer(readLine())
        val wt = st.nextToken().toInt()              // <= 10_000
        val wc = st.nextToken().toInt()             // <= 1_000_000
        val ct = st.nextToken().toInt()
        val cc = st.nextToken().toInt()

        if (i == 1) {
            dp[1][wt] = wc
            dp[1][ct] = max(dp[1][ct], cc)
        }

        for (j in 0..k) {
            // dp[i][j + wt] 가 0일 때 dp[i - 1][j]가 0인경우 dp[i][j + wt]는 무조건 wc가 되어버리기 때문에 예외 처리가 필요함.
            if (dp[i - 1][j] == 0) continue
            if (j + wt <= k) {
                dp[i][j + wt] = max(dp[i][j + wt], dp[i - 1][j] + wc)
            }
            if (j + ct <= k) {
                dp[i][j + ct] = max(dp[i][j + ct], dp[i - 1][j] + cc)
            }
        }
    }
    println(dp[n].maxOf { it })
}