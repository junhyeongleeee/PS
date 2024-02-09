package kotlinCode.boj

import java.util.*

/**
 *  제한 시간 : 2초
 *  메모리 제한 : 128 MB
 *
 *  풀이 과정
 *      - 블루레이의 최소 크기를 어떻게 ?
 *      - 이상 적인 크기는 총 크기 / M
 *      -
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    st = StringTokenizer(readLine())

    val arr = IntArray(n)

    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    val l = arr.maxOf { it }
    val r = 10_000 * 100_000

    if (n == m) {
        println(arr.maxOf { it })
        return@with
    }

//    println("l : $l r: $r")
    println(binarySearch(arr, m, l, r))
}

fun counting(arr: IntArray, max: Int): Int {
    var sum = 0
    var cnt = 1
    for (v in arr) {
        sum += v
        if (sum > max) {
            sum = v
            cnt++
        }
    }
    return cnt
}

fun binarySearch(arr: IntArray, m: Int, l: Int, r: Int): Int {

    var left = l
    var right = r

    while (left <= right) {
        val mid = (left + right) / 2
        val count = counting(arr, mid)
//        println("left: $left right: $right mid: $mid count: $count")
        if (count > m) {
            left = mid + 1
        }else {
            right = mid - 1
        }

    }

    return left
}