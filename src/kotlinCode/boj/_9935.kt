package kotlinCode.boj

/**
 *  문자열 폭발
 *
 *  1 <= 문자열 <= 1,000,000
 *  1 <= 폭발문자열 <= 36
 */
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()){
    val s = readLine()
    val ex = readLine()

    for (i in s.indices) {
        sb.append(s[i])

        if (sb.length >= ex.length) {
            var flag = true
            for (j in ex.indices) {
                val idx = sb.length - ex.length + j
                if (sb[idx] != ex[j]) {
                    flag = false
                    break
                }
            }
            if (flag) {
                for (j in ex.indices) {
                    sb.deleteAt(sb.lastIndex)
                }
            }
        }
    }

    if (sb.length == 0) {
        println("FRULA")
    }else {
        println(sb)
    }
}