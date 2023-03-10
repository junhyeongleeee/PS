package kotlinCode.boj

/**
 *  파도반 수열
 *  1 <= n <= 100
 */
private val arr = LongArray(101)
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val t = readLine().toInt()
    arr[1] = 1
    arr[2] = 1
    arr[3] = 1
    arr[4] = 2
    arr[5] = 2
    for (i in 6 ..  100) {
        arr[i] = arr[i - 1] + arr[i - 5]
    }
    repeat(t) { i ->
        sb.append(arr[readLine().toInt()]).append("\n")
    }
    println(sb)
}