fun main() {
    decodeMorse(" .... . -.--   .--- ..- -.. .         ")
}

val morseCollection = mapOf (
    ".-" to "A",
    "-..." to "B",
    "-.-." to "C",
    "-.." to "D",
    "." to "E",
    "..-." to "F",
    "--." to "G",
    "...." to "H",
    ".." to "I",
    ".---" to "J",
    "-.-" to "K",
    ".-.." to "L",
    "--" to "M",
    "-." to "N",
    "---" to "O",
    ".--." to "P",
    "--.-" to "Q",
    ".-." to "R",
    "..." to "S",
    "-" to "T",
    "..-" to "U",
    "...-" to "V",
    ".--" to "W",
    "-..-" to "X",
    "-.--" to "Y",
    "--.." to "Z",
    ".----" to "1",
    "..---" to "2",
    "...--" to "3",
    "....-" to "4",
    "....." to "5",
    "-...." to "6",
    "--..." to "7",
    "---.." to "8",
    "----." to "9",
    "-----" to "0",
    "...---..." to "SOS"
)

fun decodeMorse(code: String): String {

    val morseCode = mutableListOf<Map<Int, String>>()
    val morseWord = code.split("   ")
    val words = mutableListOf<String>()

    morseWord.forEachIndexed { index, wordCollection ->
        var wordHolder = ""
        wordCollection.split(" ").forEach { characterCollection ->
            if (characterCollection.isNotBlank()) {
                wordHolder += morseCollection[characterCollection]
            }
        }
        if (wordCollection.isNotBlank()) {
            morseCode.add(mapOf(index to wordHolder))
        }
    }

    morseCode.forEach { morseMap ->
        morseMap.forEach { value ->
            words.add(value.value)
        }
    }

    return words.joinToString(" ")
}