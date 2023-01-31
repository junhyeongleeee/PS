package kotlinCode.boj


private val br = System.`in`.bufferedReader()
private val sb = StringBuilder()
private lateinit var arr: IntArray
fun main() = with(br){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)
    fun dfs(s: Int, depth: Int) {
        if (depth == m) {
            sb.append(arr.joinToString(" ")).append('\n')
            return
        }
        for (i in s until n) {
            arr[depth] = i + 1
            dfs(i, depth + 1)
        }
    }
    dfs(0, 0)
    print(sb)
}