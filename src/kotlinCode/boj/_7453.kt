package kotlinCode.boj

import java.util.StringTokenizer

/**
 *   정수로 이루어진 크기가 같은 배열 A, B, C, D가 있다.
 *   A[a], B[b], C[c], D[d]의 합이 0인 (a, b, c, d) 쌍의 개수를 구하는 프로그램을 작성하시오.
 *
 *   첫째 줄에 배열의 크기 n (1 ≤ n ≤ 4000)이 주어진다.
 *   다음 n개 줄에는 A, B, C, D에 포함되는 정수가 공백으로 구분되어져서 주어진다.
 *   배열에 들어있는 정수의 절댓값은 최대 228이다.
 *
 *   합이 0이 되는 쌍의 개수를 출력한다.
 *
 *   4000^4 = 64,000,000,000,000 -> 64조
 *   228 * 4 = 약 1000
 *
 *   두 배열끼리 합 -> 4000^2 * 2 = 32,000,000 -> 약 3천2백만
 *   이분 탐색 -> n * logN -> 3천 * ... -> Good -> 실패
 *
 *   투 포인터 ->
 */

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    // Array(row) { IntArray (column) }
    val array = Array(n) { IntArray(4) }
    repeat(n) { i ->
        val st = StringTokenizer(readLine())
        repeat(4) { j ->
            array[i][j] = st.nextToken().toInt()
        }
    }
    val sum1 = IntArray(n * n)
    val sum2 = IntArray(n * n)
    var idx = 0
    repeat(n) { i ->
        repeat(n) { j ->
            sum1[idx] = array[i][0] + array[j][1]
            sum2[idx] = array[i][2] + array[j][3]
            idx++
        }
    }

    sum1.sort()
    sum2.sort()

    var p1 = 0
    var p2 = sum1.size - 1

    var result = 0
    while (p1 < sum1.size && p2 >= 0) {
        if (sum1[p1] + sum2[p2] == 0) {
            var cnt1 = 0
            var i = p1
            while (i < sum1.size && sum1[i] == sum1[p1]) {
                cnt1++
                i++
            }
            p1 = i

            var cnt2 = 0
            i = p2
            while (i >= 0 && sum2[i] == sum2[p2]) {
                cnt2++
                i--
            }
            p2 = i
            result += cnt1 * cnt2
        } else if (sum1[p1] + sum2[p2] < 0) {
            p1++
        } else {
            p2--
        }
    }

    println(result)
}


fun binarySearch7453(target: Int, array: IntArray): Int {
    var left = 0
    var right = 0

    var start = 0
    var end = array.size - 1

    // left
    while (start <= end) {
        val mid = (start + end) / 2
        if (array[mid] >= target) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }

    left = end
    start = 0
    end = array.size - 1
    // right
    while (start <= end) {
        val mid = (start + end) / 2
        if (array[mid] <= target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    right = end
    return right - left
}