package practice

import kotlin.math.abs
import kotlin.math.max


/**
 *  1 <= n <= 30
 *  1 <= m <= 1,000,000,000
 *  1 <= fires, ices <= n
 */
private lateinit var arr: Array<IntArray>
private val fList = mutableListOf<Pair<Int, Int>>()
private val iList = mutableListOf<Pair<Int, Int>>()
fun main() {
    // 0 분 , 모든칸은 온도 0 에서 시작
    // m 분 후 온도를 알고 싶다.

    val n = 5
    val m = 3
    val fires = arrayOf(
        intArrayOf(5, 5),
        intArrayOf(1, 3),
        intArrayOf(5, 2),
    )
    val ices = arrayOf(
        intArrayOf(1, 5),
        intArrayOf(3, 2),
    )

    arr = Array(n) { IntArray(n) }

    fires.forEach {
        val x = it[1] - 1
        val y = it[0] - 1
        arr[y][x] = -1
        fList.add(Pair(x, y))
    }

    ices.forEach {
        val x = it[1] - 1
        val y = it[0] - 1
        arr[y][x] = 1
        iList.add(Pair(x, y))
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            // 불 범위 확인
            for (k in 0 until fList.size) {
                val point = fList[k]
                val x = point.first
                val y = point.second
                val nm = max(abs(x - j), abs(y - i))
                if (nm > m) continue
                arr[i][j] += m - nm + 1
            }
            // 얼음 범위 확인
            for (k in 0 until iList.size) {
                val point = iList[k]
                val x = point.first
                val y = point.second
                val nm = abs(x - j) + abs(y - i)
                if (nm > m) continue
                arr[i][j] -= m - nm + 1
            }
        }
    }
    arr.forEach { println(it.joinToString(" ")) }
}