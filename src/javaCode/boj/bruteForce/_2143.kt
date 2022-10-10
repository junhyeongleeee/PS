package javaCode.boj.bruteForce

import java.io.BufferedReader
import java.io.InputStreamReader


/**
 *  A = {1, 3, 1, 2}, B = {1, 3, 2}, T=5
 *  T(=5) = A[1] + B[1] + B[2]
 *  = A[1] + A[2] + B[1]
 *  = A[2] + B[3]
 *  = A[2] + A[3] + B[1]
 *  = A[3] + B[1] + B[2]
 *  = A[3] + A[4] + B[3]
 *  = A[4] + B[2]
 *
 *  - T(-1,000,000,000 ≤ T ≤ 1,000,000,000) -> 10억
 *  - n(1 ≤ n ≤ 1,000) -> 배열의 크기 최대 1000
 *  - 각각의 배열 원소는 절댓값이 1,000,000을 넘지 않는 정수이다.
 *
 *
 *
 *  1           2                       3                     4
 *  [1][2][3][4] [1,2][2,3][3,4]
 *  1, 3, 1, 2, (1, 3), (3, 1), (1, 2), (1, 3, 1), (3, 1, 2), (1, 3, 1, 2)
 *
 *
 *  1, 3, 1, 2, 4, 4, 3, 5, 6, 7
 *  1, 3, 2, 4, 5, 6
 *
 */

fun main()  {

    val t: Long = readln().toLong()
    val aSize = readln().toInt()
    val aList = readln().split(" ").map { it.toInt() }
    val bSize = readln().toInt()
    val bList = readln().split(" ").map { it.toInt() }

    val aSumList = getSumList(aList)
    val bSumList = getSumList(bList)


    bSumList.sort()

    var result = 0L
    aSumList.forEach {
        result += binarySearch(t - it, bSumList)
    }
    println(result)
}

fun binarySearch(target: Long, list: MutableList<Long>): Long {
    var left = 0
    var right = 0

    var start = 0
    var end = list.size - 1

    while (start <= end) {
        var mid : Int = (start + end) / 2

        if (target <= list[mid]) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }
    left = end

    start = 0
    end = list.size - 1
    while (start <= end) {
        var mid : Int = (start + end) / 2

        if (list[mid] <= target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    right = end
    return (right - left).toLong()
}

fun getSumList(list: List<Int>): MutableList<Long> {
    val aSumList = mutableListOf<Long>()
    for (i in list.indices) {
        var sum = 0L
        for (j in i until list.size) {
            sum += list[j]
            aSumList.add(sum)
        }
    }
    return aSumList
}