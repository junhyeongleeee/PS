package kotlinCode.boj

/**
 *  제곱수 찾기
 *  -
 *
 */

private val bw = System.out.bufferedWriter()
private val br = System.`in`.bufferedReader()

fun main() = with(br) {
    val a = readLine().toInt()
    val t = readLine().toInt()
    val b = readLine().toInt()

    println(startGame(a, t, b))
}

fun startGame(a: Int, t: Int, b: Int): Int {

    var cnt = 0
    var result = 0
    var n = 2

    while(true) {
        for (i in 0 until 4) {
            if (i % 2 == b) {
                result++
            }
            if (result == t) return cnt % a
            cnt++
        }

        repeat(n) {
            if (b == 0) result++
            if (result == t) return cnt % a
            cnt++
        }
        repeat(n) {
            if (b == 1) result++
            if (result == t) return cnt % a
            cnt++
        }
        n++
    }
}