package kotlinCode

import java.util.StringTokenizer

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    val p = IntArray(n)
    val s = IntArray(n)
    val card = IntArray(n) { i -> i % 3 }
    val result = IntArray(n) { i -> i % 3 }

    st = StringTokenizer(readLine())
    repeat(n) { p[it] = st.nextToken().toInt() }
    st = StringTokenizer(readLine())
    repeat(n) { s[it] = st.nextToken().toInt() }

    var cnt = 0
    while (!card.contentEquals(p)) {
        val tmp = card.clone()
        for (i in 0 until n) card[i] = tmp[s[i]]
        if (card.contentEquals(result)) {
            println(-1)
            return@with
        }
        cnt++
    }
    println(cnt)
}