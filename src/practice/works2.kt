package practice

/**
 *  유저 채팅을 분석하여 비속어 단속 프로그램을 만든다.
 *  단어 사전을 참고하여 비속어를 사용한 유저 채팅 단속
 *
 *  유저 채팅은 알파벳 소문자 or 특수문자 . 로 이루어졌다. 공백으로 단어를 구분
 *
 *  비속어 단어와 완벽히 일치한 단어가 있다면 단어 길이 만큼 #
 *  단어에 포함된 점.을 1이상 k이하 길이의 알파벳으로 대체했을 때 비속어가 된다면 #
 *
 *  1 <= k <= 5
 *  1 <= dic <= 50
 *      - 1 <= 원소의 길이 <= 10
 *
 */
private val map = hashMapOf<String, Boolean>()
private val visited = BooleanArray(10)
private lateinit var dicWord : CharArray
fun main() {
    val k = 2
    val dic = arrayOf("slang", "badword")
    val chat = "badword ab.cd bad.ord .word sl.. bad.word"

    dic.forEach { map[it] = true }

    val words = chat.split(" ")

    for (word in dic) {
        dicWord = word.toCharArray()
        for (i in 1 .. k) {
        }
    }

    map.forEach { t, u -> println(t) }
}
fun mappingDic(idx: Int, cnt: Int, k: Int) {


}