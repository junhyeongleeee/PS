package kotlinCode.boj

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var result = 0
    for (i in 1..n) {
        val list = mutableListOf<Int>()
        var tmp = i
        while (tmp > 0) {
            list += tmp % 10
            tmp /= 10
        }
        when (list.size) {
            1, 2 -> {
                result++
                continue
            }
            else -> {
                val diff = list[0] - list[1]
                for (j in 2 until list.size) {
                    val nDiff = list[j - 1] - list[j]
                    if (diff != nDiff) {
                        result--
                        break
                    }
                }
                result++
            }
        }
    }
    println(result)
}