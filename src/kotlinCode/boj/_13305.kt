package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    st = StringTokenizer(readLine())

    val distances = IntArray(n - 1)
    repeat(n - 1) {
        distances[it] = st.nextToken().toInt()
    }

    val cities = IntArray(n)
    st = StringTokenizer(readLine())
    repeat(n) {
        cities[it] = st.nextToken().toInt()
    }

    var result = 0L
    var c = cities[0]
    var d = distances[0].toLong()

    for (i in 1 until n - 1) {
        if(c >= cities[i]) {
            result += (c.toLong() * d)
            c = cities[i]
            d = distances[i].toLong()
        }else {
            d += distances[i]
        }
    }

    result += (c * d)

    println(result)
}