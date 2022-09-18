package kotlinCode.programmers

import kotlin.math.abs


/**
 *
 *  2020 KAKAO BLIND RECRUITMENT - 자물쇠와 열쇠
 *      - key 는 회전과 이동 가능
 *
 */
fun main() {
    val solution = solution(
        arrayOf(
            intArrayOf(0, 0, 0),
            intArrayOf(1, 0, 0),
            intArrayOf(0, 1, 1)
        ),
        arrayOf(
            intArrayOf(1, 1, 1),
            intArrayOf(1, 1, 0),
            intArrayOf(1, 0, 1)
        )
    )

    println(solution)
}

fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
    var answer = false

    val dx = intArrayOf(0, -1, 1, 0)
    val dy = intArrayOf(-1, 0, 0, 1)

    var tmpKey : Array<IntArray> = key.clone()

    // 회전
    repeat(4) {
        var ny = 0
        var nx = 0
        // 방향
        repeat(4) { v ->
            // 횟수
            ny += dy[v]
            nx += dx[v]

            repeat(4) { v2 ->
                var checkLIst = tmpKey

                val nny = ny + dy[v2]
                val nnx = nx + dx[v2]

                repeat(key.size - 1) {

                    val tmp = Array(key.size) { IntArray(key[0].size) }

                    // 방향에 따라 옮기기
                    checkLIst.forEachIndexed { i, ints ->
                        ints.forEachIndexed { j, int ->
                            val ni = i + nny
                            val nj = j + nnx
                            if (ni >= 0 && nj >= 0 && ni < key.size && nj < key[0].size) {
                                tmp[ni][nj] = int
                            }
                        }
                    }
                    println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")
                    tmp.forEach { println(it.joinToString(",")) }
                    println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")

                    if (check(tmp, lock)) {
                        return true
                    }
                    checkLIst = tmp
                }
            }


        }
        val tmp = Array(key.size) { IntArray(key[0].size) }

        // 회전하기
        tmpKey.forEachIndexed { i, ints ->
            ints.forEachIndexed { j, int ->
                tmp[j][abs(i - 2)] = int
            }
        }
        tmpKey = tmp.clone()

        println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ 회전 ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ")
        tmpKey.forEach { println(it.joinToString(",")) }
        println()
    }


    return answer
}

fun check(key: Array<IntArray>, lock: Array<IntArray>) : Boolean{
    key.forEachIndexed { i, ints ->
        ints.forEachIndexed { j, int ->
            if (int + lock[i][j] == 2 || int + lock[i][j] == 0) return false
        }
    }
    return true
}