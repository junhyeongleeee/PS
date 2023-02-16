package kotlinCode.boj

import java.util.*

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
private val dx = intArrayOf(1, -1)
private val queue = ArrayDeque<Int>()
private val dq = ArrayDeque<Pair<Int, Int>>()
private const val MAX_SIZE = 100001
private var arr = IntArray(MAX_SIZE) { Integer.MAX_VALUE }
private val visited = BooleanArray(MAX_SIZE)
fun main() = with(br) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    bfs13549(n)
    // standard13549(n, k)
    println(arr[k])
}

fun standard13549(s: Int, k: Int) {
    dq.add(Pair(s, 0))
    visited[s] = true
    var count = 0
    while (!queue.isEmpty()) {
        val q = dq.poll()
        val index = q.first
        val cnt = q.second

        if (cnt == k) {
            count = cnt
            break
        }

        if (index * 2 <= 100000 && !visited[index * 2]) {
            dq.addLast(Pair(index * 2, cnt))
            visited[index * 2] = true
        }
        if (index - 1 >= 0 && !visited[index - 1]) {
            dq.addLast(Pair(index - 1, cnt + 1))
            visited[index - 1] = true
        }
        if (index + 1 <= 100000 && !visited[index + 1]) {
            dq.addLast(Pair(index + 1, cnt + 1))
            visited[index + 1] = true
        }
    }
    println(count)
}

fun bfs13549(s: Int) {
    queue.add(s)
    arr[s] = 0
    while (!queue.isEmpty()) {
        val q = queue.poll()
        // 순간이동
        if (q != 0) {
            var i = q
            while (true) {
                val ni = i * 2
                if (ni > MAX_SIZE - 1) break
                if (arr[ni] > arr[i]) {
                    arr[ni] = arr[i]
                    queue.add(ni)
                }
                i = ni
            }
        }
        // 걷기
        for (i in 0 until 2) {
            val nx = q + dx[i]
            if (nx < 0 || nx >= MAX_SIZE) continue
            if (arr[nx] > arr[q] + 1) {
                arr[nx] = arr[q] + 1
                queue.add(nx)
            }
        }
    }
}