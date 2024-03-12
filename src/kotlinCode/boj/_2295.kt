package kotlinCode.boj

import kotlin.math.max


/**
 *  5 <= N <= 1,000
 *
 *  - 중복된 3개의 집합 O(N^3)
 *  - 집합의 합이 원소의 최대 보다 넘으면 더이상 확인할 필요가 없다 -> (N / 3)^3 -> 27,000,000 정도
 *  - 합이 있는지 확인은 이분 탐색으로
 */

private lateinit var arr: IntArray
private val sum = mutableListOf<Int>()
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    arr = IntArray(n)
    repeat(n) {
        arr[it] = readLine().toInt()
    }

    arr.sort()

    val map = hashSetOf<Int>()

    // 두 수의 합 배열 생성
    for (i in 0 until n) {
        for (j in i until n) {
            map.add(arr[i] + arr[j])
        }
    }

    sum.sort()


    // A + B + C = X -> X - C = A + B
    // X != C
    for (i in n - 1 downTo 1) {
        for (j in i - 1 downTo 0) {
//            if (solve2295(arr[i] - arr[j])) {
//                println(arr[i])
//                return@with
//            }

            if (map.contains(arr[i] - arr[j])) {
                println(arr[i])
                return@with
            }
        }
    }





}
/*val max = arr.maxOf { it }
    var result = Int.MIN_VALUE

    for (i in 0 until n) {
        if (max / 3 + max % 3 < arr[i]) break
        for (j in i until n) {
//            if (max / 2 < arr[j]) break
            for (k in j until n) {
                val sum = arr[i] + arr[j] + arr[k]
                if (sum > max) break
//                println("i: $i j: $j k: $k sum: $sum")
                if (solve2295(k, n, sum)) {
                    result = max(result, sum)
                }
            }
        }
    }*/

fun solve2295(find: Int): Boolean {
    var left = 0
    var right = sum.size - 1

    if (left == right) {
//        println("arr[right]: ${arr[right]} find: $find")
        return sum[right] == find
    }

    while (left <= right) {
        val mid = (left + right) / 2

        when {
            sum[mid] < find -> {
                left = mid + 1
            }
            sum[mid] == find -> {
                return true
            }
            else -> {
                right = mid
            }
        }
    }

    return false
}