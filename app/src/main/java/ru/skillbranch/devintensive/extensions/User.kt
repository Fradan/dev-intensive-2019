package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

/**
 * Created by 580305 on 01.07.2019.
 */

fun User.toUserView() : UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName, lastName)
    val status = if (lastVisit == null) "Ни разу не был" else if (isOnline) "online" else "последний раз был ${lastVisit.humanizeDiff()}"

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        initials = initials,
        status = status
    )
}

