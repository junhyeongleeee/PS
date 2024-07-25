import java.util.*

fun solveRiceCakeProblem(N: Int, cakes: List<List<Int>>): List<Int> {
    val dp = Array(N) { IntArray(10) { -1 } }
    val prev = Array(N) { IntArray(10) { -1 } }

    // 첫 번째 날 초기화
    for (cake in cakes[0]) {
        dp[0][cake] = cake
    }

    // DP 테이블 채우기
    for (day in 1 until N) {
        for (todayCake in cakes[day]) {
            for (yesterdayCake in 1..9) {
                if (dp[day-1][yesterdayCake] != -1 && yesterdayCake != todayCake) {
                    dp[day][todayCake] = todayCake
                    prev[day][todayCake] = yesterdayCake
                    break
                }
            }
        }
    }

    // 해결책 찾기
    val solution = MutableList(N) { 0 }
    var lastCake = dp[N-1].indexOfFirst { it != -1 }
    if (lastCake == -1) return listOf(-1)

    for (day in N-1 downTo 0) {
        solution[day] = lastCake
        lastCake = prev[day][lastCake]
    }

    return solution
}

fun main() {
    val reader = System.`in`.bufferedReader()
    val writer = System.out.bufferedWriter()

    val N = reader.readLine().toInt()
    val cakes = List(N) {
        reader.readLine().split(" ").map { it.toInt() }.drop(1)
    }

    val result = solveRiceCakeProblem(N, cakes)

    result.forEach { writer.write("$it\n") }

    writer.flush()
    writer.close()
    reader.close()
}