package kotlinCode.boj

import java.util.*

/**
 *  직사각형 탈출
 *
 *  1<= r <= N, 1 <= C <= M
 *  2 <= N,M <= 1,000
 *
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private lateinit var countArr: Array<IntArray>
private val wall = mutableListOf<Pair<Int, Int>>()
private val queue = ArrayDeque<Pair<Int, Int>>()
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private var H: Int = -1
private var W: Int = -1
private var sR: Int = -1
private var sC: Int = -1
private var fR: Int = -1
private var fC: Int = -1
fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n) { IntArray(m) }
    countArr = Array(n) { IntArray(m) }

    repeat(n) { i ->
        st = StringTokenizer(readLine())
        repeat(m) { j ->
            arr[i][j] = st.nextToken().toInt()
        }
    }

    st = StringTokenizer(readLine())
    H = st.nextToken().toInt()
    W = st.nextToken().toInt()
    sR = st.nextToken().toInt()
    sC = st.nextToken().toInt()
    fR = st.nextToken().toInt()
    fC = st.nextToken().toInt()

    println(bfs16973(sR - 1, sC - 1, n, m) - 1)
}

fun bfs16973(sR: Int, sC: Int, n: Int, m: Int): Int {
    queue.add(Pair(sR, sC))
    countArr[sR][sC] = 1

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val r = q.first
        val c = q.second

        if (r == fR - 1 && c == fC - 1) {
            return countArr[r][c]
        }

        for (i in 0 until 4) {
            val nr = r + dy[i]
            val nc = c + dx[i]

            // nr + H 오른쪽 끝
            if (nr < 0 || nc < 0 || nr + H > n || nc + W > m) continue
            if (countArr[nr][nc] != 0) continue
            if (!checkWar(nr, nc)) continue

            countArr[nr][nc] = countArr[r][c] + 1
            queue.add(Pair(nr, nc))
        }
    }
    return 0
}

fun checkWar(r: Int, c: Int): Boolean {
    for (i in r until r + H) {
        for (j in c until c + W) {
            if (arr[i][j] == 1) {
                return false
            }
        }
    }
    return true
}