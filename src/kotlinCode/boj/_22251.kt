package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  풀이 :
 *      브루트포스
 *      - 각 숫자마다 몇 개의 반전이 필요한지 구해놓기
 *      - 가능한 층 수의 개수 비교
 *
 *      2. 디스플레이를 각 인덱스로 설정 ( Boolean 버전 )
 *      3. .. ( Bit 버전 )
 */

private lateinit var st: StringTokenizer
private val arr = arrayOf(
    intArrayOf(0, 4, 3, 3, 4, 3, 2, 3, 1, 2),
    intArrayOf(4, 0, 5, 3, 2, 5, 6, 1, 5, 4),
    intArrayOf(3, 5, 0, 2, 5, 4, 3, 4, 2, 3),
    intArrayOf(3, 3, 2, 0, 3, 2, 3, 2, 2, 1),
    intArrayOf(4, 2, 5, 3, 0, 3, 4, 3, 3, 2),
    intArrayOf(3, 5, 4, 2, 3, 0, 1, 4, 2, 1),
    intArrayOf(2, 6, 3, 3, 4, 1, 0, 5, 1, 2),
    intArrayOf(3, 1, 4, 2, 3, 4, 5, 0, 4, 3),
    intArrayOf(1, 5, 2, 2, 3, 2, 1, 4, 0, 1),
    intArrayOf(2, 4, 3, 1, 2, 1, 2, 3, 1, 0),
)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    // n층 까지 이용 가능, 층은 K 자리수, 최대 P개 반전 시킬 수 있음, 현재 X층

    val n = st.nextToken().toInt()          // <= 10^6
    val k = st.nextToken().toInt()          // <= 6
    val p = st.nextToken().toInt()          // <= 42
    val x = st.nextToken().toInt()          // <= 10^6

    val t = arrayOf(0b110)

    val tt = (Character.getNumericValue(t[0]) xor 12).toLong().countOneBits()


    val floor = IntArray(k)

    var num = x
    for (i in 0 until k) {
        floor[k - i - 1] = num % 10
        num /= 10
    }

    var result = 0
    for (i in 1 .. n) {
        if (i == x) continue
        var l = i
        var sum = 0
        for (j in 0 until k) {
            sum += arr[l % 10][floor[k - j - 1]]
            l /= 10
        }
        if (sum <= p) {
            result++
        }
    }

    println(result)
}
