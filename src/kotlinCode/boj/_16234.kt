package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.abs


/**
 *  인구 이동
 *
 *  - N x N 크기의 땅, 1 x 1 개의 칸, r행 c열에 있는 나라에는 A[r][c] 명이 산다.
 *
 *  - 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 동안 연다.
 *  - 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 *  - 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 *  - 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 *  - 연합을 해체하고, 모든 국경선을 닫는다.
 *
 *
 *
 *  -1 국경선 열기
 *  -2 연합 인구수 계산
 *  -3 모든 국경선 닫기
 *  -4 1~3 반복
 *
 *  - ( 1<= N <= 50), 1 <= L, R <= 100)
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private val dx = intArrayOf(0, 0, -1, 1)
private val dy = intArrayOf(-1, 1, 0, 0)
private var queue = LinkedList<Pair<Int, Int>>()

fun main() = with(br) {
    val (n, l, r) = readLine().split(" ").map { it.toInt() }

    visited = Array(n) { BooleanArray(n) }
    arr = Array(n) { IntArray(n) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(n) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    var result = 0
    while (true) {
        // daily
        val resultList = mutableListOf<MutableList<Pair<Int, Int>>>()
        repeat(n) { i ->
            repeat(n) { j ->
                val list = bfs(j, i, n, l, r)
                if (list.size == 1) return@repeat
                resultList.add(list)
                queue = LinkedList<Pair<Int, Int>>()
            }
        }
        if (resultList.size == 0) break

        resultList.forEach { list ->
            val sum = list.sumOf { arr[it.second][it.first] }
            list.forEach { arr[it.second][it.first] = sum / list.size }
        }

        queue = LinkedList<Pair<Int, Int>>()
        visited = Array(n) { BooleanArray(n) }
        result++
    }
    println(result)
}

fun bfs(x: Int, y: Int, n: Int, l: Int, r: Int): MutableList<Pair<Int, Int>> {
    queue.add(Pair(x, y))
    if (visited[y][x]) return mutableListOf()
    visited[y][x] = true
    val list = mutableListOf<Pair<Int, Int>>()
    list.add(Pair(x, y))
    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second
        repeat(4) {
            val nx = x + dx[it]
            val ny = y + dy[it]

            if (nx !in 0 until n || ny !in 0 until n) return@repeat
            val diff = abs(arr[y][x] - arr[ny][nx])
            if (visited[ny][nx] || diff !in l..r) return@repeat

            visited[ny][nx] = true
            queue.add(Pair(nx, ny))
            list.add(Pair(nx, ny))
        }
    }
    return list
}
