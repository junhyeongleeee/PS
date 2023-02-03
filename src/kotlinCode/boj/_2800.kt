package kotlinCode.boj

import java.util.*

/**
 *  괄호 제거
 *  - 괄호를 제거해서 나올 수 있는 서로 다른 식의 개수 계산
 *  - 괄호를 제거할 때는 항상 쌍이 되는 괄호끼리 제거해야 한다.
 *  - 어떤 식을 여러 쌍의 괄호가 감쌀 수 있다.
 *  - 수식 ( 숫자, +, *, -, /, (, ) ), 길이 최대 200
 *  - 쌍은 적어도 1개 많아야 10개
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val set = sortedSetOf<String>()
private lateinit var visited: BooleanArray
data class Bracket(val s: Int, val e: Int)
private val list = mutableListOf<Int>()
private val brackets = mutableListOf<Bracket>()
fun main() = with(br) {
    val mm = readLine()
    visited = BooleanArray(mm.length)
    mm.forEachIndexed { index, c ->
        if (c == '(') {
            list.add(index)
        }
        if (c == ')') {
            brackets.add(Bracket(list.removeAt(list.size - 1), index))
        }
    }
    dfs2800(mm, 0)
    set.remove(mm)
    set.forEach { bw.write(it + '\n') }
    bw.flush()
}

fun dfs2800(mm: String, idx: Int) {
    if (idx == brackets.size) {
        return
    }
    val b = brackets[idx]
    if (!visited[b.s]) {
        visited[b.s] = true
        visited[b.e] = true
        set.add(findString(mm))
        dfs2800(mm, idx + 1)
        visited[b.s] = false
        visited[b.e] = false
    }
    set.add(findString(mm))
    dfs2800(mm, idx + 1)
}

fun findString(mm: String) = mm.filterIndexed { index, _ -> !visited[index] }