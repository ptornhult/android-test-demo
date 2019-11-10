package com.sqisland.android.test_demo

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object DateUtils {
    private val SHORT_DATE = DateTimeFormat.forPattern("yyyy-MM-dd")
    fun format(dateTime: DateTime): String {
        return SHORT_DATE.print(dateTime)
    }
}