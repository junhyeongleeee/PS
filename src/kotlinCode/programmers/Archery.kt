package kotlinCode.programmers

/**
 *  양궁대회
 */

private var max = -1
private var result = intArrayOf(-1)
lateinit var lion : IntArray
private const val size = 11
fun main(n: Int, info: IntArray): IntArray {
    lion = IntArray(info.size)
    dfs(info, n, 1)
    return result
}

fun dfs(info: IntArray, n: Int, cnt: Int) {
    if(cnt == n + 1) {
        var lionSum = 0
        var infoSum = 0
        for(i in 0 until size) {
            if(info[i] == 0 && lion[i] == 0) continue
            if(info[i] < lion[i]) {
                lionSum += 10 - i
            }
            else {
                infoSum += 10 - i
            }
        }
        val sum = lionSum - infoSum
        if(sum > 0) {
            if(max <= sum) {
                max = sum
                result = lion.clone()
            }
        }
        return
    }

    for(i in 0 until size) {
        if(lion[i] <= info[i]){
            lion[i] += 1
            dfs(info, n, cnt + 1)
            lion[i] -= 1
        }
    }
}