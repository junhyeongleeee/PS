package kotlinCode.softeer

import java.util.*

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var graph: Array<MutableList<Node>>
private lateinit var visited: BooleanArray

data class Node(val n: Int, val d: Int)

fun main() = with(br) {
    val n = readLine().toInt()

    graph = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)

    repeat(n - 1) {
        st = StringTokenizer(readLine())
        val n1 = st.nextToken().toInt()
        val n2 = st.nextToken().toInt()
        val d = st.nextToken().toInt()

        graph[n1].add(Node(n2, d))
        graph[n2].add(Node(n1, d))
    }

    for(i in 1..n) {
        visited = BooleanArray(n + 1)
        println(dfs(i, 0))
    }
}

fun dfs(s: Int, sum: Int): Int {
    visited[s] = true
    var result = sum
    graph[s].forEach {
        if(!visited[it.n]) {
            result += dfs(it.n, it.d + sum)
        }
    }
    return result
}