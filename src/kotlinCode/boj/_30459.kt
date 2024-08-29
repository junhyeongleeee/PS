package kotlinCode.boj

import java.util.*
import kotlin.math.absoluteValue
import kotlin.math.max


private lateinit var st: StringTokenizer

private lateinit var flags: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())

    val n = st.nextToken().toInt()              // 2<= <= 2_000
    val m = st.nextToken().toInt()              // 1<= <= 40_000
    val r = st.nextToken().toInt()              // 1 <= <= 10^9

    st = StringTokenizer(readLine())

    val set = mutableSetOf<Int>()

    val points = IntArray(n)
    repeat(n) {
        points[it] = st.nextToken().toInt()     // -20_000 <= <= 20_000
    }

    flags = IntArray(m)
    st = StringTokenizer(readLine())
    repeat(m) {
        flags[it] = st.nextToken().toInt()      // 1<= <= 40_000
    }

    flags.sort()

    for (i in 0 until n - 1) {
        val a = points[i]
        for (j in i + 1 until n) {
            val b = points[j]
            set.add((b - a).absoluteValue)
        }
    }

    var result = -1.0

    for (line in set) {

        if ((line.toDouble() * flags[0]) / 2 > r) continue

        val idx = solve20459(r, line)
        val area = (flags[idx].toDouble() * line) / 2

        result = max(result, area)
    }

    println(if (result == -1.0) -1 else "%.1f".format(result))
}

fun solve20459(r: Int, line: Int): Int {
    var low = 0
    var high = flags.size - 1

    while (low <= high) {
        val mid = (low + high) / 2
        val area = (line.toDouble() * flags[mid]) / 2

        when {
            area < r -> low = mid + 1
            area > r -> high = mid - 1
            else -> return mid
        }
    }
    return low - 1
}