package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

/**
 *  파티
 *
 *  - 시작점과 끝점이 같은 도로는 없다.
 *  - 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
 *
 *
 *  1 <= N <= 1,000
 *  1 <= M <= 10,000
 *  1 <= T <= 100
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Pair<Int, Int>>>
private lateinit var visited: BooleanArray
private lateinit var time: IntArray
private val pq = PriorityQueue<Pair<Int, Int>> { o1, o2 ->
    o1.second - o2.second
}

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val x = st.nextToken().toInt()

    arr = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)
    time = IntArray(n + 1) { Integer.MAX_VALUE }

    repeat(m) {
        st = StringTokenizer(readLine())
        val s = st.nextToken().toInt()
        val e = st.nextToken().toInt()
        val t = st.nextToken().toInt()
        arr[s].add(Pair(e, t))
    }

    var result = 0
    for (i in 1..n) {
        if (i == x) continue
        time = IntArray(n + 1) { Integer.MAX_VALUE }
        val go = totalTime(n, i, x)
        val back = totalTime(n, x, i)
        result = max(result, go + back)
    }
    println(result)
}

fun totalTime(n: Int, s: Int, e: Int): Int {
    time = IntArray(n + 1) { Integer.MAX_VALUE }
    solve1238(s, e)
    return time[e]
}

fun solve1238(s: Int, e: Int): Int {
    pq.clear()
    pq.add(Pair(s, 0))
    while (!pq.isEmpty()) {
        val node = pq.poll()
        val v = node.first
        val t = node.second

        if (time[v] < t) continue

        for (nn in arr[v]) {
            val nv = nn.first
            val nt = nn.second
            if (time[nv] > t + nt) {
                time[nv] = t + nt
                pq.add(Pair(nv, t + nt))
            }
        }
    }
    return -1
}