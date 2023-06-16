package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

/**
 *  치킨 배달
 *
 */
private lateinit var st: StringTokenizer
private lateinit var visited: BooleanArray
private lateinit var chose: Array<Pair<Int, Int>>
private val house = mutableListOf<Pair<Int, Int>>()
private val chicken = mutableListOf<Pair<Int, Int>>()
private var result = Integer.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            when (st.nextToken().toInt()) {
                2 -> chicken.add(Pair(j, i))
                1 -> house.add(Pair(j, i))
            }
        }
    }

    visited = BooleanArray(chicken.size)
    chose = Array(m) { Pair(0, 0) }

    solve15686(0, 0, chicken.size, m)

    println(result)
}

fun calDistance(p1: Pair<Int, Int>, p2: Pair<Int, Int>) = abs(p1.first - p2.first) + abs(p1.second - p2.second)

fun solve15686(s: Int, cnt: Int, size: Int, m: Int) {
    if (cnt == m) {
        var sum = 0
        for (i in 0 until house.size) {
            var md = Integer.MAX_VALUE
            for (element in chose) {
                md = min(md, calDistance(element, house[i]))
            }
            sum += md
        }
        result = min(result, sum)
        return
    }

    for (i in s until size) {
        if (visited[i]) continue
        visited[i] = true
        chose[cnt] = chicken[i]
        solve15686(i, cnt + 1, size, m)
        visited[i] = false
    }
}