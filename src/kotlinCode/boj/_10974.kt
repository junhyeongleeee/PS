package kotlinCode.boj

private lateinit var arr : IntArray
private lateinit var visited : BooleanArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()  // <= 8
    arr = IntArray(n)
    visited = BooleanArray(n)
    solve10974(0, n)
    println(sb)
}
fun solve10974(idx: Int, n: Int) {
    if (idx == n) {
        sb.append(arr.joinToString(" ")).append("\n")
        return
    }

    for (i in 0 until n) {
        if (visited[i]) continue
        arr[idx] = i + 1
        visited[i] = true
        solve10974(idx + 1, n)
        visited[i] = false
    }
}