package kotlinCode.boj

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var arr: IntArray
private val sb = StringBuilder()

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(m)

    dfs15651(n, m, 0)
    print(sb)
}

fun dfs15651(n: Int, m: Int, depth: Int) {
    if (depth == m) {
        arr.forEach {
            sb.append(it).append(' ')
        }
        sb.append('\n')
        return
    }

    for (i in 0 until n) {
        arr[depth] = i + 1
        dfs15651(n, m, depth + 1)
    }
}