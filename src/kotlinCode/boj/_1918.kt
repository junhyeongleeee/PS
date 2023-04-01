package kotlinCode.boj

/**
 *  후위 표기식
 */
fun main() = with(System.`in`.bufferedReader()) {
    val input = readLine()
    val sb = StringBuilder()
    val stack = java.util.ArrayDeque<Char>()

    input.forEach {
        when (it) {
            '+', '-', '*', '/' -> {
                while (!stack.isEmpty() && priority(stack.peek()) >= priority(it)) {
                    sb.append(stack.pop())
                }
                stack.push(it)
            }
            '(' -> {
                stack.push(it)
            }
            ')' -> {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop())
                }
                stack.pop()
            }
            else -> {
                sb.append(it)
            }
        }
    }
    while (!stack.isEmpty()) {
        sb.append(stack.pop())
    }
    println(sb)
}

fun priority(c: Char) = when (c) {
    '*', '/' -> 1
    '+', '-' -> 0
    else -> -1
}