package kotlinCode.boj

import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *  아기 상어
 *  - 아기상어는 1초에 상하좌우로 인접한 한 칸씩 이동
 *  - 아기상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 *      - 크기가 같다면 지나갈 수 있다.
 *  - 더 이상 먹을 수 없으면 엄마에게 요청
 *
 *  - 먹을 수 있는 물고기가 1마리면 그 물고기를 먹으러 간다.
 *  - '' 여러개면 가장 가짜운 물고기를 먹으러 간다.
 *
 *  - 2 <= n <= 20
 */
private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(0, -1, 0, 1)
private val dy = intArrayOf(-1, 0, 1, 0)
private val queue = java.util.ArrayDeque<Shark>()
private lateinit var visited: Array<BooleanArray>

data class Shark(val x: Int, val y: Int, val dist: Int)

fun main() = with(br) {
    val n = readLine().toInt()

    arr = Array(n) { IntArray(n) }
    visited = Array(n) { BooleanArray(n) }
    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            val num = st.nextToken().toInt()
            arr[i][j] = num
            if (num == 9) {
                queue.add(Shark(j, i, 0))
                arr[i][j] = 0
                visited[i][j] = true
            }
        }
    }

    var answer = 0
    var eatCnt = 0
    var sharkSize = 2
    while (true) {

        val fishList = mutableListOf<Shark>()

        while (!queue.isEmpty()) {
            val q = queue.poll()

            for (i in 0 until 4) {
                val nx = q.x + dx[i]
                val ny = q.y + dy[i]
                if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue
                // 먹을 수 있는 물고기들 탐색
                if (sharkSize < arr[ny][nx] || visited[ny][nx]) continue
                visited[ny][nx] = true
                queue.add(Shark(nx, ny, q.dist + 1))
                if (arr[ny][nx] != 0 && arr[ny][nx] < sharkSize) {
                    fishList.add(Shark(nx, ny, q.dist + 1))
                }
            }
        }

        if (fishList.size == 0) {
            println(answer)
            break
        }

        // 거리, x, y 순으로 정렬
        fishList.sortWith(compareBy<Shark> { it.dist }.thenBy { it.y }.thenBy { it.x })
        val fish = fishList[0]
        // 거리 계산
        answer += fish.dist
        // 빈칸으로 초기화
        arr[fish.y][fish.x] = 0
        // 먹은 횟수가 상어 크기와 같은지 비교
        if (++eatCnt == sharkSize) {
            eatCnt = 0
            sharkSize++
        }
        queue.add(Shark(fish.x, fish.y, 0))
        visited = Array(n) { BooleanArray(n) }
        visited[fish.y][fish.x] = true
    }
}