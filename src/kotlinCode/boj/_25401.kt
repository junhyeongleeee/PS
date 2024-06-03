package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

/**
 *  - 등차수열 -
 *  1. 정답의 최대 값은 n - 2
 *  2. 모든 쌍을 확인하면서 바꿔야할 숫자 카드의 개수를 센다.
 */

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()              // n <= 500
    st = StringTokenizer(readLine())

    val deck = IntArray(n)

    repeat(n) {
        deck[it] = st.nextToken().toInt()
    }

    var result = n - 2
    for (i in 0 until n) {
        for (j in i + 1 until n) {
            if ((deck[j] - deck[i]) % (j - i) != 0) continue

            val diff = (deck[j] - deck[i]) / (j - i)
            var cnt = 0

            for (k in 0 until n) {
                if (i == k || j == k) continue
                if (deck[k] == deck[i] + (k - i) * diff) continue
                cnt++
            }
            result = min(result, cnt)
        }
    }
    println(result)
}