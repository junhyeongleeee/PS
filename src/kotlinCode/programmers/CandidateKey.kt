

/**
 *
 *  relation은 2차원 문자열 배열이다.
 *  relation의 컬럼(column)의 길이는 1 이상 8 이하이며, 각각의 컬럼은 릴레이션의 속성을 나타낸다.
 *  relation의 로우(row)의 길이는 1 이상 20 이하이며, 각각의 로우는 릴레이션의 튜플을 나타낸다.
 *  relation의 모든 문자열의 길이는 1 이상 8 이하이며, 알파벳 소문자와 숫자로만 이루어져 있다.
 *  relation의 모든 튜플은 유일하게 식별 가능하다.(즉, 중복되는 튜플은 없다.)
 */

fun main() {
    val relation = listOf(
        listOf("100", "ryan", "music", "2"),
        listOf("200", "apeach", "math", "2"),
        listOf("300", "tube", "computer", "3"),
        listOf("400", "con", "computer", "4"),
        listOf("500", "muzi", "music", "3"),
        listOf("600", "apeach", "music", "2"),
    )
    val rowSize = relation.size
    val columnSize = relation[0].size
    val list = IntArray(columnSize) { i -> i }
    val result = mutableListOf(listOf<Int>())
    val tmpList = mutableListOf(listOf<Int>())
    var answer = 0

    for (i in 1..columnSize)
        candidateCombination(result, list.toMutableList(), BooleanArray(columnSize), 0, i)

    println(result)

    result.forEachIndexed go@{ i, indexs ->
        val set = mutableSetOf<String>()
        // "100", "ryan", "music", "2"
        relation.forEach {
            var tmp = ""
            tmpList.forEach {element ->
                if (element.isNotEmpty() && indexs.containsAll(element)) return@go
            }
            // ex 0, 3
            indexs.forEachIndexed { j, index ->
                tmp += it[index]
            }
            set.add(tmp)
        }
        if (set.size == rowSize) {
            tmpList.addAll(listOf(indexs))
            answer++
        }
    }

    println(answer)
}



fun <T> candidateCombination(
    result: MutableList<List<T>>,
    comb: MutableList<T>,
    visited: BooleanArray,
    start: Int,
    r: Int
) {
    if (r == 0)
        result.addAll(listOf(comb.filterIndexed { index, _ -> visited[index] }))
    else {
        for (i in start until comb.size) {
            visited[i] = true
            candidateCombination(result, comb, visited, i + 1, r - 1)
            visited[i] = false
        }
    }
}
