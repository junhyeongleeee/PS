package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private val stack = java.util.ArrayDeque<Int>()
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()              // 1 <= N <= 1_000_000
    arr = IntArray(n)

    st = StringTokenizer(readLine())
    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    for (i in 0 until n) {

        while (stack.isNotEmpty() && arr[stack.peek()] < arr[i]) {
            arr[stack.pop()] = arr[i]
        }

        stack.push(i)
    }

    while (stack.isNotEmpty()) {
        arr[stack.pop()] = -1
    }

    arr.forEach { sb.append("$it ") }

    println(sb)
}