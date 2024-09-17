package kotlinCode.boj

import java.util.StringTokenizer

fun main() {
    `15681`().solution()
}
class `15681` {
    lateinit var st: StringTokenizer
    lateinit var graph: Array<MutableList<Int>>
    lateinit var parent: IntArray
    lateinit var child: Array<MutableList<Int>>
    lateinit var size: IntArray
    val sb = StringBuilder()

    fun solution() = with(System.`in`.bufferedReader()){
        st = StringTokenizer(readLine())

        val n = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        val q = st.nextToken().toInt()

        graph = Array(n + 1) { mutableListOf() }
        parent = IntArray(n + 1)
        child = Array(n + 1) { mutableListOf() }
        size = IntArray(n + 1)

        repeat(n - 1) {
            st = StringTokenizer(readLine())

            val u = st.nextToken().toInt()
            val v = st.nextToken().toInt()

            graph[u].add(v)
            graph[v].add(u)
        }

        makeTree(r, -1)
        countSubTreeNodes(r)

        repeat(q) {
            val u = readLine().toInt()
            sb.appendLine(size[u])
        }
        println(sb)
    }

    fun makeTree(currentNode: Int, p: Int) {
        for (node in graph[currentNode]) {
            if(node != p) {
                child[currentNode].add(node)
                makeTree(node, currentNode)
            }
        }
    }

    fun countSubTreeNodes(currentNode: Int) {
        size[currentNode] = 1
        for (node in child[currentNode]) {
            countSubTreeNodes(node)
            size[currentNode] += size[node]
        }
    }
}