package kotlinCode.boj


//    10
//    9 8 7 6 5 1 2 3 4 5
//    8
//    1 3 5 7 9 6 5 4

//    9
//    5 4 3 1 5 3 7 5 5
//    8
//    5 7 2 1 3 5 4 3

//    6
//    5 4 3 1 5 3
//    7
//    5 2 1 3 5 4 3

//    7
//    5 4 3 1 5 4 3
//    7
//    5 2 1 3 5 4 3

//    8
//    4 2 9 6 8 9 10 9
//    5
//    6 7 6 9 8

import java.util.StringTokenizer

class `30805` {

    fun solution() = with(System.`in`.bufferedReader()){

        val n = readLine().toInt()
        var st = StringTokenizer(readLine())

        val aArr = IntArray(n)
        repeat(n) {
            aArr[it] = st.nextToken().toInt()
        }

        val m = readLine().toInt()
        st = StringTokenizer(readLine())

        val bArr = IntArray(m)
        repeat(m) {
            bArr[it] = st.nextToken().toInt()
        }

        val list = aArr.mapIndexed { index, i -> Pair(index, i) }.sortedByDescending { it.second }

        val result = mutableListOf<Int>()
        var aIdx = -1
        var bIdx = 0

        for (i in 0 until n) {
            val v = list[i]

            if (aIdx > v.first) continue

            for (j in bIdx until m) {
                if (v.second == bArr[j]) {
                    bIdx = j + 1
                    aIdx = v.first + 1
                    result.add(v.second)
                    break
                }
            }
        }

        println(result.size)
        println(result.joinToString(" "))
    }
}

fun main() {
    `30805`().solution()
}