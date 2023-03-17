package kotlinCode.boj

import java.util.StringTokenizer

/**
 *  트리의 순회
 *
 *  - 전위 순회 : preorder traverse
 *      뿌리를 먼저 방문
 *  - 중위 순회 : inorder traverse
 *      왼쪽 하위 트리를 방문 후 뿌리 방문
 *  - 후위 순회 : postorder traverse
 *      하위 트리 모두 방문 후 뿌리 방문
 *
 *   1 <= n <= 100,000
 */
private lateinit var st: StringTokenizer
private lateinit var inorder: IntArray
private lateinit var postorder: IntArray
private lateinit var preorder: IntArray
private var idx = 0
private val sb = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    // 중위 순회
    st = StringTokenizer(readLine())
    inorder = IntArray(n)
    postorder = IntArray(n)
    preorder = IntArray(n)

    repeat(n) {
        inorder[it] = st.nextToken().toInt()
    }

    // 후위 순회
    st = StringTokenizer(readLine())
    repeat(n) {
        postorder[it] = st.nextToken().toInt()
    }
    solve2263(0, n - 1, 0, n - 1)

    for (p in preorder) {
        sb.append(p).append(" ")
    }
    println(sb)
}

fun solve2263(inS: Int, inE: Int, poS: Int, poE: Int) {
    if (inS > inE || poS > poE) return

    val root = postorder[poE]
    // post 마지막은 root 노드
    preorder[idx++] = root

    //
    var pos = inS

    for (i in inS..inE) {
        if (inorder[i] == root) {
            pos = i
            break
        }
    }

    solve2263(inS, pos - 1, poS, poS + pos - inS - 1)
    solve2263(pos + 1, inE, poS + pos - inS, poE - 1)
}