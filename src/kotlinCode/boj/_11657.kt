package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min


private lateinit var st: StringTokenizer
private val sb = StringBuilder()
private lateinit var arr: Array<MutableList<Node11657>>
private lateinit var dist: LongArray

data class Node11657(val v: Int, val c: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n + 1) { mutableListOf() }
    dist = LongArray(n + 1) { Long.MAX_VALUE }

    repeat(m) {
        st = StringTokenizer(readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val c = st.nextToken().toInt()

        arr[a].add(Node11657(b, c))
    }

    // 1이 아무것도 연결 안되어있을 때
    if (arr[1].size == 0) {
        for (i in 2 .. n) {
            sb.append(-1).appendLine()
        }
        println(sb)
        return@with
    }

    dist[1] = 0

    for (i in 0 until n - 1) {
        for (j in 1 .. n) {
            for (v in arr[j]) {
                if (dist[j] != Long.MAX_VALUE && dist[v.v] > dist[j] + v.c) {
                    dist[v.v] = dist[j] + v.c
                }
            }
        }
    }
//    println("dist: ${dist.joinToString(" ")}")

    for (j in 1 .. n) {
        for (v in arr[j]) {
            if (dist[j] != Long.MAX_VALUE && dist[v.v] > dist[j] + v.c) {
                println(-1)
                return@with
            }
        }
    }

    for (i in 2 .. n) {
        sb.append(if (dist[i] == Long.MAX_VALUE) -1 else dist[i]).appendLine()
    }

    println(sb)
}
