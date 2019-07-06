package ru.skillbranch.devintensive.extensions

/**
 * Created by 580305 on 06.07.2019.
 */

fun String.truncate(amount : Int = 16) : String {
    var tmp = this.trimEnd()
    tmp = if (tmp.length > amount) "${tmp.substring(0, amount).trimEnd()}..." else tmp
    return tmp
}

fun String.stripHtml() : String {
    var regex = "<[^>]*>".toRegex()
    var escapeSymbols = "[&<>'\"]+".toRegex()
    var trimmer = "\\s\\s+".toRegex()
    var res = regex.replace(this, "")
    res = escapeSymbols.replace(res, "")
    res = trimmer.replace(res, " ")
    return res
}