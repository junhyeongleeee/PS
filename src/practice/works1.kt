package practice

/**
 *  - 새로운 배열의 크기는 원소의 수 이상인 2의 거듭제곱 중 가장 작은 값
 *  - 새로운 배열에 기존 배열의 모든 원소를 복사한 후 원소를 추가, 배열의 번호는 바뀌지 않음.
 *
 *  1 <= queries 길이 <= 100,000
 *  1 <= a <= 1,000     배열 번호
 *  1 <= b <= 10,000    추가 원소 개수
 */

private val arr = Array(1001) { ArrayData(0, 0) }

data class ArrayData(val el: Int, val size: Int)

fun main() = with(System.`in`.bufferedReader()) {
    val queries = arrayOf(
        intArrayOf(1, 1),
        intArrayOf(1, 2),
        intArrayOf(1, 4),
        intArrayOf(1, 8),
    )
    var result = 0

    queries.forEachIndexed { index, ints ->
        val num = ints[0]
        val count = ints[1]

        val ad = arr[num]
        val element = ad.el
        val size = ad.size

        val newEl = element + count
        arr[num] = if (newEl > size) {
            result += element
            ArrayData(newEl, findPow(newEl))
        } else {
            ArrayData(newEl, size)
        }
    }
    println(result)
}
fun findPow(s: Int): Int {
    var size = 1
    while (size < s) {
        size = size shl 1
    }
    return size
}