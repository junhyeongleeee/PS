package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  거짓말
 *
 *
 *
 */
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var unionFind: IntArray
private lateinit var knowPeople: BooleanArray
fun main() = with(System.`in`.bufferedReader()) {
    st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    arr = Array(m + 1) { mutableListOf() }
    unionFind = IntArray(n + 1) { it }
    knowPeople = BooleanArray(n + 1)

    st = StringTokenizer(readLine())
    repeat(st.nextToken().toInt()) {
        knowPeople[st.nextToken().toInt()] = true
    }

    for (i in 1 .. m) {
        st = StringTokenizer(readLine())
        repeat(st.nextToken().toInt()) { j ->
            val num = st.nextToken().toInt()
            arr[i].add(num)
        }

        for (j in 0 until arr[i].size - 1) {
            val p = arr[i][j]
            val np = arr[i][j + 1]
            if (find(p) != find(np)) {
                union(p, np)
            }
        }
    }

    val visited = BooleanArray(n + 1)
    for (i in 1 .. n) {
        if (knowPeople[i] && !visited[i]) {
            val parent = find(i)
            for (j in 1..n) {
                if (find(j) == parent) {
                    knowPeople[j] = true
                    visited[j] = true
                }
            }
        }
    }

    var result = 0
    for (i in 1 .. m) {
        var flag = false
        for (p in arr[i]) {
            if (knowPeople[p]) {
                flag = true
                break
            }
        }
        if (!flag) {
            result++
        }
    }

    println(result)
}

fun find(a: Int): Int = if (a == unionFind[a]) {
    a
} else find(unionFind[a])

fun union(a: Int, b: Int) {
    unionFind[find(b)] = a
}
