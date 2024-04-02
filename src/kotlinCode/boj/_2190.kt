package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

/**
 *  <범위>
 *      1 <= N <= 100
 *      1 <= A <= 2_000_000_000
 *      1 <= B <= 2_000_000_000
 *
 *
 *  <풀이>
 *      M = 2_000_000_000
 *      사각형 안의 포함된 점들의 최대 개수
 *
 *      그냥 찾는 건 안되고 N 개의 점들을 활용해야 함.
 *      결국 N을 포함시켜야 하기 때문에 N개의 점들을 기준으로 어떻게 해봐야할 것 같음.
 *
 *      N들의 교차점들을 이용해서 찾아보기 -> O(N^2) 교차점 찾기 * O(N) 포함되는 점 찾기
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val (n, a, b) = readLine().split(" ").map { it.toInt() }

    val list = mutableListOf<Pair<Long, Long>>()
    val set = mutableSetOf<Pair<Long, Long>>()

    repeat(n) {
        st = StringTokenizer(readLine())

        val x = st.nextToken().toLong()
        val y = st.nextToken().toLong()

        list.add(Pair(x, y))
        set.add(Pair(x, y))
    }

    for (i in 0 until list.size) {
        val p1 = list[i]
        for (j in 0 until list.size) {
            val p2 = list[j]

            if (p1.first == p2.first || p1.second == p2.second) continue

            set.add(Pair(p1.first, p2.second))
            set.add(Pair(p1.second, p2.first))
        }
    }

    var result = 0

    set.forEach { p1 ->
        result = max(result, list.count { p2 ->
            (p1.first <= p2.first && p1.first + a >= p2.first) && (p1.second <= p2.second && p1.second + b >= p2.second)
        }.also {
            println("it: $it p1: $p1 ")
        })
    }

    println(result)
}