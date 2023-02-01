package kotlinCode.boj

import java.lang.Math.max

/**
 *  계란으로 계란치기
 *  - 가장 왼쪽에 계란을 든다.
 *  - 손에 든 계란으로 깨지지 않은 다른 계란을 친다.
 *      단, 손에 든 계란이 깨졌거나, 꺠지지 않은 다른 계란이 없으면 치지 않고 넘어간다.
 *      이후 손에 든 계란을 원래 자리에 내려놓고 3번 과정을 진행한다.
 *  - 가장 최근에 든 계란이 한 칸 오른쪽 계란을 손에 들고 2번 과정을 진행한다.
 *      단, 가장 최근에 든 계란이 가장 오른쪽에 위치한 계란일 경우 종료한다.
 *
 *  - 내구도 S(1<= S <= 300), 무게 W(1<= W <= 300)
 *  - 계란의 수 N (1<= N <= 8)
 */

private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val eggs = mutableListOf<Egg>()
data class Egg(var s: Int, var w: Int)
fun main() = with(br){
    val n = readLine().toInt()

    repeat(n) {
        val (s, w) = readLine().split(" ").map { it.toInt() }
        eggs.add(Egg(s, w))
    }
    var result = 0

    fun dfs(s: Int, cnt: Int) {
        if (s == n) {
            return
        }
        val egg = eggs[s]
        if (egg.s <= 0) {
            dfs(s + 1, cnt)
            return
        }
        for (i in 0 until n) {
            var count = 0
            if (s == i) continue
            if (eggs[i].s <= 0) continue

            egg.s -= eggs[i].w
            eggs[i].s -= egg.w

            if (egg.s <= 0) {
                count++
            }
            if (eggs[i].s <= 0) {
                count++
            }
            result = result.coerceAtLeast(count + cnt)
            dfs(s + 1, cnt + count)

            egg.s += eggs[i].w
            eggs[i].s += egg.w
        }
    }
    dfs(0,0)
    println(result)
}