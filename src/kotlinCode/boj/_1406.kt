package kotlinCode.boj

private val st1 = java.util.ArrayDeque<Char>()
private val st2 = java.util.ArrayDeque<Char>()
fun main() = with(System.`in`.bufferedReader()){
    val s = readLine()          // s.length <= 100,000
    val m = readLine().toInt()  // 1 <= M <= 500,000

    s.forEach { st1.push(it) }

    repeat(m) { i ->
        val line = readLine().split(" ")
        when(line[0].single()) {
            'L' -> {
                if (st1.isNotEmpty()) {
                    st2.push(st1.pop())
                }
            }
            'D' -> {
                if (st2.isNotEmpty()) {
                    st1.push(st2.pop())
                }
            }
            'B' -> {
                if (st1.isNotEmpty()) {
                    st1.pop()
                }
            }
            'P' -> {
                val c = line[1].single()
                st1.push(c)
            }
        }
    }

    val sb = StringBuilder()
    while (st1.isNotEmpty()) {
        sb.append(st1.pollLast())
    }
    while (st2.isNotEmpty()) {
        sb.append(st2.pop())
    }
    println(sb)
}