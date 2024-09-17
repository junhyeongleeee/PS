package kotlinCode.boj

import java.util.*
import kotlin.math.max

class `1647` {

    private lateinit var st: StringTokenizer
    private lateinit var parent: IntArray

    data class DistanceData(val a: Int, val b: Int, val c: Int)

    val set = mutableSetOf<Int>()

    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())

        val n = st.nextToken().toInt()              // <= 100_000
        val m = st.nextToken().toInt()              // <= 1_000_000

        val arr = Array(m) { DistanceData(0, 0, 0) }
        parent = IntArray(n + 1) { it }

        for (i in 1 .. n) { set.add(i) }

        repeat(m) { i ->
            st = StringTokenizer(readLine())

            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            val c = st.nextToken().toInt()

            arr[i] = DistanceData(a, b, c)
        }

        arr.sortBy { it.c }

        var answer = 0
        var max = Int.MIN_VALUE

        for (dd in arr) {
            if (find(dd.a) == find(dd.b)) continue
            max = max(max, dd.c)
            answer += dd.c
            union(dd.a, dd.b)
        }
        println(answer - max)
    }


    fun find(a: Int) : Int {

        val pa = parent[a]
        if (a == pa) return a

        parent[a] = find(pa)

        return parent[a]
    }
    fun union(a: Int, b: Int) {
        val pa = find(a)
        val pb = find(b)

        if (pa < pb) {
            parent[pb] = pa
        }else {
            parent[pa] = pb
        }
    }
}

fun main() {
    `1647`().solution()
}