package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs
import kotlin.math.min

/**
 *  알고리즘 : 구현, 그리디 알고리즘
 *
 *  - 가능한 연속 코드로 만들 수 있는 비트 문자열 생성
 *  - 연산 수 계산
 *
 *  100101
 *  011100
 *
 *  (0 - 1) = 1
 *  (3 - 2) = 1
 *  (5 - 3) = 2
 *  1+1+2 = 4
 *
 *
 */

private lateinit var st: StringTokenizer
private lateinit var strings: IntArray
private lateinit var code: IntArray
private lateinit var strings2: IntArray

fun main() = with(System.`in`.bufferedReader()) {

    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()      // <= 15
    val m = st.nextToken().toInt()      // <= n

    strings = IntArray(n)
    strings2 = IntArray(n)
    code = IntArray(m)

    st = StringTokenizer(readLine())
    repeat(n) {
        strings[it] = st.nextToken().toInt()
    }

    st = StringTokenizer(readLine())
    repeat(m) {
        code[it] = st.nextToken().toInt()
    }

    var zeroCount = strings.count { it == 0 }
    var oneCount = strings.count { it == 1 }

    var czc = 0
    var coc = 0

    for (i in 0 until m) if (i % 2 == 0) czc += code[i] else coc += code[i]

    var result = Int.MAX_VALUE

    if (zeroCount == czc) {
        mapping(m, true)
        result = min(result, counting(n))
    }
    if (oneCount == czc) {
        mapping(m, false)
        result = min(result, counting(n))
    }
    println(if (result == Int.MAX_VALUE) 0 else result)
}

fun counting(n: Int): Int {
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    for (i in 0 until n) {
        if (strings[i] == strings2[i]) continue
        if (strings[i] == 1) list1.add(i)
        if (strings2[i] == 1) list2.add(i)
    }
    return list2.mapIndexed { index, i -> abs(i - list1[index]) }.sum()
}

fun mapping(m: Int, flag: Boolean) {
    var idx = 0
    var f = flag
    for (i in 0 until m) {
        for (j in 0 until code[i]) {
            strings2[idx] = if (f) 0 else 1
            idx++
        }
        f = !f
    }
}