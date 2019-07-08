package ru.skillbranch.devintensive.extensions

/**
 * Created by 580305 on 06.07.2019.
 */

fun TimeUnits.plural(value: Int) : String {

    var strVal: String = value.toString().takeLast(2)
    var value = strVal.toInt()
    var result: String
    when {
        strVal.toInt() in 5..20 -> result = getVal(this, TimeType.THIRDTYPE)
        strVal.endsWith("1") -> result = getVal(this, TimeType.FIRSTTYPE)
        strVal.endsWith("2").or(strVal.endsWith("3")).or(strVal.endsWith("4")) -> result = getVal(this, TimeType.SECONDTYPE)
        else -> result = getVal(this, TimeType.THIRDTYPE)

    }
    return "$value $result"
}

private fun getVal(tu: TimeUnits, type: TimeType ) : String {
    if (tu == TimeUnits.SECOND) {
        return when (type) {
            TimeType.FIRSTTYPE -> "секунду"
            TimeType.SECONDTYPE -> "секунды"
            TimeType.THIRDTYPE -> "секунд"
        }
    } else if (tu == TimeUnits.DAY){
        return when (type) {
            TimeType.FIRSTTYPE -> "день"
            TimeType.SECONDTYPE -> "дня"
            TimeType.THIRDTYPE -> "дней"
        }
    } else if (tu == TimeUnits.MINUTE) {
        return when (type) {
            TimeType.FIRSTTYPE -> "минуту"
            TimeType.SECONDTYPE -> "минуты"
            TimeType.THIRDTYPE -> "минут"
        }
    } else {
        return when (type) {
            TimeType.FIRSTTYPE -> "час"
            TimeType.SECONDTYPE -> "часа"
            TimeType.THIRDTYPE -> "часов"
        }
    }
}

private enum class TimeType {
    FIRSTTYPE,
    SECONDTYPE,
    THIRDTYPE
}