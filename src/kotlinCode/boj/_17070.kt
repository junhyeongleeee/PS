package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  파이프 옮기기 1
 *
 *  3 <= N <= 16
 */
private var result = 0
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }
    solve17070(1, 0, 0, n)

    println(result)
}

fun move17070(x: Int, y: Int, v: Int, n: Int) {
    when (v) {
        0 -> {
            if (!possible17070(x + 1, y, n)) return
            solve17070(x + 1, y, v, n)
        }
        1 -> {
            if (!possible17070(x, y + 1, n)) return
            solve17070(x, y + 1, v, n)
        }
        2 -> {
            if (!possible17070(x + 1, y, n)) return
            if (!possible17070(x, y + 1, n)) return
            if (!possible17070(x + 1, y + 1, n)) return
            solve17070(x + 1, y + 1, v, n)
        }
    }
}
fun possible17070(x: Int, y: Int, n: Int): Boolean {
    if (x > n - 1 || y > n - 1) return false
    if (arr[y][x] == 1) return false
    return true
}

fun solve17070(x: Int, y: Int, v: Int, n: Int) {
    if (x == n - 1 && y == n - 1) {
        result++
        return
    }

    when (v) {
        0 -> {
            move17070(x, y, 0, n)
            move17070(x, y, 2, n)
        }
        1 -> {
            move17070(x, y, 1, n)
            move17070(x, y, 2, n)
        }
        else -> {
            move17070(x, y, 0, n)
            move17070(x, y, 1, n)
            move17070(x, y, 2, n)
        }
    }
}