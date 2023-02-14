package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.system.exitProcess


/**
 *  스도쿠
 *  - 행 체크
 *  - 열 체크
 *  - 사각형 체크
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var st: StringTokenizer
private val arr = Array(9) { IntArray(9) }

data class Point2580(val r: Int, val c: Int)

fun main() = with(br) {
    repeat(9) { i ->
        st = StringTokenizer(readLine())
        repeat(9) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }
    dfs2580(0, 0)
}

fun dfs2580(r: Int, c: Int) {
    if (c == 9) {
        dfs2580(r + 1, 0)
        return
    }
    if (r == 9) {
        arr.forEach { println(it.joinToString(" ")) }
        exitProcess(0)
    }
    if (arr[r][c] == 0) {
        for (i in 1 .. 9) {
            if (check2580(r, c, i)) {
                arr[r][c] = i
                dfs2580(r, c + 1)
            }
        }
        arr[r][c] = 0
        return
    }
    dfs2580(r, c + 1)
}

fun check2580(r: Int, c: Int, v: Int): Boolean {
    for (i in 0 until 9) {
        if (v == arr[r][i]) {
            return false
        }
        if (v == arr[i][c]) {
            return false
        }
    }
    val tempR = r / 3 * 3
    val tempC = c / 3 * 3
    for (i in tempR until 3) {
        for (j in tempC until 3) {
            if (v == arr[i][j]) {
                return false
            }
        }
    }
    return true
}