package kotlinCode.boj

import javax.xml.stream.events.Characters
import kotlin.math.pow


private val dx = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)
private val dy = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
private lateinit var arr: Array<CharArray>
private lateinit var visited: Array<BooleanArray>

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    visited = Array(n) { BooleanArray(n) }
    val zeroList = mutableListOf<Pair<Int, Int>>()
    arr = Array(n) { CharArray(n) }

    repeat(n) { i ->
        readLine().forEachIndexed { j, c ->
            arr[i][j] = c
            if (c == '0') {
                zeroList.add(Pair(i, j))
            }
        }
    }

    if (n < 3) {
        println(0)
        return@with
    }

    // 중복 개수 세기
    zeroList.forEach {
        val x = it.second
        val y = it.first

        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) continue
            if (arr[ny][nx] == '#') {
                arr[ny][nx] = 'x'
            }
        }
    }


    for (i in 0 until n) {
        for (j in 0 until n) {
            if (Character.isDigit(arr[i][j]) && arr[i][j] - '0' >= 1) {

                var cnt = arr[i][j] - '0'

                for (k in 0 until 8) {
                    val nx = j + dx[k]
                    val ny = i + dy[k]
                    if (nx < 0 || nx > n - 1 || ny < 0 || ny > n - 1) continue
                    if (arr[ny][nx] == 'x') continue

                    if (cnt == 0 && arr[ny][nx] == '#') {
                        arr[ny][nx] = 'x'
                    }
                    else if (arr[ny][nx] == '*') {
                        cnt--
                    }
                    else if (cnt != 0 && arr[ny][nx] == '#') {
                        arr[ny][nx] = '*'
                        cnt--
                    }
                }
            }
        }
    }

    var result = if (n >= 5) (n - 4.0).pow(2).toInt() else 0

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (arr[i][j] == '*') {
                result++
            }
        }
    }

    println(result)
}