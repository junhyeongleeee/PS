package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    `2473`().solution()
}

class `2473` {
    lateinit var st: StringTokenizer
    fun solution() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()                  // 3 <= <= 5_000

        st = StringTokenizer(readLine())

        val arr = IntArray(n)

        repeat(n) {
            arr[it] = st.nextToken().toInt()
        }

        arr.sort()

        var a = 0
        var b = 0
        var c = 0

        var answer = Long.MAX_VALUE

//        println(arr.joinToString(" "))

        for (i in 0 until n - 2) {
            for (j in i + 1 until n - 1) {

                var l = j + 1
                var h = n - 1

                val target = -(arr[i] + arr[j]).toLong()

//                println(target)

                while (l + 1 < h) {
                    val mid = (l + h) / 2

                    if (arr[mid] < target) {
                        l = mid
                    } else if (arr[mid] > target) {
                        h = mid
                    } else {
                        println("${arr[i]} ${arr[j]} ${arr[mid]}")
                        return@with
                    }
                }

                val last = if (abs(arr[l] - target) < abs(arr[h] - target)) arr[l] else arr[h]

//                println("a: ${arr[i] } b: ${arr[j]} c: $last")

                if (answer > abs(last - target)) {
                    answer = abs(last - target)
                    a = arr[i]
                    b = arr[j]
                    c = last
                }
            }
        }

        println("$a $b $c")
    }
}