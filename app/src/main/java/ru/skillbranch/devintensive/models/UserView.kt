package ru.skillbranch.devintensive.models

/**
 * Created by 580305 on 01.07.2019.
 */

class UserView(
    val id: String,
    val fullName: String,
    val nickName: String,
    var avatar: String? = null,
    var status: String? = "offline",
    var initials: String?
) {
    fun printMe() {
        println("""
            id: $id
            fullName: $fullName
            nickName: $nickName
            avatar: $avatar
            status: $status
            initials: $initials
        """.trimIndent())
    }
}