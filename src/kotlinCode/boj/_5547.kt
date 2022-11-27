package kotlinCode.boj

import java.util.LinkedList
import java.util.StringTokenizer


/**
 *  일루미네이션
 *
 *  <설명>
 *   - 1미터의 정육각형이 붙어있는 상태
 *   - 상근이는 건물의 벽면을 조명으로 장식하려고 한다.
 *   외부에 보이지 않는 부분에 조명을 장식하는 것은 낭비라고 생각한다.
 *   - 상근이의 건물 위치 지도가 주어졌을 때 조명을 장식할 벽면의 길이의 합을 구하는 프로그램
 *
 *   W 와 H 가 주어진다. ( 1 <= W, H <= 100)
 *   i + 1 줄에는 W 개의 정수가 공백으로 구분되어 있다.
 *   j 번째 ( 1<= j <= w) 정수의 좌표는 (j,i) 이며, 건물이 있을 때는 1 없을 때는 0
 *
 *   지도의 가장 왼쪽 좌표 (1,1)
 *
 *   지도의 가장 왼쪽 위에 있는 정육각형의 좌표는 (1,1)이다.
 *   (x,y)의 오른족에 있는 정육각형의 좌표는 (x+1,y)이다.
 *   y가 홀수 일 때, 아래쪽에 있는 정육각형의 좌표는 (x,y+1)이다.
 *   y가 짝수 일 때, 오른쪽 아래에 있는 정육각형의 좌표는 (x,y+1)이다.
 *
 *
 *   - y 가 홀수 일 때 왼쪽 상단 , 위
 *   - y 가 짝수 일 때 위, 오른쪽 상단
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val oDx = intArrayOf(1, -1, 0, 0, -1, -1)
private val eDx = intArrayOf(1, -1, 0, 0, 1, 1)
private val dy = intArrayOf(0, 0, -1, 1, -1, 1)
private val queue = LinkedList<Pair<Int, Int>>()
private val queue2 = LinkedList<Pair<Int, Int>>()
private lateinit var visited: Array<BooleanArray>
private lateinit var visited2: Array<BooleanArray>
private lateinit var arr: Array<IntArray>
private lateinit var st: StringTokenizer

fun main() = with(br) {
    val (w, h) = readLine().split(" ").map { it.toInt() }

    arr = Array(h) { IntArray(w) }
    visited = Array(h) { BooleanArray(w) }
    visited2 = Array(h) { BooleanArray(w) }

    repeat(h) { i ->
        st = StringTokenizer(readLine())
        repeat(w) { j ->
            arr[i][j] = st.nextToken().toInt()
            if (arr[i][j] == 1) queue.add(Pair(j, i))
        }
    }

    repeat(w) { if (arr[0][it] == 0) checkOutSide(0, it) }
    repeat(w) { if (arr[h - 1][it] == 0) checkOutSide(h - 1, it) }
    repeat(h) { if (arr[it][0] == 0) checkOutSide(it, 0) }
    repeat(h) { if (arr[it][w - 1] == 0) checkOutSide(it, w - 1) }

    checkSurrounded(w, h)
    bfs5547(w, h)

    var result = 0
    arr.forEach { line -> result += line.filter { it != -1 }.sum() }
    println(result)
}

fun checkOutSide(y: Int, x: Int) {
    visited2[y][x] = true
    arr[y][x] = -1
    queue2.add(Pair(x, y))
}

fun checkSurrounded(w: Int, h: Int) {
    while (!queue2.isEmpty()) {
        val q = queue2.poll()
        val x = q.first
        val y = q.second
        repeat(6) {
            val nx = x + if (q.second % 2 == 0) {
                eDx[it]
            } else {
                oDx[it]
            }
            val ny = y + dy[it]

            if (nx !in 0 until w || ny !in 0 until h) return@repeat

            if (!visited2[ny][nx] && arr[ny][nx] == 0) {
                arr[ny][nx] = -1
                visited2[ny][nx] = true
                queue2.add(Pair(nx, ny))
            }
        }
    }
}

fun bfs5547(w: Int, h: Int) {

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.first
        val y = q.second
        var sum = 0
        repeat(6) {
            val nx = x + if (q.second % 2 == 0) {
                eDx[it]
            } else {
                oDx[it]
            }
            val ny = y + dy[it]

            if (nx !in 0 until w || ny !in 0 until h) sum++
            else {
                if (visited[ny][nx]) return@repeat
                if (arr[ny][nx] == -1) sum++
                else visited[ny][nx] = true
            }
        }
        arr[y][x] = sum
    }
}