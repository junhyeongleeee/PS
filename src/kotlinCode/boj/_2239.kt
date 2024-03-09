package kotlinCode.boj

import kotlin.system.exitProcess

private var blankSize = 0
fun main() = with(System.`in`.bufferedReader()) {

    val sudoku = Array(9) { IntArray(9) }

    repeat(9) { i ->
        val line = readLine()
        line.forEachIndexed { j, c ->
            val n = c - '0'
            sudoku[i][j] = n
            if (n == 0) {
                blankSize++
            }
        }
    }

    solve2239(sudoku, 0, 0)


}

/**
 *  103000509
 *  002109400
 *  000704000
 *  300502006
 *  060000050
 *  700803004
 *  000401000
 *  009205800
 *  804000107
 */

fun solve2239(sudoku: Array<IntArray>, i: Int, j: Int) {

    if (i == 8 && j == 9) {
        sudoku.forEach {
            println(it.joinToString(""))
        }
        // 정해진 순간 바로 종료
        exitProcess(0)
    }

    if (j == 9) {
//        println("ni: ${i + 1}")
        solve2239(sudoku, i + 1, 0)
        return
    }

    for (k in j until 9) {
        // 이미 숫자가 있다면 바로 다음으로 넘어감
        if (sudoku[i][k] != 0) {
            if (k == 8) {
                solve2239(sudoku, i, k + 1)
            }
            continue
        }

        // i 행 검사, j열 검사, (i, j) 포함된 사각형 검사
        // 존재하는 숫자 index 에 true 값 넣어줌.
        val arr = BooleanArray(10)

        for (m in 0 until 9) {
            arr[sudoku[m][k]] = sudoku[m][k] != 0
            arr[sudoku[i][m]] = sudoku[i][m] != 0
        }

        val y = i / 3 * 3
        val x = k / 3 * 3

        for (m in y until y + 3) {
            for (n in x until x + 3) {
                arr[sudoku[m][n]] = sudoku[m][n] != 0
            }
        }

        var flag = false
        for (m in 1..9) {
            // 정해지지 않은 부분
            if (!arr[m]) {
                flag = true
//                println("=========================================")
//                println("i: $i")
//                println("=========================================")
                sudoku[i][k] = m

//                sudoku.forEach {
//                    println(it.joinToString(""))
//                }
                sudoku[i][k] = m
                solve2239(sudoku, i, k + 1)
                sudoku[i][k] = 0
                flag = false
            }
        }
        // 아무런 숫자도 넣을 수 없으면 다음으로 넘어가지 않고 멈춤
        if (!flag) {
            break
        }
    }
}