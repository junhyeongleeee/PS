package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private val sb = StringBuilder()
private lateinit var arr: Array<MutableList<Node11657>>
private lateinit var visited: BooleanArray
private lateinit var dist: LongArray

data class Node11657(val v: Int, val c: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n + 1) { mutableListOf() }
    dist = LongArray(n + 1) { Long.MAX_VALUE }
    visited = BooleanArray(n + 1)

    repeat(m) {
        st = StringTokenizer(readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()

        if (a != b) {
            arr[a].add(Node11657(b, c))
        }

    }

    dist[1] = 0

    if (solve11657(n)) {
        println(-1)
        return@with
    } else {
        for (i in 2..n) {
            sb.append(if (dist[i] == Long.MAX_VALUE) -1 else dist[i]).append("\n")
        }
    }
    println(sb)
}

fun solve11657(n: Int): Boolean {

    var result = false

    for (j in 1 until n) {              // V - 1 만큼

        for (i in 1..n) {             // 모든 정점 확인

            for (node in arr[i]) {
                if (dist[node.v] > dist[i] + node.c) {
                    dist[node.v] = dist[i] + node.c
                }
            }
        }
    }

    for (i in 1..n) {
        for (node in arr[i]) {
            if (dist[node.v] > dist[i] + node.c) {
                result = true
                break
            }
        }
    }

    return result
}

