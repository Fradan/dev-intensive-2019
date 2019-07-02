package ru.skillbranch.devintensive.utils

/**
 * Created by 580305 on 01.07.2019.
 */
object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.split(' ', ignoreCase = true)
        var firstName = parts?.getOrNull(0).orEmpty()
        var lastName = parts?.getOrNull(1).orEmpty()
        return firstName to lastName
    }


    private var transliterationMap =
        mapOf(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "yo",
            'ж' to "dj",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "kh",
            'ц' to "ts",
            'ч' to "sh",
            'щ' to "sch",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya",
            'А' to "A",
            'Б' to "B",
            'В' to "V",
            'Г' to "G",
            'Д' to "D",
            'Е' to "E",
            'Ё' to "Yo",
            'Ж' to "Dj",
            'З' to "Z",
            'И' to "I",
            'Й' to "I",
            'К' to "K",
            'Л' to "L",
            'М' to "M",
            'Н' to "N",
            'О' to "O",
            'П' to "P",
            'Р' to "R",
            'С' to "S",
            'Т' to "T",
            'у' to "U",
            'ф' to "F",
            'Х' to "Kh",
            'Ц' to "Ts",
            'Ч' to "Sh",
            'Щ' to "Sch",
            'Ъ' to "",
            'Ы' to "I",
            'Ь' to "",
            'Э' to "E",
            'Ю' to "Yu",
            'Я' to "Ya")

    fun transliteration(payLoad: String, divider: String = " "): String {
        var words = payLoad.split(divider)

        val res = words.map { word ->
            var newWord = ""
            word.forEach { s ->
                newWord = newWord.plus(transliterationMap[s] ?: s)
            }
            newWord
        }

        return res.joinToString(separator = divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String {
        var firstNameInit = firstName?.getOrNull(0)
        var lastNameInit = lastName?.getOrNull(0)
        return listOfNotNull(firstNameInit, lastNameInit).joinToString(separator = "")
    }
}