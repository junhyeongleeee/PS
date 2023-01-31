package kotlinCode.boj

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var arr: IntArray

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)
    dfs15650(n, m, 0, 0)
    bw.flush()
    bw.close()
}

fun dfs15650(n: Int, m: Int, s: Int, depth: Int) {
    if (depth == m) {
        bw.write(arr.joinToString(" ") + "\n")
        return
    }

    for (i in s until n) {
        arr[depth] = i + 1
        dfs15650(n, m, i + 1, depth + 1)
    }
}