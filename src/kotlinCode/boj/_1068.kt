package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  트리
 *  - 노드의 개수 N ( N <= 50) 자연수
 *  - 각 노드의 부모가 주어진다.
 *  - 지울 노드의 부모가 주어진다.
 *  - 리프 노드의 개수를 출력한다.
 *
 *  - 배열에 자식 노드들을 리스트로 연결
 *  - 자식이 삭제 노드면 더이상 탐색하지 않는다.
 *  - 자식이 없고 삭제 노드가 아니면 리프노드 + 1
 */

private val br = System.`in`.bufferedReader()
private lateinit var st: StringTokenizer
private lateinit var arr: Array<MutableList<Int>>
private lateinit var visited: BooleanArray
private var result = 0
fun main() = with(br) {
    val n = readLine().toInt()
    arr = Array(n) { mutableListOf() }
    visited = BooleanArray(n)
    st = StringTokenizer(readLine())
    var root = -1
    repeat(n) {
        when (val v = st.nextToken().toInt()) {
            -1 -> {
                root = it
            }
            else -> {
                arr[v].add(it)
            }
        }
    }
    val d = readLine().toInt()
    if (d == root) {
        println(0)
    }
    else {
        search(root, d)
        println(result)
    }
}

fun search(v: Int, d: Int) {

    if (v != d && arr[v].size == 0) {
        result++
    }

    arr[v].forEach {
        if (it != d) {
            search(it, d)
        }
        else {
            if (arr[v].size == 1) {
                result++
            }
        }
    }
}