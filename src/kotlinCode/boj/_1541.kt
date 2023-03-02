package kotlinCode.boj

/**
 *  잃어버린 괄호
 */
private val br = System.`in`.bufferedReader()

fun main() = with(br){
    val input = readLine()

    // 55, 50+50+10+20, 40, 5+2+3....
    val mDivide = input.split("-")
    if (mDivide.size == 1) {
        println(input.split("+").sumOf { it.toInt() })
    }
    else {
        var result = mDivide[0].split("+").sumOf { it.toInt() }
        for (i in 1 until mDivide.size) {
            result -= mDivide[i].split("+").sumOf { it.toInt() }
        }
        println(result)
    }
}