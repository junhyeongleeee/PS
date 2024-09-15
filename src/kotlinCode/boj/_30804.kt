package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.max

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    st = StringTokenizer(readLine())

    val set = mutableSetOf<Int>()
    val arr = IntArray(n)
    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }


    var i = 0
    var p = 0
    var c = arr[0]
    var count = 0
    var answer = 0

    while (i < n) {

        set.add(arr[i])
        count++

        if(set.size > 2) {
            set.clear()
            set.add(arr[i])
            set.add(arr[p])
            count = i - p + 1
        }

        if (c != arr[i]) {
            p = i
            c = arr[i]
        }

        i++
        answer = max(answer, count)
    }

    println(answer)
}