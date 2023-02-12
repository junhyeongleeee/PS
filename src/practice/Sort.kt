package practice


// Int
private val list = mutableListOf<Int>()
private val arr = intArrayOf()
private val map = hashMapOf<Int, Int>()
private val set = setOf<Int>()

// Class
data class DataClass(val v: Int, val n: Int)
private val classList = mutableListOf<DataClass>()
private val classMap = hashMapOf<DataClass, Int>()
fun main() {

    // list
    list.sort()
    val sortedList = list.sorted()

    // arr
    arr.sort()
    val sortedArr = arr.sorted()

    // map
    val sortedMap = map.toSortedMap()

    // set
    val sortedSet = set.sorted()

    // classList
    classList.sortBy { it.v }
    classList.sortByDescending { it.v }
    val sortedClassList = classList.sortedBy { it.n }

    // classMap

    for (i in 10 downTo 1) {

    }
}