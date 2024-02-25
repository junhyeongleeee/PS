package kotlinCode.boj

import java.util.ArrayDeque
import java.util.StringTokenizer
import kotlin.math.max


/**
 *  제한 시간 : 1초
 *  메모리 제한 : 512 MB
 *
 *  풀이 과정
 *      - 정렬 x
 *      - 마지막 까지 계산해봐야 최대 값을 알 수 있음
 *      - 최대 값 스캔 O(n) *
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val (x, n) = readLine().split(" ").map { it.toInt() }

    st = StringTokenizer(readLine())

    var cnt = 0
    var sum = 0
    var maxValue = Int.MIN_VALUE
    val deque = ArrayDeque<Int>()
    val sumList = mutableListOf<Int>()

    repeat(x) {
        val num = st.nextToken().toInt()

        if (cnt == n) {
            // 저장
            sumList.add(sum)
            // max 계산
            maxValue = max(sum, maxValue)
            // 앞에 제거 + 합에서 빼기
            sum -= deque.pollFirst()
            cnt--
//            println("idx: $it")
        }

        deque.addLast(num)
        sum += num
        cnt++
//        println("idx: $it deque: $deque")
    }

    if (maxValue == 0) {
        println("SAD")
        return@with
    }

    sumList.add(sum)

//    println("result: $maxValue")
//    println("sumList: $sumList")

    println(maxValue)
    println(sumList.count { it == maxValue })
}