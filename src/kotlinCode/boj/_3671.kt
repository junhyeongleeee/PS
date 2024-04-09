package kotlinCode.boj

import kotlin.math.min
import kotlin.math.sqrt


private lateinit var set: MutableSet<Int>
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val c = readLine().toInt()

    val minority = BooleanArray(10_000_000)
    for (i in 2 .. sqrt(9999999f).toInt()) {
        if (minority[i]) continue
        var c = 2
        while (i * c <= 9999999) {
            minority[i * c] = true
            c++
        }
    }
    minority[0] = true
    minority[1] = true
//    println("size: ${minority.filter { !it }.size}")

    repeat(c) {
        val counting = IntArray(10)
        val num = readLine().map { it - '0' }
        val arr = IntArray(num.size)
        set = mutableSetOf()
        num.forEach {
            counting[it]++
        }
//        println(counting.joinToString(" "))

        solve3671(counting, arr, "",0)

        sb.appendLine(set.count { !minority[it] })
    }

    println(sb)
}

fun solve3671(counting: IntArray, arr: IntArray, value: String, idx: Int) {

    if (idx == arr.size) {
        return
    }

    for (i in counting.indices) {
        if (counting[i] == 0) continue

        val nv = "$value$i"
        set.add(nv.toInt())
        counting[i]--
        solve3671(counting, arr, nv, idx + 1)
        counting[i]++
    }
}