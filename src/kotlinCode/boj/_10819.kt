package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.max

private lateinit var st: StringTokenizer
private lateinit var result : IntArray
private lateinit var arr : IntArray
private lateinit var visited: BooleanArray
private var answer = Int.MIN_VALUE
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()  // 3 <=  <= 8
    st = StringTokenizer(readLine())
    arr = IntArray(n)
    result = IntArray(n)
    visited = BooleanArray(n)

    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }
    solve10819(0, n)
    println(answer)
}
fun solve10819(cnt: Int, n: Int) {
    if (cnt == n) {
        // 계산
        var sum = 0
        for (i in 0 until n - 1) {
            sum += abs(result[i] - result[i + 1])
        }
        answer = max(answer, sum)
        return
    }

    for (i in 0 until n) {
        if (visited[i]) continue
        result[cnt] = arr[i]
        visited[i] = true
        solve10819(cnt + 1, n)
        visited[i] = false
    }
}