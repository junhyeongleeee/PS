package kotlinCode.boj

/**
 *  베르트랑 공준
 *  - n보다 크고 2n보다 작거나 같은 소수는 적어도 하나 존재한다.
 */
const val MAX = 123_456 * 2
private val sb = StringBuilder()
private val visited = BooleanArray(MAX + 1)
fun main() = with(System.`in`.bufferedReader()) {

    // 에라토스테네스의 체
    for (i in 2..MAX) {
        var cnt = 2
        while (i * cnt <= MAX) {
            visited[i * cnt++] = true
        }
    }

    while (true) {
        val n = readLine().toInt()
        if (n == 0) {
            break
        }
        var cnt = 0
        for (i in n + 1 .. 2 * n) {
            if (!visited[i]) {
                cnt++
            }
        }
        sb.append("$cnt\n")
    }

    println(sb)
}