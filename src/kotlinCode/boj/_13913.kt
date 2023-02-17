package kotlinCode.boj

import java.util.*
/**
 *  숨바꼭질 4
 *
 */
private val br = System.`in`.bufferedReader()
private val ad = ArrayDeque<Pair<Int, Int>>()
private val stack = ArrayDeque<Int>()
private const val MAX_VALUE = 100001
private val visited = BooleanArray(MAX_VALUE)
private val arr = IntArray(MAX_VALUE)
private val sb = StringBuilder()
fun main() = with(br) {
    val (n, k) = readLine().split(" ").map { it.toInt() }
    val cnt = find13913(n, k)
    sb.append(cnt).append("\n")
    printRoute(n, k)
    println(sb)
}
fun printRoute(n: Int, k: Int) {
    var i = k
    stack.push(k)
    while (arr[i] != n) {
        stack.push(arr[i])
        i = arr[i]
    }
    stack.push(n)
    while (!stack.isEmpty()) {
        sb.append(stack.pop()).append(" ")
    }
}

fun find13913(s: Int, k: Int): Int {
    ad.add(Pair(s, 0))
    visited[s] = true
    var result = 0
    while (!ad.isEmpty()) {
        val (i, c) = ad.poll()

        if (i == k) {
            result = c
            break
        }
        
        for (j in 0 until 3) {
            var ni = i
            when(j) {
                0 -> ni += 1
                1 -> ni -= 1
                2 -> ni *= 2
            }
            if (ni < 0 || ni > MAX_VALUE - 1) continue
            if(visited[ni]) continue
            visited[ni] = true
            arr[ni] = i
            ad.add(Pair(ni, c + 1))
        }
    }
    return result
}