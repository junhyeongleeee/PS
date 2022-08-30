package util

fun main() {
    val dx = arrayOf(0, -1, 1, 0)
    val dy = arrayOf(-1, 0, 0, 1)
    val places = arrayOf(
        arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
        arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO")
    )
    val result = mutableListOf<Int>()

    places.toMutableList().forEach { p ->
        var check = true

        run {
            // ["POOOP","OXXOX"...]
            p.forEachIndexed { i, s ->
                // ["p","O","O"...]
                s.forEachIndexed { j, c ->
                    var pCount = 0
                    for (k in 0..3){
                        val nx = j + dx[k]
                        val ny = i + dy[k]
                        if (nx >= 0 && ny >= 0 && nx < s.length && ny < p.size) {
                            val value = p[ny][nx]
                            when(c) {
                                'P' -> {
                                    if (value == 'P') {
                                        check = false
                                        return@run
                                    }
                                }
                                'O' -> {
                                    if (value == 'P'){
                                        pCount++
                                        if (pCount == 2){
                                            check = false
                                            return@run
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (check) result.add(1)
        else result.add(0)
    }

    result.forEach { println(it) }
}