package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max


/**
 *  제한 시간 : 1초
 *  메모리 제한 : 512 MB
 *
 *
 *  풀이 방식
 *      - 코딩지의 면적 즉 가로와 세로의 길이만 알면 문제를 풀 수 있다.
 *      - 몇번 겹치는 지, 이어져 있는지를 확인하면 됨.
 *      - 입력값을 어떻게 저장할 지?
 *      - 가로 길이 재기, 새로 길이 재기
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()      // 1..1000

    val array = Array<MutableList<Pair<Int, Int>>>(366) { mutableListOf() }

    val width = BooleanArray(366)
    val height = IntArray(366)

    repeat(n) {
        st = StringTokenizer(readLine())

        val s = st.nextToken().toInt()      // 1..365
        val e = st.nextToken().toInt()      // 1..365

        array[s].add(Pair(s, e))
    }

    repeat(366) {
        val data = array[it]

        data.forEach {
//            println("data: ${data.joinToString(" ")} s: ${it.first} e: ${it.second}")
            (it.first .. it.second).forEach {
                width[it] = true
                height[it]++
            }
        }
    }

//    println("width: ${width.joinToString(" ")}")
//    println("height: ${height.joinToString(" ")}")

    var max = 0
    var cnt = 0
    var result = 0

    for (i in 1 .. 365) {
        if (!width[i]) {
//            println("ccccc result: $result cnt: $cnt max: $max")
            result += cnt * (max)
            max = 0
            cnt = 0
            continue
        }

        max = max(height[i], max)
        cnt++

//        println("result: $result cnt: $cnt max: $max")
    }

    if (width[365]) {
//        println("result: $result cnt: $cnt max: $max")
        result += cnt * (max)
    }

    println(result)
}