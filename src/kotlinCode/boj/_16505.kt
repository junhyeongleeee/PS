package kotlinCode.boj

import java.lang.Math.pow
import kotlin.math.pow

/**
 *  별
 *      - **
 *      - *
 *      - 각 별 마다 재귀를 돌아야 함
 *      - 마지막은 위의 모양으로 저장해야 됌.
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val area = Array(1024) { Array(1024) { ' ' } }

fun main() = with(br) {
    val n = readLine().toInt()

    markStar(0, 0, n)
    printStar(n)

    bw.flush()
    bw.close()
}

fun markStar(row: Int, col: Int, n: Int) {
    if (n == 0) {
        area[row][col] = '*'
        return
    }
    markStar(row, col, n - 1)
    markStar(row + 2.0.pow((n - 1).toDouble()).toInt(), col, n - 1)
    markStar(row, col + 2.0.pow((n - 1).toDouble()).toInt(), n - 1)
}

fun printStar(n: Int) {
    val size = 2.0.pow(n.toDouble()).toInt()
    for (i in 0 until size) {
        for (j in 0 until size - i) {
            bw.write(area[i][j].toString())
        }
        bw.write("\n")
    }
}