package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  ATM
 */
private val br = System.`in`.bufferedReader()
private lateinit var st : StringTokenizer
fun main() = with(br){
    val n = readLine().toInt()
    val time = IntArray(n)
    st = StringTokenizer(readLine())
    repeat(n) {
        time[it] = st.nextToken().toInt()
    }
    time.sort()
    var answer = 0
    for (i in time.indices) {
        for (j in 0 until  time.size - i) {
            answer += time[j]
        }
    }
    println(answer)
}