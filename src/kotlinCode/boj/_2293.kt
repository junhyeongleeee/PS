package kotlinCode.boj

private lateinit var dp: IntArray
private lateinit var arr: IntArray
fun main() = with(System.`in`.bufferedReader()){
    val (n, k) = readLine().split(" ").map { it.toInt() }

    arr = IntArray(n)
    dp = IntArray(k + 1)

    repeat(n) {
        arr[it] = readLine().toInt()
    }

    dp[0] = 1

    for (num in arr) {
        for (i in num .. k) {
            dp[i] += dp[i - num]
        }
    }

    println(dp[k])
}