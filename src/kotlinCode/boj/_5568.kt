package kotlinCode.boj


/**
 *  카드 놓기
 *   - 카드 n (4 <= n <= 10)장
 *   - 카드에는 1 이상 99이하의 정수
 *   - 카드 중 k (2<= k <= 4) 장을 선택하고, 가로로 나란히 정수를 만듬
 *   - 상근이가 만들 수 있는 정수는 모두 몇가지?
 *
 */

private val br = System.`in`.bufferedReader()
private lateinit var arr: IntArray
private lateinit var visited: BooleanArray
private lateinit var set: MutableSet<String>

fun main() = with(br){
    val n = readLine().toInt()
    val k = readLine().toInt()

    arr = IntArray(n)
    visited = BooleanArray(n)
    set = mutableSetOf()

    repeat(n) {
        arr[it] = readLine().toInt()
    }

    permutation(k, "")

    println(set.size)
}

fun permutation(size: Int, result: String) {

    if (size == 0) {
        set.add(result)
        return
    }

    for (i in arr.indices) {
        if (!visited[i]) {
            visited[i] = true
            permutation(size - 1, result + arr[i])
            visited[i] = false
        }
    }
}