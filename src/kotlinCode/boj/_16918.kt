package kotlinCode.boj

/**
 *
 *  봄버맨
 *
 *  R X C 인 직사각형, 격자의 각 칸은 비어있거나 폭탄
 *
 *  폭탄이 있는 칸은 3초가 지난 후에 폭발한다.
 *  폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다.
 *  인접한 칸에 폭탄이 있는 경우 인접한 폭탄은 폭발 없이 파괴 된다.
 *  연쇄 반응은 없다.
 *
 *  1- 가장 처음에 폭탄 설치
 *  2- 다음 1초 동안 아무것도 안함
 *  3- 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치
 *  4- 1초가 지난 후 3초 전에 설치된 폭탄이 모두 폭발
 *  5- 3과 4를 반복
 *
 *
 *  폭탄 설치 - 모든 칸에 폭탄 설치 - 1초가 지난 후 3초전 설치된 폭탄 모두 폭발
 *   - 모든 칸에 폭탄 설치 - 1초가 지난 후 3초전 설치된 폭탄 모두 폭발
 *
 *   1 <= R, C, N <= 200
 *
 *  200 * 200 + 200*200*4 +
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(br) {
    val (r, c, n) = readLine().split(" ").map { it.toInt() }

    val arr = Array(r) { CharArray(c) }
    val allBom = Array(r) { CharArray(c) { 'O' } }

    repeat(r) { i ->
        val line = readLine()
        line.forEachIndexed { j, c ->
            arr[i][j] = c
        }
    }

    if (n == 1) {
        printArray(arr)
        return
    }

    when ((n - 1) % 2) {
        1 -> {
            printArray(allBom)
        }
        0 -> {
            if (n % 4 != 0) {
                val dots = mutableListOf<Pair<Int, Int>>()

                arr.forEachIndexed { i, chars ->
                    chars.forEachIndexed { j, c ->
                        if (c == 'O') {
                            dots.add(Pair(i, j))
                            allBom[i][j] = '.'
                        }
                    }
                }

                dots.forEach { point ->

                    repeat(4) {
                        val nx = point.second + dx[it]
                        val ny = point.first + dy[it]

                        if (nx in 0 until c && ny in 0 until r) {
                            allBom[ny][nx] = '.'
                        }
                    }
                }
                printArray(allBom)
            } else {
                printArray(arr)
            }
        }
    }
}

fun printArray(arr: Array<CharArray>) {
    arr.forEach {
        println(it.joinToString(""))
    }
}