package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.system.exitProcess

private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var result: IntArray
private lateinit var visited: Array<BooleanArray>

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()                      // <= 1_000
    arr = Array(n) { mutableListOf() }
    result = IntArray(n)
    visited = Array(n) { BooleanArray(9) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        val m = st.nextToken().toInt()                  // <= 9
        repeat(m) { j ->
            arr[i].add(st.nextToken().toInt())        // <= 9
        }
    }
    solve16432(0, -1, n)

    println(-1)
}

fun solve16432(idx: Int, prev: Int, n: Int) {
    if (idx == n) {
        println(result.joinToString("\n"))
        exitProcess(0)
    }

    for (i in 0 until arr[idx].size) {
        if (visited[idx][i] || prev == arr[idx][i]) continue
        visited[idx][i] = true
        result[idx] = arr[idx][i]
        solve16432(idx + 1, arr[idx][i], n)
    }
}