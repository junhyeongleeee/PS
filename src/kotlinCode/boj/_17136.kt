package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

/**
 *  색종이 붙이기
 */
private val arr = Array(10) { IntArray(10) }
private lateinit var st: StringTokenizer
private val confetti = IntArray(5) { 5 }
private var total = 0
private var result = Integer.MAX_VALUE
private var colorCount = 0
fun main() {
    repeat(10) { i ->
        st = StringTokenizer(readLine())
        repeat(10) { j ->
            val n = st.nextToken().toInt()
            arr[i][j] = n
            if (n == 1) {
                colorCount++
            }
        }
    }
    solve17136(0, 0, 0)

    if (result == Integer.MAX_VALUE) {
        println(-1)
    }else {
        println(result)
    }
}

fun possible(x: Int, y: Int, size: Int): Boolean {
    if (x + size > 10 || y + size > 10) return false
    if (confetti[size - 1] <= 0) return false

    for (i in y until y + size) {
        for (j in x until x + size) {
            if (arr[i][j] != 1) {
                return false
            }
        }
    }
    return true
}

fun color(x: Int, y: Int, size: Int, c: Int) {
    for (i in y until y + size) {
        for (j in x until x + size) {
            arr[i][j] = c
        }
    }
}

fun fill(x: Int, y: Int, size: Int, cnt: Int) {
    if (!possible(x, y, size)) return

    confetti[size - 1]--
    total++
    color(x, y, size, 0)
    solve17136(x, y, cnt + size * size)
    color(x, y, size, 1)
    confetti[size - 1]++
    total--
}

fun solve17136(x: Int, y: Int, cnt: Int) {

    if (cnt == colorCount) {
        result = min(result, total)
        return
    }

    if (x >= 10) {
        solve17136(0, y + 1, cnt)
        return
    }

    if (y >= 10) return

    if (arr[y][x] == 0) {
        solve17136(x + 1, y, cnt)
        return
    }

    for (i in 5 downTo 1) {
        fill(x, y, i, cnt)
    }
}