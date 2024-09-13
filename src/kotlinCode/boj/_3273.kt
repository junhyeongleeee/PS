package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer

fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()

    val arr = IntArray(n)

    st = StringTokenizer(readLine())
    repeat(n) {
        arr[it] = st.nextToken().toInt()
    }

    val x = readLine().toInt()

    arr.sort()

    var s = 0
    var e = n - 1

    var result = 0

    while (s < e) {
        if(arr[e] + arr[s] < x) {
            s++
        }else if(arr[s] + arr[e] > x) {
            e--
        }else{
            result++
            e--
            s++
        }
    }
    println(result)
}