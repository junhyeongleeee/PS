package kotlinCode

import java.util.LinkedList


val orders = arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD")
val course = arrayOf(2, 3, 4)

fun main() {

    val comb = mutableListOf<String>()
    for (o in orders)
        for (c in course)
            combination(comb, o.toList().sorted(), "", 0, c)

    val answer = mutableListOf<String>()
    comb.groupingBy { it }.eachCount().toList().groupBy { it.first.length }.forEach { (_, pair) ->
        val maxCount = pair.maxByOrNull { it.second }?.second
        answer.addAll(pair.filter { it.second >= 2 && it.second == maxCount }.map { it.first })
    }
}

fun combination(
    comb: MutableList<String>,
    element: List<Char>,
    part: String,
    start: Int,
    r: Int
): Unit = when(r){
    0 -> {
        comb.add(part)
        Unit
    }
    else -> {
        for (i in start until element.size)
            combination(comb, element, part + element[i], i + 1, r - 1)
    }
}