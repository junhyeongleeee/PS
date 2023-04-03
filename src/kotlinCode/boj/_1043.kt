package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  거짓말
 *
 *
 *
 */
private lateinit var st: StringTokenizer
private lateinit var arr: IntArray
private lateinit var parent: IntArray
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    arr = IntArray(m)
    parent = IntArray(n + 1) { it }

    st = StringTokenizer(readLine())
    repeat(st.nextToken().toInt()) {
        union(0, st.nextToken().toInt())
    }

    repeat(m) { i ->
        st = StringTokenizer(readLine())
        val total = st.nextToken().toInt()
        val p = st.nextToken().toInt()
        repeat(total - 1) {
            union(p, st.nextToken().toInt())
        }
        arr[i] = parent[p]
    }

    var result = 0
    repeat(m) { i ->
        if (find(arr[i]) != 0) {
            result++
        }
    }

    println(result)
}

fun find(a: Int): Int = if (a == parent[a]) {
    a
} else find(parent[a])

fun union(a: Int, b: Int) {
    val ap = parent[a]
    val bp = parent[b]
    if (ap > bp) {
        parent[ap] = bp
    }
    else {
        parent[bp] = ap
    }
}
