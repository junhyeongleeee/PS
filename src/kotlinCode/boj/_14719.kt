package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val (h, w) = readLine().split(" ").map { it.toInt() }

    st = StringTokenizer(readLine())

    val arr = IntArray(w)

    var max = Int.MIN_VALUE
    var idx = 0
    var result = 0

    repeat(w) {
        val n = st.nextToken().toInt()
        arr[it] = n
        if (max < n) {
            max = n
            idx = it
        }
    }

//    println("max: $max")

    var lm = Int.MIN_VALUE
    for (i in 0 until idx) {
        if (lm <= arr[i]) {
            lm = arr[i]
        }else {
//            println("lm: $lm i: $i")
            result += lm - arr[i]
        }
    }

    var rm = Int.MIN_VALUE
    for (i in w - 1 downTo  idx) {
        if (rm <= arr[i]) {
            rm = arr[i]
        }else {
//            println("rm: $rm i: $i")
            result += rm - arr[i]
        }
    }

    println(result)
}