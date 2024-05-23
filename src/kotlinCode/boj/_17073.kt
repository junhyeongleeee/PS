package kotlinCode.boj

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*


private lateinit var st: StringTokenizer
private lateinit var arr : Array<MutableList<Int>>
private lateinit var visited : BooleanArray
private var count = 0

fun main() = with(System.`in`.bufferedReader()){
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val w = st.nextToken().toLong()

    arr = Array(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)

    repeat(n - 1) {
        st = StringTokenizer(readLine())

        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()

        arr[u].add(v)
        arr[v].add(u)
    }

    solve17073(1)

    println(
        DecimalFormat("#.###").run {
            roundingMode = RoundingMode.DOWN
            format(w / count.toDouble())
        }
    )
}

fun solve17073(s: Int) {
    visited[s] = true

    var c = 0

    arr[s].forEach {
        if (visited[it]) return@forEach
        c++
        solve17073(it)
    }

    if (c == 0) {
        count++
    }
}