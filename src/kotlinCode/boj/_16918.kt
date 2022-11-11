package kotlinCode.boj

/**
 *
 *  봄버맨
 *
 *  R X C 인 직사각형, 격자의 각 칸은 비어있거나 폭탄
 *
 *  폭탄이 있는 칸은 3초가 지난 후에 폭발한다.
 *  폭탄이 폭발한 이후에는 폭탄이 있던 칸이 파괴되어 빈 칸이 되며, 인접한 네 칸도 함께 파괴된다.
 *  인접한 칸에 폭탄이 있는 경우 인접한 폭탄은 폭발 없이 파괴 된다.
 *  연쇄 반응은 없다.
 *
 *  1- 가장 처음에 폭탄 설치
 *  2- 다음 1초 동안 아무것도 안함
 *  3- 다음 1초 동안 폭탄이 설치되어 있지 않은 모든 칸에 폭탄 설치
 *  4- 1초가 지난 후 3초 전에 설치된 폭탄이 모두 폭발
 *  5- 3과 4를 반복
 *
 *
 *  폭탄 설치 - 모든 칸에 폭탄 설치 - 1초가 지난 후 3초전 설치된 폭탄 모두 폭발
 *   - 모든 칸에 폭탄 설치 - 1초가 지난 후 3초전 설치된 폭탄 모두 폭발
 *
 *   1 <= R, C, N <= 200
 *
 *  200 * 200 + 200*200*4 +
 */

val dir = arrayOf(
    intArrayOf(1,0),
    intArrayOf(0,1),
    intArrayOf(-1,0),
    intArrayOf(0,-1)
)

fun install(r: Int, c : Int, graph: Array<IntArray>){
    for(i in 0 until r){
        for(j in 0 until c){
            if(graph[i][j]==0){
                graph[i][j]=2
            }
        }
    }
}

fun bomb(r : Int, c : Int, graph: Array<IntArray>){
    val graphCopy = Array(r){IntArray(c)}
    //복사하면서 나중 폭탄을 1로 변경
    for(i in 0 until r){
        for(j in 0 until c){
            graphCopy[i][j] = graph[i][j]
            if(graph[i][j]==2){
                graph[i][j]=1
            }
        }
    }
    for(i in 0 until r){
        for(j in 0 until c){
            if(graphCopy[i][j]==1){
                graph[i][j] =0
                for(k in 0 until 4){
                    val nr = i+dir[k][0]
                    val nc = j+dir[k][1]
                    if(nr !in 0 until r || nc !in 0 until c) continue
                    graph[nr][nc] =0
                }
            }
        }
    }
}

fun play(r : Int, c : Int, n : Int, graph: Array<IntArray>){
    var time=0
    var bombTime =3
    while(time++<n){
        //설치
        if(time%2==0){
            install(r,c,graph)
        }
        if(time==bombTime){
            bomb(r,c,graph)
            bombTime +=2
        }
    }
}

fun main() = with(System.out.bufferedWriter()){
    val br = System.`in`.bufferedReader()
    val (r,c,n) = br.readLine().split(' ').map{it.toInt()}
    val graph = Array(r){r->
        val line = br.readLine()
        var idx=0
        IntArray(c){c->
            if(line[idx++]=='.') 0 else 1
        }
    }
    play(r,c,n,graph)
    for(i in 0 until r){
        for(j in 0 until c){
            if(graph[i][j]==0){
                write(".")
            }
            else{
                write("O")
            }
        }
        write("\n")
    }
    close()
}

/*
private val br = System.`in`.bufferedReader()
private val bw = System.out.bufferedWriter()
private val dx = intArrayOf(-1, 1, 0, 0)
private val dy = intArrayOf(0, 0, -1, 1)

fun main() = with(br) {
    val (r, c, n) = readLine().split(" ").map { it.toInt() }

    val arr = Array(r) { CharArray(c) }
    val allBom = Array(r) { CharArray(c) { 'O' } }

    repeat(r) { i ->
        readLine().forEachIndexed { j, c ->
            arr[i][j] = c
        }
    }

    when ((n - 1) % 2) {
        1 -> {
            printArray(allBom)
        }
        0 -> {
            if ((n - 1) % 4 != 0) {
                val dots = mutableListOf<Pair<Int, Int>>()

                arr.forEachIndexed { i, chars ->
                    chars.forEachIndexed { j, c ->
                        if (c == 'O') {
                            dots.add(Pair(i, j))
                            allBom[i][j] = '.'
                        }
                    }
                }

                dots.forEach { point ->

                    repeat(4) {
                        val nx = point.second + dx[it]
                        val ny = point.first + dy[it]

                        if (nx in 0 until c && ny in 0 until r) {
                            allBom[ny][nx] = '.'
                        }
                    }
                }
                printArray(allBom)
            } else {
                printArray(arr)
            }
        }
    }
}

fun printArray(arr: Array<CharArray>) {
    arr.forEach {
        println(it.joinToString(""))
    }
}*/
