package kotlinCode.boj

import java.util.StringTokenizer


/**
 *  알고리즘 : 브루트포스
 *
 *  (m^2 + n^2)(x^2 + y^2) = p^2 + q^2
 *
 *  - 은행 수의 범위를 고려하여 가능한 수 set 에 저장 (m^2 + n^2 < 20_000)
 *  - 식 재구성 -> x^2 + y^2 = (p^2 + q^2) / (m^2 + n^2)
 *  - 입력 받은 은행수를 대입 후 식이 성립하는 x, y 찾기
 */

private lateinit var st: StringTokenizer
private val sb = StringBuilder()

fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    val set = mutableSetOf<Int>()

//    for (i in 0 until 100) {
//        for (j in 0 until 100) {
//            set.add(i * i + j * j)
//        }
//    }
    for (i in 0 .. m) {
        for (j in 0 .. m) {
            val sum = i * i + j * j
            if (sum >= 20_000) continue
            set.add(sum)
        }
    }
    set.remove(0)
    set.remove(1)

    val list = set.sorted()

    repeat(t) {
        st = StringTokenizer(readLine())
        val m = st.nextToken().toInt()
        val n = st.nextToken().toInt()

        val sum = m * m + n * n
        var result = true
        for (num in list) {
            if (sum != num && sum % num == 0) {
                if (list.any { it == (sum / num) }) {
                    result = false
                    break
                }
            }
        }
        sb.appendLine(if (result) "P" else "C")
    }
    println(sb)
}