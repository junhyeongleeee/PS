package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  색종이 만들기
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private var white = 0
private var black = 0
fun main() = with(br) {
    val n = readLine().toInt()
    arr = Array(n) { IntArray(n) }
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }
    solve(0, 0, n)
    println(white)
    println(black)
}

fun solve(r: Int, c: Int, size: Int) {

    val result = check(r, c, size)
    if (result != 0) {
        when(result) {
            -1 -> white++
            else -> black++
        }
        return
    }
    if (size == 1) return
    val newSize = size / 2
    solve(r, c, newSize)
    solve(r + newSize, c, newSize)
    solve(r, c + newSize, newSize)
    solve(r + newSize, c + newSize, newSize)
}

fun check(r: Int, c: Int, size: Int): Int {
    val v = arr[r][c]
    for (i in r until r + size) {
        for (j in c until c + size) {
            if (v != arr[i][j]) {
                return 0
            }
        }
    }
    return if(v == 1) 1 else -1
}