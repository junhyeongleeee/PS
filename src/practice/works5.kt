package practice

import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

/**
 *  1 <= fees <= 100,000
 *  1 <= x <= 1,000,000
 *  1 <= y <= 1,000,000,000
 *  1 <= t <= 1,000,000
 */
fun main() {
    val t = 27
    val fees = arrayOf(
        intArrayOf(4, 1000),
        intArrayOf(6, 1000),
        intArrayOf(21, 3000),
        intArrayOf(16, 2000),
        intArrayOf(26, 3000)
    )

    fees.sortBy { it[0] }

//    fees.forEach { println(it.joinToString(" ")) }

    val list = mutableListOf<Int>()
    val b = fees[0][1]
    for (i in 1 until fees.size) {
        val prevFee = fees[i - 1]
        val currentFee = fees[i]
        if (currentFee[1] != prevFee[1]) {
            if (list.size == 0) {
                list.add(prevFee[0])
            }
            list.add(currentFee[0])
        }
    }

//    println(list.joinToString(" "))

    var s = Integer.MIN_VALUE
    var e = Integer.MAX_VALUE
    for (i in 1 until list.size) {
        var pt = list[i - 1]
        var ct = list[i]

        pt = ceil ((pt + 1).toDouble() / i).toInt()
        ct /= i

        s = max(s, pt)
        e = min(e, ct)
    }

    val min = (t / e * b) + b
    val max = (t / s * b) + b

    println("min: $min max: $max")
}