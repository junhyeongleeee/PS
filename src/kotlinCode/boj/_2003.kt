package kotlinCode.boj

import java.util.StringTokenizer

fun main() {
    var input = readln().split(" ").map { it.toInt() }
    val N = input[0]
    val M = input[1]

    val st = StringTokenizer(readln())

    val arr = IntArray(N)

    repeat(N) {
        arr[it] = st.nextToken().toInt()
    }

    var left = 0
    var right = 0
    var sum = 0
    var result = 0
    while (true) {
        if (sum == M) {
            sum -= arr[left]
            result++
            left++
        }
        else if (sum > M) {
            sum -= arr[left]
            left++
        }
        else if (right == N) {
            break
        }
        else {
            sum += arr[right++]
        }
    }
    println(result)
}