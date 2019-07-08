package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.time.Duration
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by 580305 on 01.07.2019.
 */

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    var diff = this.time - date.time;

    var day = TimeUnit.MILLISECONDS.toDays(diff)
    var hh = (TimeUnit.MILLISECONDS.toHours(diff) - TimeUnit.DAYS.toHours(day))
    var mm = (TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)))
    var ss = (TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff))))

    return "Дней: $day часов: $hh минут: $mm секунд: $ss"
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

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