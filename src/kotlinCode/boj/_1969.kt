package kotlinCode.boj

/**
 *  DNA
 *  - N, M ( N <= 1000, M <= 50)
 */

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()
private lateinit var alphabet: Array<IntArray>

fun main() = with(br) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    val list = mutableListOf<String>()

    alphabet = Array(m) { IntArray(26) }

    repeat(n) {
        list.add(readLine())
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            alphabet[j][(list[i][j] - 65).code]++
        }
    }

//    alphabet.forEach { println(it.joinToString(" ")) }

    var result = ""
    var cnt = 0
    for (i in 0 until m) {
        var max = 0
        var index = 0
        for (j in 25 downTo 0) {
            if (max <= alphabet[i][j]) {
                max = alphabet[i][j]
                index = j

            }
        }
//        println("max: $max")
        result += (index + 65).toChar()
    }

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (result[j] != list[i][j]) cnt++
        }
    }

    println(result)
    println(cnt)
}