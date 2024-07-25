package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  풀이 :
 *      1. 이분탐색 (x)
 *      (T0, S0), (T1, S1) ... (Tn, Sn) -> S 순으로 정렬
 *
 *      Tn-1, Sn-1 이 불가능하다면 Tn, Sn도 불가능 하기 때문
 *
 *      가장 늦은 시간의 최대 <= 1_000_000 - 1_000
 *      n <= 1_000
 *
 *      1_000_000 * 1_000 -> 시간 초과
 *      따라서
 *
 *      0 부터 최대시간에서 이분탐색으로 가능한 시간대를 찾는다.
 *
 *      O(log(1_000_000) * 1_000)
 *
 *      2. 그리디 (o)
 *      똑같이 정렬 후
 *
 *      가장 마지막 일 부터 최대한 늦게 시작할 수 있는 시간을 업데이트 한다.
 *
 *
 *      ex) (5, 3) (14, 10) (16, 1) (20, 1)
 *
 *      - 맨 마지막 일의 늦게 시작할 수 있는 시간 20 - 1 => 19 이지만 이전 일은 16시에 끝나야 하기 때문에 16으로 업데이트..
 *
 */

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()                      // <= 1_000

    val arr = Array(n) { Pair(0, 0) }

    repeat(n) {
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()              // <= 1_000
        val s = st.nextToken().toInt()              // <= 1_000_000

        arr[it] = t to s
    }

    arr.sortBy { it.second }

    // 최대한 늦게 시작할 수 있는 시간
    var maxStartTime = arr[n - 1].second

    for (i in n - 1 downTo 1) {
        maxStartTime -= arr[i].first
        if (maxStartTime > arr[i - 1].second) {
            maxStartTime = arr[i - 1].second
        }
    }

    // 첫 번째 일이 해결되야 할 시간대와 비교
    println(
        if (maxStartTime > arr[0].second) arr[0].second - arr[0].first else {
            if (maxStartTime - arr[0].first < 0) -1 else maxStartTime - arr[0].first
        }
    )
}