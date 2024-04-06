package kotlinCode.boj

import java.util.StringTokenizer


/**
 *  N <= 1_000_000
 *
 *  - 그리디 ? 스택 ?
 *
 *
 *  - 256 MB ->
 *      128MB = 128 * 1024KB = 128 * 1024 * 1024B = int형 128 * 1024 * 1024 / 4개 = 33_554_432
 *
 *      약 6천7백만 개
 *      map 사용 가능
 */

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    val arr = IntArray(n)
    val map = hashMapOf<Int, Int>()
    var result = 0

    st = StringTokenizer(readLine())
    repeat(n) {
        val num = st.nextToken().toInt()
        arr[it] = num

        if (map.getOrDefault(num + 1, 0) >= 1) {
            map[num + 1] = map.getOrDefault(num + 1, 0) - 1
        }else {
            result++
        }

        map[num] = map.getOrDefault(num, 0) + 1
    }

    println(result)
}