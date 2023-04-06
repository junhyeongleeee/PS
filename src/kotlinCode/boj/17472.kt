package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer
import kotlin.math.min

/**
 *  다리 만들기2
 *
 *  - 다리의 양 끝은 섬과 인접한 바다 위에 있어야 한다.
 *  - 한 다리의 방향이 중간에 바뀌면 안된다.
 *  - 방향이 가로인 다리는 다리의 양 끝이 가로 방향으로 섬과 인접해야 한다. 세로도 마찬가지.
 *  - 다리의 길이는 2 이상
 *
 *  - 두 섬을 연결하는 다리가 다른 섬을 지나면 연결되어 있는 것이 아니다.
 *
 *
 *
 *  1 <= N, M <= 10
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var island: Array<IntArray>
private val queue = ArrayDeque<Pair<Int, Int>>()
private val islands = mutableListOf<Pair<Int, Int>>()
private lateinit var visited: Array<BooleanArray>
private lateinit var edges: MutableList<Edge17472>
private lateinit var parent: IntArray

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

data class Edge17472(val v1: Int, val v2: Int, val d: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    arr = Array(n) { IntArray(m) }
    island = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            val data = st.nextToken().toInt()
            arr[i][j] = data
            if (data == 1) {
                islands.add(Pair(j, i))
            }
        }
    }

    // 섬 만들기
    var num = 1
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (visited[i][j] || arr[i][j] == 0) continue
            settingIsland(n, m, j, i, num++)
        }
    }

    edges = mutableListOf()
    parent = IntArray(num) { it }

    for (q in islands) {
        val x = q.first
        val y = q.second
        val currentIsland = island[y][x]

        for (i in 0 until 4) {
            var dis = 0
            var nx = x + dx[i]
            var ny = y + dy[i]
            var flag = false
            while (true) {
                // 범위 체크
                if (checkIndexOutOfRange(nx, ny, n, m)) {
                    break
                }
                if (currentIsland == island[ny][nx]) {
                    break
                }
                if (checkIsland(currentIsland, island[ny][nx])) {
                    flag = true
                    break
                }
                dis++
                nx += dx[i]
                ny += dy[i]
            }

            if (!flag) continue
            if (dis < 2) continue

            edges.add(Edge17472(currentIsland, island[ny][nx], dis))
        }
    }

    edges.sortBy { it.d }

    var result = 0
    var cnt = 0
    for (edge in edges) {
        if (find17472(edge.v1) == find17472(edge.v2)) continue
        union17472(edge.v1, edge.v2)
        result += edge.d
        cnt++
    }
    if (cnt != num - 2) {
        println(-1)
    }else {
        println(result)
    }
}

fun union17472(a: Int, b: Int) {
    val ap = find17472(a)
    val bp = find17472(b)
    if (a > b) {
        parent[ap] = bp
    }
    else {
        parent[bp] = ap
    }
}

fun find17472(v: Int) : Int
    = if (parent[v] == v) v else find17472(parent[v])

fun checkIsland(ci: Int, oi: Int) = oi != 0 && ci != oi

fun checkIndexOutOfRange(x: Int, y: Int, n: Int, m: Int) = x < 0 || y < 0 || x > m - 1 || y > n - 1

fun settingIsland(n: Int, m: Int, x: Int, y: Int, num: Int) {
    queue.clear()
    queue.add(Pair(x, y))
    visited[y][x] = true
    island[y][x] = num

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val cx = q.first
        val cy = q.second

        for (i in 0 until 4) {
            val nx = cx + dx[i]
            val ny = cy + dy[i]

            if (checkIndexOutOfRange(nx, ny, n, m)) continue
            if (visited[ny][nx]) continue
            if (arr[ny][nx] == 0) continue

            visited[ny][nx] = true
            queue.add(Pair(nx, ny))
            island[ny][nx] = num
        }
    }
}
