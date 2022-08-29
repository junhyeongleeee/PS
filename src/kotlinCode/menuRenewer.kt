package kotlinCode

import java.util.LinkedList


val orders = arrayOf("ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD")
val course = arrayOf(2, 3, 4)
val answer = mutableListOf<String>()

fun main() {

    val comb = mutableListOf<String>()
    val map = mutableMapOf<String, Int>()

    for (order in orders) {
        var sortedOrder =
            order.toCharArray()
                .sorted()
                .joinToString("")

        for (i in course)
            combination(comb, sortedOrder, "", 0, i)
    }

    for (element in comb) {
        if (map[element] == null)
            map[element] = 1
        else
            map[element] = map.getValue(element) + 1
    }

    var mapToList = map.toList()
    mapToList = mapToList.sortedWith(compareBy({ it.first.length }, { -it.second }))

    mapToList.forEach{
        println(it)
    }

    var maxCount = mapToList[0].second
    var length = mapToList[0].first.length


    mapToList.forEach go@{
        if (it.second >= 2) {

            if (length != it.first.length) {
                length = it.first.length
                answer.add(it.first)
                maxCount = it.second
                return@go
            }

            if (maxCount == it.second)
                answer.add(it.first)
        }
    }

    answer.sort()
    answer.forEach { println(it) }
}

fun combination(
    comb: MutableList<String>,
    element: String,
    part: String,
    start: Int,
    r: Int
): Unit = when(r){
    0 -> {
        comb.add(part)
        Unit
    }
    else -> {
        for (i in start until element.length)
            combination(comb, element, part + element[i], i + 1, r - 1)
    }
}