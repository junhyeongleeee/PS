package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var arr: CharArray
private lateinit var answer: CharArray
private var length = 0

private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val (l, c) = readLine().split(" ").map { it.toInt() }
    length = l
    arr = CharArray(c)
    answer = CharArray(l)
    st = StringTokenizer(readLine())
    repeat(c) {
        arr[it] = st.nextToken().single()
    }

    arr.sort()

    solve1759(0, 0, c)

    println(sb)

}
fun solve1759(idx: Int, cnt: Int, size: Int) {
    if (cnt == length) {
        // 최소 한개 모음 (a, e, i, o, u), 두개 자음
        var g = 0
        var co = 0
        for (c in answer) {
            when(c) {
                'a','e','i','o','u' -> {
                    g++
                }
                else -> {
                    co++
                }
            }
        }
        if (g >= 1 && co >= 2) {
            sb.append(answer.joinToString("") + "\n")
        }
        return
    }

    for (i in idx until size) {
        answer[cnt] = arr[i]
        solve1759(i + 1, cnt + 1, size)
    }
}