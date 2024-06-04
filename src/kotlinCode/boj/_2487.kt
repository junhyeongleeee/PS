package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  [ 3, 2, 5, 6, 1, 4 ]
 *
 *  [ A1, A2, A3, A4, A5, A6 ]
 */

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    st = StringTokenizer(readLine())

    val set = mutableSetOf<Long>()
    val arr = IntArray(n + 1)
    val visited = BooleanArray(n + 1)

    repeat(n) {
        arr[it + 1] = st.nextToken().toInt()
    }

    for (i in 1 .. n) {
        var j = arr[i]
        var cnt = 1L
        if (visited[i]) continue

        while (j != i) {
            visited[j] = true
            j = arr[j]
            cnt++
        }

        set.add(cnt)
    }

    // 최소 공배수
    val list = set.toList()
    var a = list[0]
    for (i in 0 until list.size - 1) {
        a = (a * list[i + 1]) / solve2487(a, list[i + 1])
    }

    println(a)
}

fun solve2487(a: Long, b: Long): Long {
    var a = a
    var b = b
    var r = 0L
    while (b != 0L) {
        r = a % b
        a = b
        b = r
    }
    return a
}