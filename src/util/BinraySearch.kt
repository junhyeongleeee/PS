package util


/**
 *  이진 탐색 중복 존재할 때 응용 버전 UpperBound, LowerBound
 */

fun lowerBound(list: MutableList<Long>, target: Long): Int {
    var low = 0
    var high = list.size

    while (low < high) {
        var mid = low + (high - low) / 2
        if (target <= list[mid]) {
            high = mid
        } else {
            low = mid + 1
        }
    }
    return low
}

fun upperBound(list: MutableList<Long>, target: Long): Int {
    var low = 0
    var high = list.size

    while (low < high) {
        var mid = low + (high - low) / 2
        if (target >= list[mid]) {
            low = mid + 1
        } else {
            high = mid
        }
    }
    return low
}