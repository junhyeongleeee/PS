package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })
private lateinit var dist: LongArray
private lateinit var visited: BooleanArray
private lateinit var look: BooleanArray
private lateinit var arr: Array<MutableList<Pair<Int, Long>>>

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()                  // <= 100_000
    val m = st.nextToken().toInt()                  // <= 300_000

    look = BooleanArray(n)
    dist = LongArray(n) { Long.MAX_VALUE }
    visited = BooleanArray(n)
    arr = Array(n) { mutableListOf() }

    st = StringTokenizer(readLine())
    repeat(n) {
        look[it] = st.nextToken().toInt() == 1
    }

    repeat(m) {
        st = StringTokenizer(readLine())

        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val t = st.nextToken().toLong()              // <= 100_000

        arr[a].add(b to t)
        arr[b].add(a to t)
    }

    solve17396(n - 1)

    println(dist.joinToString(" "))
    println(if (dist[n - 1] == Long.MAX_VALUE) -1 else dist[n - 1])
}

fun solve17396(e: Int) {

    pq.add(0 to 0)
    dist[0] = 0

    while (pq.isNotEmpty()) {
        val queue = pq.poll()
        val cv = queue.first
        val d = queue.second

        if (dist[cv] < d) continue

        for (v in arr[cv]) {
            val nv = v.first
            val nd = v.second + d

            if (visited[cv] || (nv != e && look[nv])) continue
            if (dist[nv] > nd) {
                dist[nv] = nd
                pq.add(nv to nd)
            }
        }
    }
}