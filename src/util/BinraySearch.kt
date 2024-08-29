package util


/**
 *  이진 탐색 중복 존재할 때 응용 버전 UpperBound, LowerBound
 */

fun binarySearch(list: MutableList<Int>, target: Int) : Int {
    var low = 0
    var high = list.size - 1

    while (low <= high) {
        val mid = (low + high) / 2

        if (list[mid] < target) {
            low = mid + 1
        } else if (list[mid] > target) {
            high = mid - 1
        }else {
            return mid
        }
    }

    // 못 찾을 경우, 조건에 따라 찾는 값에서 가장 근접한 값이 작은 값인지, 큰 값인지 확인.
    // 찾는 값 보다 작은 값이라면 low -1 , 크다면 low

    return low - 1
}

fun lowerBound(list: MutableList<Long>, target: Long): Int {
    var low = 0
    var high = list.size

    while (low < high) {
        val mid = (low + high) / 2
        if (list[mid] < target) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    return low
}

fun upperBound(list: MutableList<Long>, target: Long): Int {
    var low = 0
    var high = list.size

    while (low < high) {
        val mid = (low + high) / 2
        if (list[mid] <= target) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    return low
}