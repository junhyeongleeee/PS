package kotlinCode

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val answer = mutableListOf<List<Int>>()

    combination(list, answer, BooleanArray(list.size), 0, 2)

    answer.forEach {
        println(it.toString())
    }

    val array = arrayOf<String>()
}

fun <T> combination(
    list: List<T>,
    answer: MutableList<List<T>>,
    visited: BooleanArray,
    start: Int,
    r: Int
) {
    if (r == 0)
        answer.add(list.filterIndexed { index, _ -> visited[index] })
    else {
        for (i in start until list.size) {
            visited[i] = true
            combination(list, answer, visited, i + 1, r - 1)
            visited[i] = false
        }
    }
}