package kotlinCode.boj

import java.util.ArrayDeque

/**
 *  알고리즘 : BFS, 시뮬레이션, 구현
 *  - 터질 뿌요들 찾기
 *  - 터뜨리기
 */

data class Puyo(val x: Int, val y: Int, val c: Int)

private val arr = Array(12) { CharArray(6) }
private lateinit var explode: Array<BooleanArray>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var queue = ArrayDeque<Puyo>()

fun main() = with(System.`in`.bufferedReader()) {
    repeat(12) { i ->
        readLine().toCharArray().forEachIndexed { j, c ->
            arr[i][j] = c
        }
    }
    var result = 0
    var flag = false
    while (!flag) {

        explode = Array(12) { BooleanArray(6) }

        for (i in 0 until 12) {
            for (j in 0 until 6) {

                if (arr[i][j] == '.') continue
                if (explode[i][j]) continue

                // 터질 뿌요 찾기 (bfs)
                // flag = flag || findPuyoToExplode(i, j) (x)
                flag = findPuyoToExplode(i, j) || flag
            }
        }
        // 터질 뿌요가 있는지 확인
        if (flag) {
            // 터뜨리기
            explodePuyo()
            result++
        }
        flag = !flag
    }

    println(result)
}

/**
 *  터뜨리기
 *  - explode 배열 위에서부터 하나씩 터뜨리기
 *
 */
fun explodePuyo() {
    for (i in 0 until 12) {
        for (j in 0 until 6) {
            if (explode[i][j]) {
                for (k in i downTo 1) {
                    arr[k][j] = arr[k - 1][j]
                }
                arr[0][j] = '.'
            }
        }
    }
}

fun findPuyoToExplode(i: Int, j: Int): Boolean {
    val color = arr[i][j]
    val visited = Array(12) { BooleanArray(6) }
    var count = 0
    queue = ArrayDeque<Puyo>()
    queue.add(Puyo(j, i, 0))
    visited[i][j] = true

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        count++

        for (k in 0 until 4) {
            val nx = q.x + dx[k]
            val ny = q.y + dy[k]

            if (nx < 0 || ny < 0 || nx > 5 || ny > 11) continue
            if (color != arr[ny][nx]) continue
            if (visited[ny][nx]) continue

            visited[ny][nx] = true
            queue.add(Puyo(nx, ny, q.c + 1))
        }
    }

    // 4개 이상 일 때, 터질 배열에 저장.
    if (count >= 4) {
        for (k in 0 until 12) {
            for (l in 0 until 6) {
                if (visited[k][l]) {
                    explode[k][l] = true
                }
            }
        }
    }
    return count >= 4
}