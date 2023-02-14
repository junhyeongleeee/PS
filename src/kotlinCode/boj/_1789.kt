package kotlinCode.boj

/**
 *  수들의 합
 *  - 이진 탐색
 *
 */

private val br = System.`in`.bufferedReader()

fun main() = with(br){
    val s = readLine().toLong()

    var start = 0L
    var end = s
    var result = 0L
    while(start <= end) {
        val mid = (start + end) / 2
        val sum = mid * (mid + 1) / 2
        if(sum <= s) {
            result = mid
            start = mid + 1
        }
        else {
            end = mid - 1
        }
    }
    println(result)
}