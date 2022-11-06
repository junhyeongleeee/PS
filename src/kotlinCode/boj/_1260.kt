package kotlinCode.boj

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList


private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()

fun main() = with(br) {

    val (n, m, v) = readLine().split(" ").map { it.toInt() }
    val array = Array(n + 1) { mutableListOf<Int>() }

    repeat(m) {
        val (v1, v2) = readLine().split(" ").map { it.toInt() }
        array[v1].add(v2)
        array[v2].add(v1)
    }

    array.forEach {
        it.sort()
    }

    var visited = BooleanArray(n + 1)
    dfs(array, visited, v)
    visited = BooleanArray(n + 1)
    bw.write("\n")
    bfs(array, visited, v)

    bw.flush()
    bw.close()
}

fun dfs(array: Array<MutableList<Int>>, visited: BooleanArray, v: Int) {
    visited[v] = true
    bw.write("$v ")
    array[v].forEach {
        if (!visited[it]) {
            dfs(array, visited, it)
        }
    }
}

fun bfs(array: Array<MutableList<Int>>, visited: BooleanArray, v: Int) {
    var queue = LinkedList<Int>()
    queue.add(v)
    visited[v] = true

    while (!queue.isEmpty()) {
        var nv = queue.poll()
        bw.write("$nv ")
        array[nv].forEach {
            if (!visited[it]) {
                queue.add(it)
                visited[it] = true
            }
        }
    }
}