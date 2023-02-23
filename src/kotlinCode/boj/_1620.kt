package kotlinCode.boj

/**
 *
 */

private val br = System.`in`.bufferedReader()
private val sb = StringBuilder()
fun main() = with(br){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = hashMapOf<String, Int>()
    val arr = mutableListOf<String>()
    repeat(n) {
        val name = readLine()
        map[name] = it + 1
        arr.add(name)
    }

    repeat(m) {
        val input = readLine()
        if (input.toIntOrNull() == null) {
            sb.append(map[input]!!).append("\n")
        }
        else {
            sb.append(arr[input.toInt() - 1]).append("\n")
        }
    }
    print(sb)
}