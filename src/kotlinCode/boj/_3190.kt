package kotlinCode.boj

import java.util.StringTokenizer


private lateinit var arr: Array<IntArray>
private lateinit var st: StringTokenizer

private val dx = intArrayOf(1, 0, -1, 0)
private val dy = intArrayOf(0, 1, 0, -1)

private val dq = java.util.ArrayDeque<Pair<Int, Int>>()

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val k = readLine().toInt()

    arr = Array(n) { IntArray(n) }

    repeat(k) {
        st = StringTokenizer(readLine())
        val y = st.nextToken().toInt() - 1
        val x = st.nextToken().toInt() - 1

        arr[y][x] = 1
    }

    val l = readLine().toInt()

    var time = 0
    var vector = 0
    dq.add(Pair(0, 0))

//    arr.forEach { println(it.joinToString(" ")) }

    var count = 0
    for (i in 0 until l) {
        st = StringTokenizer(readLine())
        val t = st.nextToken().toInt()
        val v = st.nextToken().single()

        // 이동
        // 꼬리자르기
        time = moveSnack(time, n, t, vector)

        if (time != t) {
            break
        }

        // 방향 이동
        vector = if (v == 'L') (vector + 3) % 4 else (vector + 1) % 4
        count++
    }

    if (count == l) {
        time = moveSnack(time, n, Int.MAX_VALUE, vector)
    }

    // 게임은 벽에 부딪히거나 몸통에 부딪히는 경우에만 존재하기 때문에 + 1
    println(time + 1)
}

fun moveSnack(time: Int, n: Int, t: Int, vector: Int): Int {
    var tmpTime = time

    while (tmpTime < t) {
        val tail = dq.peekLast()
        val head = if (dq.size == 0) tail else dq.peekFirst()
        val x = head.first
        val y = head.second

        val nx = x + dx[vector]
        val ny = y + dy[vector]

        // 벽인지 확인
        if (nx < 0 || ny < 0 || nx > n - 1 || ny > n - 1) {
            break
        }

        // 내 몸통인지 확인
        if (checkBody(nx, ny)) {
            break
        }

        dq.pollLast()

        // 사과일 경우
        if (arr[ny][nx] == 1) {
            // 꼬리 그대로
            dq.addLast(tail)
            arr[ny][nx] = 0
        }

        dq.addFirst(Pair(nx, ny))

        tmpTime++
    }
//    println("time: $tmpTime ${dq.joinToString(" ")}")

    return tmpTime
}

fun checkBody(nx: Int, ny: Int): Boolean {
    for (p in dq) {
        if (p.first == nx && p.second == ny) {
            return true
        }
    }
    return false
}
