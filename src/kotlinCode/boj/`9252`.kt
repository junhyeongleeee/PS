package kotlinCode.boj

import kotlin.math.max

fun main() {
    `9252`().solution()
}

class `9252` {

    val queue = ArrayDeque<Char>()

    lateinit var a: CharArray
    lateinit var b: CharArray

    fun solution() = with(System.`in`.bufferedReader()) {
        a = readLine().toCharArray()
        b = readLine().toCharArray()

        val arr = Array(a.size + 1) { IntArray(b.size + 1) }

        for (i in 0 until a.size) {
            for (j in 0 until b.size) {
                if (a[i] == b[j]) {
                    arr[i + 1][j + 1] = arr[i][j] + 1
                } else {
                    arr[i + 1][j + 1] = max(arr[i][j + 1], arr[i + 1][j])
                }
            }
        }

//        arr.forEach { println(it.joinToString(" ")) }

        func1(a.size, b.size, arr)

        println(queue.size)
        if (queue.isNotEmpty()) {
            println(queue.joinToString(""))
        }
    }

    fun func1(i: Int, j: Int, arr: Array<IntArray>) {
        if (i == 0 || j == 0) return

        if (arr[i - 1][j] == arr[i][j]) {
            func1(i - 1, j, arr)
            return
        }
        if (arr[i][j - 1] == arr[i][j]) {
            func1(i, j - 1, arr)
            return
        }

        queue.addFirst(a[i - 1])
        func1(i - 1, j - 1, arr)
    }
}