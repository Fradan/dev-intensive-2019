package ru.skillbranch.devintensive.models

/**
 * Created by 580305 on 01.07.2019.
 */
class Chat (
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val messages : MutableList<BaseMessage> = mutableListOf()
)