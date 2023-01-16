package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer


/**
 *
 *   솜바꼭질 3
 *
 *   수빈이는 현재 점 N 에 있고, 동생은 점 K 에 있다.
 *   수빈이는 걷거나 순간이동을 할 수 있다.
 *   수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
 *   순간이동을 하는 경우에는 0초 후에 2*X 위치로 이동하게 된다.
 *
 *   수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지?
 *
 *   0 <= N, K <= 100,000
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var st: StringTokenizer
private val dx = intArrayOf(1, -1, 2)
private val queue = LinkedList<Int>()
private var visited = BooleanArray(100001)
private var arr = IntArray(100001)

fun main() = with(br) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    // 0
    val result = bfs13549(n, k)
    if (result != -1) {
        println(result)
    }
    
}

fun bfs13549(start: Int, end: Int): Int {
    queue.add(start)
    visited[start] = true

    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (q == end) return arr[q]

        when (q) {
            0 -> check(q + 1, arr[q] + 1)
            100000 -> check(q - 1, arr[q] + 1)
            in 50001 until 100000 -> {
                check(q - 1, arr[q] + 1)
                check(q + 1, arr[q] + 1)
            }
            else -> {
                check(q - 1, arr[q] + 1)
                check(q + 1, arr[q] + 1)
                queue.add(q*2)
                visited[q*2] = true
                arr[q*2] = arr[q]
            }
        }
    }
    return -1
}

fun check(np: Int, s: Int) {
    if (visited[np] || arr[np] > s) return
    queue.add(np)
    visited[np] = true
    arr[np] = s
}