package kotlinCode.boj

fun main() = with(System.`in`.bufferedReader()){
    val x = readLine().toInt()

    // idx : 1, 2, 3, ...
    // tmp : 1, 3, 5, 8, ...
    var idx = 1
    var tmp = 0
    while (true) {
        tmp += idx
        if (tmp >= x) {
            break
        }
        idx++
    }
    tmp -= idx

    var cnt = 0
    while (++tmp < x) {
        cnt++
    }

    if (idx % 2 == 0) {
        println("${cnt + 1}/${idx - cnt}")
    }else{
        println("${idx - cnt}/${cnt + 1}")
    }
}