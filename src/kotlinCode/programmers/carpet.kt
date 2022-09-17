package kotlinCode.programmers

fun main() {
    val solution = Solution()
    val result = solution.solution(10,2)


}

class Solution {
    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = intArrayOf()

        // a + b
        val sum = brown / 2 - 2
        val mul = yellow

        val map = hashMapOf<Int, Boolean>()

        for (i in 1 until 2498) {
            if (map[i] == null) {
                if ( i * (sum - i) == mul) return intArrayOf(i, sum - i)
                map[i] = true
                map[sum - i] = true
            }
        }

        return answer
    }
}