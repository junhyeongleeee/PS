package practice

/**
 *  1 <= wall <= 500
 *
 *  H : 홀드
 *  . : 빈칸
 *  X : 막힌 칸
 *
 *  시작 위치에서 출발하여 각 칸의 홀드까지 가기 위해 거쳐야 하는 최소 홀드 개수수
 */
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private val queue = java.util.ArrayDeque<Works4>()

data class Works4(val x: Int, val y: Int, val d: Int)

private lateinit var dist: Array<IntArray>
private val wall = arrayOf(
    "H.H", ".HX", "H.H"
)

fun main() {
    val row = wall.size
    val col = wall[0].length

    dist = Array(row) { IntArray(col) }
}

fun bfsWorks4(r: Int, c: Int) {
    queue.add(Works4(0, r, 1))
    dist[r][0] = 1

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.x
        val y = q.y
        val d = q.d

        // 인접
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (possible(nx, ny, r, c)) {
                if (dist[ny][nx] == 0) {
                    queue.add(Works4(nx, ny, d + 1))
                } else {
                    if (dist[ny][nx] > dist[y][x] + 1) {
                        queue.add(Works4(nx, ny, d + 1))
                    }
                }
            }
        }

        // up +
        val nx1 = x
        val ny1 = y + 2

        if (possible(nx1, ny1, r, c)) {
            if (wall[ny1 - 1][nx1] == '.') {
                queue.add(Works4(nx1, ny1, d + 1))
            }
        }

        // right
        val nx2 = x + 2
        if (possible(nx2, y, r, c) && possible(nx2, y + 1, r, c)) {
            if (wall[y][x + 1] == '.'
                && wall[y + 1][x] == '.'
                && wall[y + 1][x + 1] == '.'
                && wall[y + 1][x + 2] == '.'
            ) {
                queue.add(Works4(nx1, y, d + 1))
            }
        }
        // right +
        val nx3 = x + 3
        if (possible(nx3, y, r, c) && possible(nx3, y + 1, r, c)) {
            if (wall[y][x + 1] == '.'
                && wall[y + 1][x] == '.'
                && wall[y + 1][x + 1] == '.'
                && wall[y + 1][x + 2] == '.'
                && wall[y + 1][x + 3] == '.'
            ) {
                queue.add(Works4(nx1, y, d + 1))
            }
        }
        // right + up
        val nx4 = x + 1
        val ny4 = y - 1
        if (possible(nx4, ny4, r, c)) {
            if (wall[ny4][x] == '.' && wall[y][nx4] == '.') {
                queue.add(Works4(nx4, ny4, d + 1))
            }
        }
        // left + up
        val nx5 = x - 1
        val ny5 = y + 1
        if (possible(nx5, ny5, r, c)) {
            if (wall[ny5][x] == '.' && wall[y][nx5] == '.') {
                queue.add(Works4(nx5, ny5, d + 1))
            }
        }
    }
}

fun possible(x: Int, y: Int, r: Int, c: Int) =
    x >= 0 && y >= 0 && x < c && y < r && wall[y][x] == 'H'