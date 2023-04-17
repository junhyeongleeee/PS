package practice

import java.util.*


data class CantIDE(val a: Int, val b: Int)

fun main() {
    val arr1 = arrayOf(1)
    val arr2 = intArrayOf()
    val arr3 = Array(5) { CantIDE(0, 0) }

    val arrList1 = mutableListOf<Int>()                             // Java: ArrayList
    val arrList2 = mutableListOf<CantIDE>()

    val set1 = mutableSetOf<String>()                               // Java: LinkedHashSet
    val map1 = mutableMapOf<Int, Int>()                             // Java: LinkedHashMap

    val queue = ArrayDeque<Int>()
    val stack = ArrayDeque<String>()
    val pq1 = PriorityQueue<CantIDE> { o1, o2 -> o1.b - o2.b }
    val pq2 = PriorityQueue(compareBy<CantIDE> { it.a }.thenBy { it.b })

    /*pq2.add(CantIDE(-1, 12))
    pq2.add(CantIDE(1, 12))
    pq2.add(CantIDE(-1, 13))
    pq2.add(CantIDE(-2, 13))
    pq2.add(CantIDE(-2, 500))

    while (!pq2.isEmpty()) {
        val ide = pq2.poll()
        println("a: ${ide.a}, b: ${ide.b}")
    }*/

    // sort

    arr1.sort()                                                     // Unit
    arr1.sorted()                                                   // List<Int>
    arr1.sortedArray()
    arr2.sortDescending()
    arr2.sortedArray()                                              // IntArray
    arr3.sortBy { it.a }
    arr3.sortWith(compareBy<CantIDE> { it.a }.thenBy { it.b })      // Unit
    arr3.sortedArrayWith(compareBy<CantIDE> { it.a }.thenBy { it.b })      // Unit

    arrList1.sort()
    arrList1.sorted()
    arrList2.sortWith(compareBy<CantIDE> { it.a }.thenBy { it.b })

    set1.sortedBy { it }
    map1.toSortedMap(compareBy<Int> { it }.reversed())              // 키를 오름차순으로 정렬한 Map

    // set, map
    set1.contains("")
    val v = map1[1]!!

    // Deque
    queue.add(1)
    queue.poll()
    stack.push("")
    stack.pop()

}