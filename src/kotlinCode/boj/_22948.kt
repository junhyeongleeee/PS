package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *  원 이동하기 2
 *
 *  2 <= N <= 200,000
 *
 */
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var route: IntArray
private lateinit var visited: BooleanArray
private val sb = StringBuilder()
private val queue = java.util.ArrayDeque<Pair<Int, Int>>()
private val pq = PriorityQueue<Pair<Int, Int>> {
    a, b -> a.first - b.first
}

fun main() = with(br) {
    val n = readLine().toInt()
    arr = Array(n + 1) { mutableListOf() }
    route = IntArray(n + 1)
    visited = BooleanArray(n + 1)

    pq.add(Pair(-1000000, 0))
    pq.add(Pair(1000000, 0))

    repeat(n) {
        st = StringTokenizer(readLine())
        val k = st.nextToken().toInt()
        val x = st.nextToken().toInt()
        val r = st.nextToken().toInt()
        pq.add(Pair(x - r, k))
        pq.add(Pair(x + r, k))
    }
    st = StringTokenizer(readLine())
    val a = st.nextToken().toInt()
    val b = st.nextToken().toInt()

    makeTree(0)

    sb.append(bfs22948(a, b)).append("\n")
    val stack = java.util.ArrayDeque<Int>()
    var v = route[b]
    stack.add(b)
    while (true) {
        if (v == a) {
            stack.add(v)
            break
        }
        stack.add(v)
        v = route[v]
    }

    while (!stack.isEmpty()) {
        sb.append(stack.pollLast()).append(" ")
    }

    println(sb)
}

fun makeTree(parent: Int) {

    val point = pq.poll()

    if (parent != 0 || point.second != 0) {
        arr[parent].add(point.second)
        arr[point.second].add(parent)
    }

    // 자기 것이 아닐 때 재귀
    while (point.second != pq.peek().second) {
        makeTree(point.second)
    }
    pq.poll()
}

fun bfs22948(s: Int, e: Int): Int {
    queue.add(Pair(s, 1))
    visited[s] = true
    while (!queue.isEmpty()) {
        val q = queue.poll()
        val v = q.first
        val cnt = q.second
        if (v == e) {
            return cnt
        }

        arr[v].forEach {
            if (!visited[it]) {
                visited[it] = true
                queue.add(Pair(it, cnt + 1))
                route[it] = v
            }
        }
    }
    return -1
}