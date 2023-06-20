package kotlinCode.boj

private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(-2, -1, 1, 2, -2, -1, 1, 2)
private val dy = intArrayOf(-1, -2, -2, -1, 1, 2, 2, 1)

data class Chess(val x: Int, val y: Int)

private var queue = java.util.ArrayDeque<Chess>()

private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()

    repeat(t) {
        val l = readLine().toInt()
        val (sy, sx) = readLine().split(" ").map { it.toInt() }
        val (ey, ex) = readLine().split(" ").map { it.toInt() }

        sb.append("${solve7562(Chess(sx, sy), Chess(ex, ey), l)}\n")
    }
    println(sb)
}
fun solve7562(start: Chess, end: Chess, size: Int): Int {
    arr = Array(size) { IntArray(size) { Int.MAX_VALUE } }
    queue = java.util.ArrayDeque<Chess>()
    queue.add(start)
    arr[start.y][start.x] = 0

    while (!queue.isEmpty()) {
        val q = queue.poll()
        val x = q.x
        val y = q.y

        if (x == end.x && y == end.y) {
            return arr[y][x]
        }

        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > size - 1 || ny > size - 1) continue
            if (arr[ny][nx] <= arr[y][x] + 1) continue

            arr[ny][nx] = arr[y][x] + 1
            queue.add(Chess(nx, ny))
        }
    }
    return 0
}