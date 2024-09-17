package kotlinCode.boj

import java.util.StringTokenizer

fun main() {
    `10942`().solution()
}

class `10942` {
    lateinit var st: StringTokenizer
    val sb = StringBuilder()

    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()                  // <= 2_000
        st = StringTokenizer(readLine())

        val arr = IntArray(n + 1)
        val pailndrome = Array(2001) { BooleanArray(2001) }

        repeat(n) {
            arr[it + 1] = st.nextToken().toInt()        // <= 100_000
        }

        val m = readLine().toInt()                  // <= 1_000_000

        for (i in 1..n) {
            func1(i, i, arr, pailndrome)
        }

        for (i in 1 .. n - 1) {
            func1(i, i + 1, arr, pailndrome)
        }


        repeat(m) {
            st = StringTokenizer(readLine())

            val s = st.nextToken().toInt()
            val e = st.nextToken().toInt()

            sb.appendLine(if (pailndrome[s][e]) 1 else 0)
        }

        println(sb)
    }

    fun func1(l: Int, r: Int, arr: IntArray, pailndrome: Array<BooleanArray>) {
        var s = l
        var e = r
        while (s >= 1 && e < arr.size && arr[s] == arr[e]) {
            pailndrome[s][e] = true
            s--
            e++
        }
    }
}