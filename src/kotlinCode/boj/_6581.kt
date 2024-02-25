package kotlinCode.boj

import java.util.StringTokenizer


/**
 *  제한 시간 : 1초
 *  메모리 제한 : 128 MB
 *
 *
 *  - 한 줄에는 80자보다 많은 글자가 출력되어서는 안 된다.
 *
 *  - 이번에 출력할 단어를 출력하고 나서도 현재 줄이 80글자를 넘지 않으면 현재 줄에 출력해도 좋다. 단, 80글자를 넘어가게 된다면 새로운 줄에 출력해야 한다.
 *  - <br> 태그를 읽게 되면, 새 줄을 시작한다.
 *  - <hr> 태그를 읽게 되면, 이미 줄의 첫 부분이 아니라면 새 줄을 시작한 뒤, '-'를 한 줄에 80글자를 입력한다. 그 후 다시 새 줄을 시작한다.
 *
 *  - 여러 개의 연속된 개행 문자, 공백 문자, 탭 문자는 하나의 공백문자로 출력한다.
 *
 *  풀이 과정
 *      -  이미 줄의 첫 부분이 아니라면, 반대의 경우, 새 줄을 시작하지 않고 해야 한다..?
 *          ex) a <br> <hr>
 *              a
 *              -------
 *
 */

private lateinit var st: StringTokenizer
private val sb = StringBuilder()

private val brTag = "<br>"
private val hrTag = "<hr>"
private val hrLine = "--------------------------------------------------------------------------------"
fun main() = with(System.`in`.bufferedReader()) {

    var lineSize = 0

    repeat(13) {
        st = StringTokenizer(readLine())

//        println("line: ${line}")

        while (st.hasMoreTokens()) {
            val word = st.nextToken()

            if (word.isEmpty()) continue

//            println("lineSize: $lineSize")
            sb.append(
                when(word) {
                    brTag -> {
                        lineSize = 0
                        removeLastBlank()
                        "\n"
                    }
                    hrTag -> {
                        lineSize = 0
                        removeLastBlank()
                        if (sb.last() == '\n') hrLine + "\n" else "\n" + hrLine + "\n"
                    }
                    else -> {
                        lineSize += word.length + 1
                        if (lineSize > 81) {
                            lineSize = word.length + 1
                            removeLastBlank()
                            "\n$word"
                        } else "$word "
                    }
                }
            )
        }
    }

    /*while (true) {
        val line = readLine() ?: break
        st = StringTokenizer(line)

//        println("line: ${line}")

        while (st.hasMoreTokens()) {
            val word = st.nextToken()

            if (word.isEmpty()) continue

//            println("lineSize: $lineSize")
            sb.append(
                when(word) {
                    brTag -> {
                        lineSize = 0
                        "\n"
                    }
                    hrTag -> {
                        lineSize = 0
                        if (sb.last().toString() == "\n") hrLine + "\n" else "\n" + hrLine + "\n"
                    }
                    else -> {
                        lineSize += word.length + 1
                        if (lineSize > 80) {
                            lineSize = word.length
                            "\n$word"
                        } else "$word "
                    }
                }
            )
        }
    }*/

    println(sb)
}

fun removeLastBlank() {
    if (sb.last() == ' ') {
        sb.deleteAt(sb.lastIndex)
    }
}