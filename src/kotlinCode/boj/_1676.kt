package kotlinCode.boj

/**
 *  팩토리얼 0의 개수
 */
private val br = System.`in`.bufferedReader()
fun main() = with(br){
    var n = readLine().toInt()
    var answer = 0
    while (n >= 5) {
        answer += n / 5
        n /= 5
    }
    println(answer)
}