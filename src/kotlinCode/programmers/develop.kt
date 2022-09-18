package kotlinCode.programmers

import kotlin.math.ceil


/**
 *  각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고,
 *  이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.
 *
 *  먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses
 *  각 작업의 개발 속도가 적힌 정수 배열 speeds
 */
fun main() {
    val solution = solution(
        intArrayOf(95, 90, 99, 99, 80, 99),
        intArrayOf(1, 1, 1, 1, 1, 1)
    )

    val solution2 = solution(
        intArrayOf(93, 30, 55),
        intArrayOf(1, 30, 5)
    )



}

fun solution(progresses: IntArray, speeds: IntArray): MutableList<Int> {
    var answer = mutableListOf<Int>()

    val remain = progresses.mapIndexed { index, progress ->
        ceil((100 - progress) / speeds[index].toDouble()).toInt()
    }

    var result = 0
    var sum = remain.first()
    remain.forEach {
        if (it - sum <= 0) {
            result++
        }
        else{
            answer.add(result)
            sum = it
            result = 1
        }
    }
    answer.add(result)

    println(answer.joinToString(","))
    return answer
}