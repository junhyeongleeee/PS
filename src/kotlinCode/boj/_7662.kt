package kotlinCode.boj

import java.util.*

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
private val sb = StringBuilder()

fun main() = with(br){
    val t = readLine().toInt()

    // 테스트
    repeat(t) {
        val k = readLine().toInt()
        val treeMap = TreeMap<Int, Int>()

        // 연산
        repeat(k) {
            st = StringTokenizer(readLine())
            val op = st.nextToken()
            val num = st.nextToken().toInt()
            when(op) {
                "I" -> {
                    treeMap[num] = treeMap.getOrDefault(num, 0) + 1
                }
                "D" -> {
                    if (treeMap.isEmpty()) return@repeat

                    val v = if (num == 1) {
                        treeMap.lastKey()
                    }else {
                        treeMap.firstKey()
                    }
                    if (treeMap[v] == 1) {
                        treeMap.remove(v)
                    }else {
                        treeMap[v] = treeMap[v]!! - 1
                    }
                }
            }
        }
        if (treeMap.isEmpty()) {
            sb.append("EMPTY").append("\n")
        }
        else {
            sb.append("${treeMap.lastKey()} ${treeMap.firstKey()}").append("\n")
        }
    }
    println(sb)
}