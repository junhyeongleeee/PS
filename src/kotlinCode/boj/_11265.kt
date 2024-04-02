package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private lateinit var visited: BooleanArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val arr = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    repeat(m) {
        st = StringTokenizer(readLine())

        val a = st.nextToken().toInt() - 1
        val b = st.nextToken().toInt() - 1
        val c = st.nextToken().toInt()

        visited = BooleanArray(n)
        visited[a] = true

        sb.appendLine(if (dfs11265(arr, a, b, c, 0)) {
            "Enjoy other party"
        }else {
            "Stay here"
        })
    }

    println(sb)
}

fun dfs11265(arr: Array<IntArray>, a: Int, b: Int, deadLine: Int, time: Int): Boolean {

    var result = false

    arr[a].forEachIndexed { n, t ->

        if (a != n && !visited[n]) {
            val nTime = time + t
            if (nTime > deadLine) return@forEachIndexed

            // 도착한 경우
            if (b == n) {
                return true
            }

            visited[n] = true
            result = result || dfs11265(arr, n, b, deadLine, nTime)
            visited[n] = false
        }
    }

    return result
}