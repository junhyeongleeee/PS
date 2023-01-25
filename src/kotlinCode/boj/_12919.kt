package kotlinCode.boj

/**
 *  A 와 B 2
 *      - A
 *      - BABA
 *
 *      - AB -> BA -> BAB -> ABA -> ABAB
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private lateinit var s : String
private var answer : Int = 0

fun main() = with(br) {
    s = readLine()
    val t = readLine()

    dfs(t, s)

    print(answer)
}

fun dfs(s: String, t: String) {
    // 더 이상 추가가 의미 없는지 확인
    if (s.length == t.length){
        if (s == t) {
            answer = 1
            return
        }
        return
    }

    if (s[0] == 'B') {
        dfs(s.substring(1).reversed(), t)
    }
    if (s.last() == 'A') {
        dfs(s.substring(0, s.length - 1), t)
    }
}