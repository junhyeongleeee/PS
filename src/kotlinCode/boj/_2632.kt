package kotlinCode.boj


/**
 *   첫 번째 줄에는 손님이 구매하고자 하는 피자크기를 나타내는 2,000,000 이하의 자연수가 주어진다.
 *   두 번째 줄에는 A, B 피자의 피자조각의 개수를 나타내 는 정수 m, n 이 차례로 주어진다 (3 ≤ m, n ≤ 1000).
 *   세 번째 줄부터 차례로 m 개의 줄에는 피자 A의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
 *   그 다음 n 개의 줄에는 차례로 피자B 의 미리 잘라진 피자조각의 크기를 나타내는 정수가 주어진다.
 *   각 종류의 피자조각의 크기는 시계방향으로 차례로 주어지며, 각 피자 조각의 크기는 1000 이하의 자연수이다.
 *
 *   s <= 2,000,000
 *   m <= 1000, n<= 1000
 *   피자 조각으 크기 <= 1000
 *
 *   합 최대 => 1000 * 1000 = 1,000,000 * 2 -> 자료형 Integer
 *
 *   피자 -> 원 -> 연속된 조각 -> [1,2,3,4] 일 때 (4,1,2) 도 연속된 값.
 *
 *   Solution
 *   1. A 와 B 각 피자에서 나올 수 있는 모든 합 들을 가진 리스트를 구함. 1,000 * 1,000 * 2 : n제곱
 *   2. 각 리스트들을 비교하여 손님이 원하는 크기의 피자를 판매한다. -> 이분탐색 응용 : n * log n
 *
 *   1,000 * 1,000 :(n2)  * 1,000 * 약 10.. : (n log n) 10,000,000,000 약 10억..?
 *
 *   A = [2,2,1,7,2]
 */

fun main() {
    val s = readln().toInt()
    val (m, n) = readln().split(" ").map { it.toInt() }
    val aList = mutableListOf<Int>()
    val bList = mutableListOf<Int>()
    repeat(m) {
        aList.add(readln().toInt())
    }
    repeat(n) {
        bList.add(readln().toInt())
    }

    val sumAList = calculationPieces(aList)
    val sumBList = calculationPieces(bList)

    sumBList.sort()

    var result = 0
    sumAList.forEach {
        result += binarySearch(s - it, sumBList)
        if (it == s){
            result += 1
        }
    }

    result += sumBList.filter { it == s }.size

    println(result)
}

fun calculationPieces(list: MutableList<Int>): MutableList<Int> {
    val nList = mutableListOf<Int>()

    for (i in 0 until list.size) {
        var sum = 0
        var cnt = 0
        var j = i
        while (cnt < list.size - 1) {
            if (j >= list.size) {
                j %= list.size
            }
            sum += list[j]
            nList.add(sum)

            cnt++
            j++
        }
    }
    nList.add(list.sum())
    return nList
}

fun binarySearch(target: Int, list: MutableList<Int>): Int {
    var left = 0
    var right = 0

    var start = 0
    var end = list.size - 1

    while (start <= end) {
        var mid : Int = (start + end) / 2

        if (target <= list[mid]) {
            end = mid - 1
        } else {
            start = mid + 1
        }
    }
    left = end

    start = 0
    end = list.size - 1
    while (start <= end) {
        var mid : Int = (start + end) / 2

        if (list[mid] <= target) {
            start = mid + 1
        } else {
            end = mid - 1
        }
    }
    right = end
    return right - left
}