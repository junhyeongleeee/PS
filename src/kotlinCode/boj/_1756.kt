package kotlinCode.boj

/**
 *  알고리즘 : 이분탐색
 *  풀이 :
 *      - 오븐 지름 다시 설정
 *      - 이분 탐색 (내림차순 upper bound)
 */

import java.util.StringTokenizer
import kotlin.math.ceil

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val d = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val oven = IntArray(d)
    val pizza = IntArray(n)

    st = StringTokenizer(readLine())
    repeat(d) {
        oven[it] = st.nextToken().toInt()
    }

    st = StringTokenizer(readLine())
    repeat(n) {
        pizza[it] = st.nextToken().toInt()
    }

    var raidus = oven[0]

    // 오븐 지름 다시 설정
    // 5 6 4 3 6 2 3 -> 5 5 4 3 3 2 2

    for (i in 1 until d) {
        if (raidus < oven[i]) {
            oven[i] = raidus
        } else {
            raidus = oven[i]
        }
    }


    var l = 0
    var r = d - 1

    for (i in 0 until n) {
        val radius = pizza[i]

        // 더이상 쌓을 수 없는 경우 종료.
        if (r == -1 || oven[0] < radius) {
            println(0)
            return@with
        }

        // 내림차순 upper bound
        l = 0
        while (l < r) {
            val mid = ceil((l + r).toDouble() / 2).toInt()

            if (radius <= oven[mid]) {
                l = mid
            } else {
                r = mid - 1
            }
        }
        // 피자가 오븐에 들어갔으면 그 위부터 쌓을 수 있음.
        r -= 1
    }
    println(r + 2)
}