package kotlinCode.leetcode

fun main() {
    val solution = Solution()
    val result = solution.twoSum(intArrayOf(2,7,11,15), 9)


}

/**
 *
 *   Input: nums = [2,7,11,15], target = 9
 *      - 답의 크기는 항상 2가 아니다
 *      - 항상 결과가 존재하는 target 입력 값이 들어온다.
 */
class Solution {
    fun twoSum(nums: IntArray, target: Int): IntArray {

        for (i in nums.indices) {
            val front = nums[i]
            for (j in i + 1 until nums.size) {
                val end = nums[j]
                if (front + end == target) return intArrayOf(i, j)
            }
        }
        return intArrayOf()
    }
}