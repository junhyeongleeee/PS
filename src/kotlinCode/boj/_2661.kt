package kotlinCode.boj

import kotlin.system.exitProcess


private val arr = IntArray(80)
private val numbers = listOf(1, 2, 3)

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    solve2661(0, n)
}

fun solve2661(index: Int, n: Int) {

    if (index == n) {
        println(arr.filterNot { it == 0 }.joinToString(""))
        exitProcess(0)
    }

    // check
    for (num in numbers) {
        if (check123(num, index, n)) {
            solve2661(index + 1, n)
        }
    }
}

fun check123(num: Int, index: Int, size: Int): Boolean {
    arr[index] = num
    for (i in 1 .. size / 2) {
        var cnt = 0
        for (j in index downTo index - i + 1) {


            if (j - i < 0) break

            val a = arr[j]
            val b = arr[j - i]

            if (a == b) {
                cnt++
            }
        }
        if (cnt == i) {
            return false
        }
    }
    return true
}