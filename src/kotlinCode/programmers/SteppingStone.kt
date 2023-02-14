package kotlinCode.programmers

/**
 *  징검다리 건너기
 */

// 건널 수 있는 친구들의 수가 존재하는지


fun solution(stones: IntArray, k: Int): Int {
    var answer = 0
    var left = 1
    var right = 200000000

    while(left <= right) {
        val mid = (left + right) / 2

        if(check(mid, k, stones)) {
            // 가능함
            answer = mid
            left = mid + 1
        }
        else {
            right = mid - 1
        }
    }

    return answer
}
fun check(num: Int, k: Int, stones: IntArray): Boolean {
    var result = 0
    stones.forEach {
        result = if(it - num < 0) result+1
        else 0
        if(k == result) return false
    }
    return true
}