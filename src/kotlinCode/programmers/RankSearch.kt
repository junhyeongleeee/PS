package kotlinCode

fun main() {
    val info = arrayOf(
        "java backend junior pizza 150",
        "python frontend senior chicken 210",
        "python frontend senior chicken 150",
        "cpp backend senior pizza 260",
        "java backend junior chicken 80",
        "python backend senior chicken 50"
    )

    val query = arrayOf(
        "java and backend and junior and pizza 100",
        "python and frontend and senior and chicken 200",
        "cpp and - and senior and pizza 250",
        "- and backend and senior and - 150",
        "- and - and - and chicken 100",
        "- and - and - and - 150"
    )

    val list =
        query.toList()
            .asSequence()
            .map {
                it.replace(" and ", " ")
                    .split(" ")
            }.toList()

    val newInfo = info.toList().map { it.split(" ") }

    val result = mutableListOf<Int>()
    list.forEach { line ->

        // ex) line[i] = [java, backend, junior, pizza, 100]
        var cnt = 0

        // "java backend junior pizza 150"
        for (i in newInfo.indices){
            var check = true

            // 100 > 150
            if (line.last().toInt() > newInfo[i].last().toInt()) continue

            // ex) [java, backend, junior, pizza, 150]

            for (j in 0 until newInfo[i].size - 1) {
                if (line[j] != "-" && line[j] != newInfo[i][j]){
                    check = false
                    break
                }
            }
            if (check) cnt++
        }

        if (cnt != 0 ) result.add(cnt)
    }

    println(result)
}