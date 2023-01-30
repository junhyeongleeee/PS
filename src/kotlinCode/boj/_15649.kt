package kotlinCode.boj

/**
 *  N과 M(1)
 *   - 자연수 N과 M 주어진다.
 *   - 1부터 N까지 자연수 중에 중복 없이 M개를 고른 수열
 *   - (1 <= M <= N <= 8)
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var visited: BooleanArray
private lateinit var arr: IntArray

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    visited = BooleanArray(n + 1)
    arr = IntArray(m)
    permutation15649(n, m, 0)
    bw.flush()
    bw.close()
}

fun permutation15649(n: Int, m: Int, depth: Int) {
    if (depth == m) {
        bw.write(arr.joinToString(" ") + "\n")
        return
    }

    for (i in 0 until n) {
        if (!visited[i]) {
            visited[i] = true
            arr[depth] = i + 1
            permutation15649(n, m, depth + 1)
            visited[i] = false
        }
    }
}