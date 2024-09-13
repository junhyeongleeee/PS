package kotlinCode.boj

import jdk.nashorn.internal.runtime.Debug
import java.util.StringTokenizer
import kotlin.math.max
import kotlin.math.min

private lateinit var st: StringTokenizer
fun main() = with(System.`in`.bufferedReader()){
    val n = readLine().toInt()
    var result = 0
    val arr = Array(101) { IntArray(101) }

    repeat(n) {
        st = StringTokenizer(readLine())

        val x = st.nextToken().toInt()
        val y = st.nextToken().toInt()

        for (i in x until x + 10) {
            for(j in y until y + 10) {
                arr[j][i] = 1
            }
        }
    }

    // 높이

    for(i in 1 .. 100) {
        for(j in 1 .. 100) {
            if(arr[i][j] == 0) continue
            arr[i][j] += arr[i - 1][j]
        }
    }

    for(i in 1 .. 100) {
        for(j in 1 .. 100) {
            var h = 100
            for(k in j .. 100) {
                h = min(h, arr[i][k])

                if (h == 0) break

                result = max(h * (k - j + 1), result)
            }
        }
    }

    println(result)
}