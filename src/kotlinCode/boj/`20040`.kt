package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

fun main() {
   `20040`().solution()
}
class `20040` {
    private lateinit var st: StringTokenizer
    lateinit var parent: IntArray
    fun solution() = with(System.`in`.bufferedReader()) {
        st = StringTokenizer(readLine())
        val n = st.nextToken().toInt()                  // 3 <= <= 500_000
        val m = st.nextToken().toInt()                  // 3 <= <= 1_000_000
        var answer = Int.MAX_VALUE

        parent = IntArray(n) { it }

        repeat(m) {
            st = StringTokenizer(readLine())

            val x = st.nextToken().toInt()
            val y = st.nextToken().toInt()

            if (func1(x) == func1(y)) {
                answer = min(answer, it + 1)
            }
            func2(x, y)
        }

        println(if (answer == Int.MAX_VALUE) 0 else answer)
    }

    fun func1(a: Int) : Int{
        if (a == parent[a]) return a
        parent[a] = func1(parent[a])
        return parent[a]
    }
    fun func2(a: Int, b: Int) {
        val pa = func1(a)
        val pb = func1(b)

        if(pa < pb) {
            parent[pb] = pa
        }
        else {
            parent[pa] = pb
        }
    }
}