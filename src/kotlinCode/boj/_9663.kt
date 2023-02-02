package kotlinCode.boj

import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.math.abs

/**
 *  N-Queen
 *  - 크기가 N x N 인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는다.
 *  - N이 주어졌을 때 퀸을 놓는 방법의 수
 *
 *  - 1차월 배열로 생각
 *  - index: 행, value: 열
 */

private val br = System.`in`.bufferedReader()
private lateinit var arr : IntArray

fun main() = with(br) {
    val n = readLine().toInt()
    arr = IntArray(n)
    var result = 0

    fun dfs(depth: Int) {
        if (depth == n) {
            result++
            return
        }
        for (i in 0 until n) {
            arr[depth] = i
            if (check(depth)) {
                dfs(depth + 1)
            }
        }
    }
    dfs(0)
    println(result)
}
fun check(col: Int): Boolean {
    for (i in 0 until col) {
        if (arr[col] == arr[i]) {
            return false
        }
        if (abs(col - i) == abs(arr[col] - arr[i])){
            return false
        }
    }
    return true
}
