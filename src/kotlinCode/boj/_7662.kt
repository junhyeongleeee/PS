package kotlinCode.boj

import java.util.Collections
import java.util.PriorityQueue
import java.util.StringTokenizer

/**
 *  이중 우선 순위 큐
 *  - 삽입
 *  - 삭제
 *      - 우선 순위가 가장 높은 것을 삭제
 *      - 우선 순위가 가장 낮은 것을 삭제
 *
 *  - 정수만 저장하는 이중 우선순위 큐, 정수 값 자체가 우선순위
 *  - 일련의 연산 후 최종적으로 Q에 저장된 데이터 중 최댓값과 최솟 값 출력
 *
 *  정수 k <= 1,000,000
 *  - 'I' : 삽입
 *  - 'D' (양수): 최댓값 삭제
 *  - 'D' (음수): 최솟값 삭제
 *
 *  - Priority Queue vs TreeMap
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer

fun main() = with(br){
    val t = readLine().toInt()

    // 테스트
    repeat(t) {
        val k = readLine().toInt()
        val minQueue = PriorityQueue<Int>()
        val maxQueue = PriorityQueue<Int>(Collections.reverseOrder())
        val map = mutableMapOf<Int, Int>()
        // 연산
        repeat(k) {
            st = StringTokenizer(readLine())
            val op = st.nextToken()
            val num = st.nextToken().toInt()
            when(op) {
                "I" -> {
                    minQueue.add(num)
                    maxQueue.add(num)
                    map[num] = map.getOrDefault(num, 0) + 1
                }
                "D" -> {
                    if (map.isEmpty()) return@repeat
                    // 최솟값 삭제
                    if(num < 0) {
                        remove7662(minQueue, map)
                    }
                    else {
                        remove7662(maxQueue, map)
                    }
                }
            }
        }

        if (map.isEmpty()) {
            println("EMPTY")
        }
        else {
            val min = remove7662(minQueue, map)
            if (map.isNotEmpty()) {
                val max = remove7662(maxQueue, map)
                println("$max $min")
            }
            else {
                println("$min $min")
            }
        }
    }
}

fun remove7662(queue: PriorityQueue<Int>, map: MutableMap<Int, Int>): Int {
    var num = 0
    while (true) {
        num = queue.poll()
        if (!map.containsKey(num)) continue
        if (map[num]!! == 1) {
            map.remove(num)
        }
        else {
            map[num] = map[num]!! - 1
        }
        break
    }
    return num
}