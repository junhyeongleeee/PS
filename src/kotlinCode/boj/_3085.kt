package kotlinCode.boj

import kotlin.math.max

private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)
private lateinit var candies: Array<CharArray>
private var answer = 0

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    candies = Array(n) { CharArray(n) }

    repeat(n) {
        candies[it] = readLine().toCharArray()
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            func1(i, j, n)
        }
    }

    println(answer)
}

fun func1(i: Int, j: Int, n: Int) {
    for (k in 0 until 4) {
        val nx = j + dx[k]
        val ny = i + dy[k]
        if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) continue

        if(candies[i][j] != candies[ny][nx]) {
            // 바꾸기

            func3(j, i, nx, ny)

            func2(n)

            func3(j, i, nx, ny)
        }
    }
}
fun func3(x: Int, y: Int, nx: Int, ny: Int) {
    val tmp = candies[y][x]
    candies[y][x] = candies[ny][nx]
    candies[ny][nx] = tmp
}

fun func2(n: Int) {

    for(y in 0 until n) {
        for(x in 0 until n) {
            val c = candies[y][x]

            for (k in 0 until 4) {
                var cnt = 1
                while (true) {
                    val nx = x + dx[k] * cnt
                    val ny = y + dy[k] * cnt
                    if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) break
                    if (candies[ny][nx] != c) break
                    cnt++
                }
//                println("cnt: $cnt")
                answer = max(answer, cnt)
            }
        }
    }

}