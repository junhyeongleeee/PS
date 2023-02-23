package kotlinCode.boj

private val br = System.`in`.bufferedReader()
fun main() = with(br){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val map = hashMapOf<String, Int>()
    val answer = mutableListOf<String>()
    repeat(n) {
        map[readLine()] = 0
    }
    repeat(m) {
        val input = readLine()
        if (map.containsKey(input)) {
            answer.add(input)
        }
    }
    answer.sort()

    println(answer.size)
    println(answer.joinToString("\n"))
}