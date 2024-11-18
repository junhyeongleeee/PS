package kotlinCode.boj

class `1302` {
    fun solution() = with(System.`in`.bufferedReader()){
        val n = readLine().toInt()          // <= 1,000
        val map = hashMapOf<String, Int>()

        repeat(n) {
            val book = readLine()
            map[book] = map.getOrDefault(book, 0) + 1
        }
        println(map.toList().sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first })[0].first)
    }
}

fun main() {
    `1302`().solution()
}