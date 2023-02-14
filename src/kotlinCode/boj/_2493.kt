package kotlinCode.boj

import java.util.Stack
import java.util.StringTokenizer


/**
 *  탑
 *  - 자료구조
 *  - N , 1 .. 500000
 *  - H , 1 .. 100000000
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private val sb = StringBuilder()
private val stack = Stack<Pair<Int, Int>>()

fun main() = with(br){
    val n = readLine().toInt()
    st = StringTokenizer(readLine())

    stack.add(Pair(st.nextToken().toInt(), 0))
    sb.append(0).append(" ")

    for (i in 1 until n) {
        val h = st.nextToken().toInt()

        while (!stack.isEmpty()) {
            val last = stack.peek()
            if (last.first < h) {
                stack.pop()
            }
            else if(last.first == h) {
                sb.append(stack.pop().second + 1).append(" ")
            }
            else {
                sb.append(last.second + 1).append(" ")
                break
            }
        }
        if (stack.isEmpty()) {
            sb.append(0).append(" ")
        }
        stack.add(Pair(h, i))
    }

    println(sb)
}