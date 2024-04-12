package kotlinCode.boj


fun main() = with(System.`in`.bufferedReader()) {
    val t = readLine().toInt()              // t <= 100_000

    val opF = LongArray(1_000_001) { 1 }
    val opG = LongArray(1_000_001) { 0 }
    val sb = StringBuilder()

    opG[1] = 1

    for (i in 2..1_000_000) {
//        println("prev opG[$i]: ${opG[i]}")
        // 본인 수 더하면 f(i)
        opF[i] += i.toLong()
        opG[i] += opF[i]
        opG[i] += opG[i - 1]

//        println("opF[$i]: ${opF[i]} opG[$i]: ${opG[i]}")
        // 본인의 배수에 미리 더하기
        var j = 2
        while (i * j <= 1_000_000) {
            opF[i * j] += i.toLong()
            j++
        }
    }

//    println("opF : ${opF.joinToString(" ")}")
//    println("opG : ${opG.joinToString(" ")}")

    repeat(t) {
        val n = readLine().toInt()          // N <= 1_000_000
        sb.appendLine(opG[n])
    }

    println(sb)
}