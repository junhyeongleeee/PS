package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  미세먼지 안녕!
 *
 *  6 <= R, C <= 50
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<IntArray>
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var dustList : MutableList<Dust>
private val cleaner = mutableListOf<Int>()
private data class Dust(val x: Int, val y: Int, val amount: Int)

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val r = st.nextToken().toInt()
    val c = st.nextToken().toInt()
    val t = st.nextToken().toInt()

    arr = Array(r) { IntArray(c) }

    repeat(r) { i ->
        st = StringTokenizer(readLine())
        repeat(c) { j ->
            val n = st.nextToken().toInt()
            arr[i][j] = n
            if (n == -1) {
                cleaner.add(i)
            }
        }
    }

    for (i in 0 until t) {
        // 미세먼지 찾기
        findDust(r, c)
        // 미세먼지 확산
        spread(r, c)
        // 공기청정기 가동
        clean(r, c, cleaner[0], cleaner[1])
    }

    var result = 0
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (arr[i][j] == -1) continue
            result += arr[i][j]
        }
    }

    println(result)
}
fun findDust(r: Int, c: Int) {

    dustList = mutableListOf()

    for (i in 0 until r) {
        for (j in 0 until c) {
            if (arr[i][j] == 0 || arr[i][j] == -1) continue
            dustList.add(Dust(j, i, arr[i][j]))
        }
    }
}

fun spread(r: Int, c: Int) {
    for (dust in dustList) {
        val x = dust.x
        val y = dust.y
        val amount = dust.amount

        if (amount < 5) continue

        val na = amount / 5
        var cnt = 0
        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || ny < 0 || nx > c - 1 || ny > r - 1) continue
            if (arr[ny][nx] == - 1) continue

            arr[ny][nx] += na
            cnt++
        }
        arr[y][x] -= na * cnt
    }
}
fun clean(r: Int, c: Int, up: Int, down: Int) {
    // 위에
    // down
    for (i in up - 1 downTo  1) {
        arr[i][0] = arr[i - 1][0]
    }
    // left
    for (i in 0 until c - 1) {
        arr[0][i] = arr[0][i + 1]
    }
    // up
    for (i in 0 until  up) {
        arr[i][c - 1] = arr[i + 1][c - 1]
    }
    // right
    for (i in c - 1 downTo  2) {
        arr[up][i] = arr[up][i - 1]
    }
    arr[up][1] = 0

    // 아래
    // up
    for (i in down + 2 until  r) {
        arr[i - 1][0] = arr[i][0]
    }
    // left
    for (i in 0 until c - 1) {
        arr[r - 1][i] = arr[r - 1][i + 1]
    }
    // down
    for (i in r - 1 downTo  down + 1) {
        arr[i][c - 1] = arr[i - 1][c - 1]
    }
    // right
    for (i in c - 1 downTo  2) {
        arr[down][i] = arr[down][i - 1]
    }
    arr[down][1] = 0
}