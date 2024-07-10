package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var st: StringTokenizer
private lateinit var parent: IntArray

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    parent = IntArray(n + 1) { it }

    repeat(n - 2) {
        st = StringTokenizer(readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        union13752(a, b)
    }

//    println(parent.joinToString(" "))

    val p = parent[1]
    for (i in 1..n) {
        if (find13752(i) != p) {
            println("1 $i")
            return@with
        }
    }
}

fun find13752(a: Int): Int {
    if (parent[a] == a) return a
    parent[a] = find13752(parent[a])
    return parent[a]
}

fun union13752(a: Int, b: Int) {
    val ap = find13752(a)
    val bp = find13752(b)

    if (ap < bp) {
        parent[bp] = ap
    } else {
        parent[ap] = bp
    }
}