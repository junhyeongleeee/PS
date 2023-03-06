package kotlinCode.boj

/**
 *  AC
 *  - R (뒤집기) , D (버리기)
 *  - Function -> 개수 n -> 배열에 들어있는 정수
 *
 *  1 <= T <= 100
 *  1 <= p <= 100,000
 *  0 <= n <= 100,000
 */
private val deque = java.util.ArrayDeque<Int>()
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()
    repeat(t) {
        val function = readLine()
        val n = readLine().toInt()
        val arr = readLine()

        if (n != 0) {
            arr
                .let { it.substring(1, it.length - 1) }
                .split(",")
                .map { it.toInt() }
                .forEach {
                    deque.add(it)
                }
        }
        solution5430(function)
        deque.clear()
    }
    println(sb)
}

fun solution5430(function: String) {
    var queueCheck = true
    for (op in function) {
        when (op) {
            'R' -> queueCheck = !queueCheck
            'D' -> {
                if (deque.size == 0) {
                    sb.append("error").append("\n")
                    return
                }
                if (queueCheck) {
                    deque.pollFirst()
                } else {
                    deque.pollLast()
                }
            }
        }
    }
    val dq = if (!queueCheck) {
        deque.reversed()
    }
    else {
        deque
    }
    sb.append(dq.joinToString(",", "[", "]")).append("\n")
}