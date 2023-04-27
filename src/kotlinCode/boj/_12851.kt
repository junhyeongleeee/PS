package kotlinCode.boj

/**
 *  숨바꼭질2
 */
private const val INF = 100_000
private val dist = IntArray(INF + 1) { Int.MAX_VALUE }
private val queue = java.util.ArrayDeque<Int>()
private var result = 1
private var min = Integer.MAX_VALUE
fun main() = with(System.`in`.bufferedReader()) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    solve12851(n, k)
    println(dist[k])
    println(result)
}

fun solve12851(s: Int, e: Int) {
    queue.add(s)
    dist[s] = 0
    while (!queue.isEmpty()) {
        val x = queue.poll()

        if(x == e) {
            if(min > dist[x]) {
                min = dist[x]
            }
            else if(min == dist[x]) {
                result++
            }
            continue
        }

        // x + 1
        if (x < INF) {
            checkDist(x + 1, x, e)
        }
        // x - 1
        if (x > 0) {
            checkDist(x - 1, x, e)
        }
        // 2 * x
        if (INF / 2 >= x) {
            checkDist(x * 2, x, e)
        }
    }
}

fun checkDist(nx: Int, x: Int, e: Int) {
    if (dist[nx] >= dist[x] + 1) {
        dist[nx] = dist[x] + 1
        queue.add(nx)
    }
}