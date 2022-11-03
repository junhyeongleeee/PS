package kotlinCode.boj

import java.util.StringTokenizer
import kotlin.math.min

/**
 *   부분합
 *
 *   문제 :
 *   10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
 *   이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중,
 *   가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.
 *
 *   입력 :
 *   첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다.
 *   둘째 줄에는 수열이 주어진다.
 *   수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.
 *
 *   출력 : 첫째 줄에 구하고자 하는 최소의 길이를 출력한다.
 *   만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.
 *
 *   해결 방법 :
 *    - 수열 -> 배열
 *    - 연속된 수들의 부분합 구하기
 *      - N + (N-1) + (N-2) +...+ (N - K-1), K <= N , n2 ,
 *    - 합이 S 이상이 되는 것 중 가장 짧은 것의 길이
 *
 *
 *    N개의 수열이 있는데 연속된 수들의 합(부분합) S 이상인 것 중 가장 짧은 길이를 구하는 문제였습니다. 투포인터는 다음과 같이 나눴습니다.
 *    부분합이 S보다 적은 경우는 right 를 오른쪽으로 이동 더 큰 경우는 left 를 왼쪽으로 이동시킴으로써 더 적은 길이가 될 수 있는 기회를 갖게됩니다.
 */

fun main() {
    val inputLine = readln().split(" ")
    val N = inputLine[0].toInt()
    val S = inputLine[1].toInt()

    val st = StringTokenizer(readln())
    val arr = IntArray(N)
    repeat(N) {
        arr[it] = st.nextToken().toInt()
    }

    var left = 0
    var right = 0
    var result = 100001
    var sum = 0
    while (true) {
        if (sum >= S) {
            // sum 갱신
            sum -= arr[left]
            result = min(result, right - left)
            // left 이동
            left++
        }
        else if (right == N) {
            break
        }
        else {
            sum += arr[right++]
        }
    }
    if (result == 100001){
        println(0)
    }
    else {
        println(result)
    }
}