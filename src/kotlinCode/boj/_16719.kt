package kotlinCode.boj

import java.util.SortedSet


private lateinit var arr: CharArray
private lateinit var visited: BooleanArray
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    val s = readLine()
    arr = s.toCharArray()
    visited = BooleanArray(arr.size)
    solve16719(0, arr.size - 1)
    println(sb)
}

fun solve16719(s: Int, e: Int) {
    if (s > e) {
        return
    }

    var idx = -1
    var minValue = Char.MAX_VALUE
    for (i in s..e) {
        if (minValue > arr[i]) {
            minValue = arr[i]
            idx = i
        }
    }
    visited[idx] = true
    sb.append(arr.filterIndexed { index, c -> visited[index] }.joinToString("")).appendLine()

    solve16719(idx + 1, e)
    solve16719(s, idx - 1)
}