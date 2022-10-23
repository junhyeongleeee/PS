package kotlinCode.boj

/**
 *   첫째 줄에 미로의 크기를 나타내는 가로 크기 M, 세로 크기 N (1 ≤ N, M ≤ 100)이 주어진다.
 *   다음 N개의 줄에는 미로의 상태를 나타내는 숫자 0과 1이 주어진다. 0은 빈 방을 의미하고
 *   , 1은 벽을 의미한다.
 *
 *
 * 100* 100 = 10,000
 *
 * 첫째 줄에 알고스팟 운영진이 (N, M)으로 이동하기 위해 벽을 최소 몇 개 부수어야 하는지 출력한다.
 */

val dx = arrayOf(-1, 1, 0, 0)
val dy = arrayOf(0, 0, -1, 1)

fun main() {

    val input = readln().split(" ").map { it.toInt() }
    val n = input[0]
    val m = input[1]

    val arr = Array(n) { IntArray(m) }
    repeat(n) { i ->
        val line = readln()
        repeat(m) { j ->
            arr[i][j] = line[j] - '0'
        }
    }


}