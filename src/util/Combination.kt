package util


fun <T> combination(
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
            combination(result, comb, visited, i + 1, r - 1)
            visited[i] = false
        }
    }
}