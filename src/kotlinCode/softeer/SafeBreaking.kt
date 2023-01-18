package kotlinCode.softeer

import java.util.PriorityQueue

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()

data class Bag(val m: Int, val p: Int)

fun main() = with(br) {
    val (w, n) = readLine().split(" ").map { it.toInt() }
    val bags = PriorityQueue<Bag> { a, b ->
        if (a.p > b.p) {
            -1
        } else if (a.p == b.p) {
            b.m - a.m
        } else {
            1
        }
    }

    repeat(n) {
        val (m, p) = readLine().split(" ").map { it.toInt() }
        bags.add(Bag(m, p))
    }

    println(bags.joinToString(" "))

    var result = 0L
    var max = w
    while (bags.isNotEmpty()) {
        val bag = bags.poll()
        if (bag.m >= max) {
            result += max * bag.p
            break
        }
        result += bag.m * bag.p
        max -= bag.m
    }
    println(result)
}