package kotlinCode.boj


private lateinit var arr: Array<CharArray>
private lateinit var visited: Array<Array<Array<BooleanArray>>>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

data class Bead(val rx: Int, val ry: Int, val bx: Int, val by: Int, val cnt: Int)

fun main() = with(System.`in`.bufferedReader()) {
    // 3 <= n, m <= 10
    val (n, m) = readLine().split(" ").map { it.toInt() }

    arr = Array(n) { CharArray(m) }
    visited = Array(n) { Array(m) { Array(n) { BooleanArray(m) } } }

    var rx = 0
    var ry = 0
    var bx = 0
    var by = 0
    var hx = 0
    var hy = 0
    repeat(n) { i ->
        readLine().forEachIndexed { j, c ->
            when(c) {
                'R' -> {
                    rx = j
                    ry = i
                }
                'B' -> {
                    bx = j
                    by = i
                }
                'O' -> {
                    hx = j
                    hy = i
                }
            }
            arr[i][j] = c
        }
    }
    val bead = Bead(rx, ry, bx, by, 0)

    println(solve13460(bead, hx, hy))
}

fun solve13460(bead: Bead, hx: Int, hy: Int): Int {
    val queue = java.util.ArrayDeque<Bead>()
    visited[bead.ry][bead.rx][bead.by][bead.bx] = true
    queue.add(bead)

    var result = -1
    while (!queue.isEmpty()) {
        val q = queue.poll()

        if (q.cnt > 10) {
            continue
        }

        // 구멍에 빠지는지 확인
        if (q.by == hy && q.bx == hx) {
            continue
        }
        if (q.ry == hy && q.rx == hx) {
            result = q.cnt
            break
        }

        for (i in 0 until 4) {
            // 구슬 위치
            val vx = dx[i]
            val vy = dy[i]
            val nBead = moveBead(q, vx, vy)
            if (visited[nBead.ry][nBead.rx][nBead.by][nBead.bx]) continue
            visited[nBead.ry][nBead.rx][nBead.by][nBead.bx] = true
            queue.add(nBead)
        }
    }
    return result
}
fun moveBead(bead: Bead, vx: Int, vy: Int): Bead {
    var rx = bead.rx
    var ry = bead.ry
    var bx = bead.bx
    var by = bead.by

    // 이동가능한지 여부
    var rFlag = false
    var bFlag = false

    while (true) {
        // 구멍일 경우 같이 빠지는지 확인해야 함
        if (arr[ry + vy][rx + vx] == 'O') {
            ry += vy
            rx += vx
            rFlag = true
        }
        if (arr[by + vy][bx + vx] == 'O') {
            by += vy
            bx += vx
            bFlag = true
        }

        // 벽이면 멈춰야함, 어차피 끝 구슬이 #에 먼저 도착함
        if (arr[ry + vy][rx + vx] == '#') {
            rFlag = true
            // 움직이는 방향 뒤에 바로 있다면 멈춰야함.
            if (ry - vy == by && rx - vx == bx) {
                bFlag = true
            }
        }
        if (arr[by + vy][bx + vx] == '#') {
            bFlag = true
            if (by - vy == ry && bx - vx == rx) {
                rFlag = true
            }
        }

        // 둘다 못 움직인다면 나가기
        if(rFlag && bFlag) {
            break
        }

        // 움직일 수 있는 구슬만 이동
        if (!rFlag) {
            ry += vy
            rx += vx
        }
        if (!bFlag) {
            by += vy
            bx += vx
        }
    }

    // 어차피 안움직였다면 visited 에서 걸림
    return Bead(rx, ry, bx, by, bead.cnt + 1)
}