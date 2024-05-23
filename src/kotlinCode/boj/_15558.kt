package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer
import kotlin.system.exitProcess


private lateinit var st: StringTokenizer
private lateinit var visited : Array<BooleanArray>
private lateinit var line : Array<List<Int>>

private var N = 0
private var K = 0

data class Turn(val l: Int, val c: Int, val idx: Int)

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())

    N = st.nextToken().toInt()
    K = st.nextToken().toInt()

    line = Array(2) { listOf() }
    visited = Array(2) { BooleanArray(N) }

    line[0] = readLine().map { it - '0' }
    line[1] = readLine().map { it - '0' }

    visited[0] = BooleanArray(N)
    visited[1] = BooleanArray(N)

    visited[0][0] = true

    var result = false
    val queue = ArrayDeque<Turn>()

    queue.add(Turn(0, 0, 0))

    while (queue.isNotEmpty()) {
        val q = queue.poll()

        val n1 = q.idx - 1
        val n2 = q.idx + 1
        val n3 = q.idx + K

        if (n2 >= N || n3 >= N) {
            result = true
            break
        }

        if (n1 > q.c && !visited[q.l][n1] && line[q.l][n1] != 0) {
            visited[q.l][n1] = true
            queue.add(Turn(q.l, q.c + 1, n1))
        }
        if (!visited[q.l][n2] && line[q.l][n2] != 0) {
            visited[q.l][n2] = true
            queue.add(Turn(q.l, q.c + 1, n2))
        }
        if (!visited[1 - q.l][n3] && line[1 - q.l][n3] != 0) {
            visited[1- q.l][n3] = true
            queue.add(Turn(1 - q.l, q.c + 1, n3))
        }
    }

    println(if (result) 1 else 0)
}