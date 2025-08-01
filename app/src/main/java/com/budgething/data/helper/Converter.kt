package com.budgething.data.helper

import androidx.room.TypeConverter
import java.time.LocalDate

class SubList {

    @TypeConverter
    fun fromSubList(sub: List<String>): String {
        return sub.joinToString(separator = "||")
    }

    @TypeConverter
    fun toSubList(data: String): List<String> {
        return if (data.isEmpty()) emptyList() else data.split("||")
    }
}

class LocalDateConverter {
    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.toString()

    @TypeConverter
    fun toLocalDate(dateString: String?): LocalDate? =
        dateString?.let { LocalDate.parse(it) }
}