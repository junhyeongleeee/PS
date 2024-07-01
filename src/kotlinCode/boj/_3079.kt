package kotlinCode.boj



private lateinit var arr: IntArray

fun main() = with(System.`in`.bufferedReader()){
    val (n, m) = readLine().split(" ").map { it.toInt() }
    arr = IntArray(n)
    repeat(n) {
        arr[it] = readLine().toInt()
    }
    println(solve3079(0, arr.maxOf { it.toLong() * m }, m))
}

fun solve3079(left: Long, right: Long, m: Int): Long {

    var l = left
    var r = right               // 10^9 * 10^9 , long <= 약 9 * 10^18

    while (l < r) {
        val mid = (l + r) / 2
        var sum = 0L

        // 심사대 걸리는 시간 1 <= Tk <= 1_000_000_000 , mid <= 약 10^18
        // arr.sumOf { mid / it } -> 10^6 * 10^18 long 범위를 넘어 overflow 발생
        for (num in arr) {
            val n = mid / num

            if (sum >= m) {
                break
            }
            sum += n
        }

        if (sum >= m) {
            r = mid
        }else {
            l = mid + 1
        }
    }

    return l
}