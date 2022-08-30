package kotlinCode

import java.beans.Expression
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.exp

/**
 *  다음과 같이 바꾸는 방법
 *      100-200*300 ... -> -100 * 300
 *      - 토큰 화 하여 숫자와 연산자를 나눈다.
 *          ex) 100,200,300,500,20 | -, *, -, +
 *          - * 우선순위가 첫 번 째라면 해당 index 가 1
 *          - 숫자 리스트의 2, 3번째를 연산 -> 200*300 ->  60000
 *          -
 */

fun main() {
    val expression = "100-200*300-500+20"
    val operations = listOf('-', '*', '+')
    val operationList = permutation(operations)
    var answer = Long.MIN_VALUE

    operationList.forEach { dfs(expression, 0, it).let { result -> answer = answer.coerceAtLeast(abs(result)) } }

}

fun dfs(expression: String, depth: Int, operations: List<Char>): Long {

    // Operand 만 있는 경우
    if (expression.isLong()) return expression.toLong()

    val tokens = expression.split(operations[depth])
    var result : Long? = null

    // token 들의 결과
    for (token in tokens) {
        val value = dfs(token, depth + 1, operations)

        result = if (result == null) value
        else calculation(result, value, operations[depth])
    }

    return result!!
}

fun String.isLong(): Boolean {
    return try {
        this.toLong()
        true
    } catch (e: Exception) {
        false
    }
}


fun calculation(num1: Long, num2: Long, operation: Char) = when (operation) {
    '-' -> num1 - num2
    '*' -> num1 * num2
    '+' -> num1 + num2
    else -> -1
}

/**
 *  순열 함수
 *      - el : 원본 데이터로서 변하지 않는다. -> elements
 *      - fin : 원소를 담는 리스트로서 기본값은 빈 리스트 -> finish
 *      - sub : fin 이 담는 리스트라면 sub 는 빼는 리스트 기본값은 el -> subtraction
 *      -> sub 의 원소를 빼서 fin 에 넣는 과정을 반복하여 sub 가 비었을 때 fin 을 반환한다.
 */
fun <T> permutation(el: List<T>, fin: List<T> = listOf(), sub: List<T> = el): List<List<T>> {
    return if (sub.isEmpty()) listOf(fin)
    else sub.flatMap { permutation(el, fin + it, sub - it) }
}