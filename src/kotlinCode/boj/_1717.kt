package kotlinCode.boj

import java.util.StringTokenizer

private lateinit var st: StringTokenizer
private lateinit var parent: IntArray
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }

    // 0 : 합집합
    // 1 : 포함되어 있는지 확인
    parent = IntArray(n + 1) { it }

    repeat(m) {
        st = StringTokenizer(readLine())
        val op = st.nextToken().toInt()
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        when (op) {
            1 -> {
                if (findParent(a) == findParent(b)) {
                    sb.append("YES\n")
                } else {
                    sb.append("NO\n")
                }
            }
            0 -> {
                union1717(a, b)
            }
        }
    }
    println(sb)
}

fun union1717(a: Int, b: Int) {
    val pa = findParent(a)
    val pb = findParent(b)

    if (pa == pb) return
    parent[pa] = pb
}

fun findParent(n: Int): Int {
    if (parent[n] == n) {
        return n
    }
    parent[n] = findParent(parent[n])
    return findParent(parent[n])
}