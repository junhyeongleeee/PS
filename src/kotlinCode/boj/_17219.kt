package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  비밀번호 찾기
 */

private lateinit var st: StringTokenizer
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val map = hashMapOf<String, String>()
    repeat(n) {
        val (ad, pwd) = readLine().split(" ")
        map[ad] = pwd
    }
    repeat(m) {
        sb.append(map[readLine()]!!).append("\n")
    }
    println(sb)
}