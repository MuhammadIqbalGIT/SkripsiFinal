package com.example.myapplicationskripsiiqbal3.utils

import android.os.Build
import android.util.Log
import timber.log.Timber
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import java.util.concurrent.TimeUnit

object DateTimeFormatter {
    fun getDateTimeNow(pattern: String = "yyyy-MM-dd HH:mm:ss"): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale("id", "ID")))
        } else {
            SimpleDateFormat(pattern, Locale("id", "ID")).format(Date())
        }
    }

    fun getDateTimeNowT(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale("id", "ID")))
        } else {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("id", "ID")).format(Date())
        }
    }

    fun getDateNow(pattern: String? = "yyyy-MM-dd"): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern, Locale("id", "ID")))
        } else {
            SimpleDateFormat(pattern, Locale("in_ID")).format(Date())
        }
    }

    fun getDate(pattern: String, diff: Int): String {

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, diff)

        val format = SimpleDateFormat(pattern, Locale("id", "ID"))

        return format.format(calendar.time)
    }

    fun format(pattern: String? = "dd/MM/yyyy HH:mm", strDate: String): String =
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val dateFormat = DateTimeFormatter.ofPattern(pattern, Locale("id", "ID")).withZone(
                    ZoneId.of("Asia/Jakarta")
                )
                LocalDateTime.parse(strDate).format(dateFormat).toString()
            } else {
                val strDateReplaced = strDate.replace("T", " ")
                val newDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale("id", "ID")).parse(
                    strDateReplaced
                )
                val sdf = SimpleDateFormat(pattern, Locale("id", "ID"))
                val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
                sdf.timeZone = timeZone
                sdf.format(newDate!!)
            }
        } catch (e: Exception) {
            Timber.tag("DateTimeFormatter").e("format: error convert date %s", e.message)
            ""
        }

    fun formatDate(
        pattern: String,
        currentFormat: String = "yyyy-MM-dd'T'HH:mm:ss",
        date: String
    ): String {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val dateFormat = DateTimeFormatter.ofPattern(pattern, Locale("id", "ID")).withZone(
                ZoneId.of("Asia/Jakarta")
            )

            return if (currentFormat.isNotEmpty()) {
                val newDate = SimpleDateFormat(currentFormat, Locale("id", "ID")).parse(date)
                val sdf = SimpleDateFormat(pattern, Locale("id", "ID"))
                val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
                sdf.timeZone = timeZone
                return sdf.format(newDate ?: "")
            } else
                LocalDateTime.parse(date).format(dateFormat).toString()
        } else {
            val newDate = if (currentFormat.isNotEmpty())
                SimpleDateFormat(currentFormat, Locale("id", "ID")).parse(date)
            else {
                val strDateReplaced = date.replace("T", " ")
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID")).parse(strDateReplaced)
            }
            val sdf = SimpleDateFormat(pattern, Locale("id", "ID"))
            val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            sdf.timeZone = timeZone
            return sdf.format(newDate ?: "")
        }
    }

    fun formatTimeOnly(strDate: String): String {
        val pattern = "HH:mm:ss"
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.parse(strDate).format(DateTimeFormatter.ofPattern(pattern)).toString()
        } else {
            val newDate =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID")).parse(strDate) ?: ""
            val sdf = SimpleDateFormat(pattern, Locale("id", "ID"))
            sdf.format(newDate)
        }
    }

    /**
     * return dalam satuan menit
     */
    fun diffDateStartEnd(startTime: String, endTime: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID"))
        var d1: Date? = null
        var d2: Date? = null
        try {
            d1 = format.parse(startTime)
            d2 = format.parse(endTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = d1!!.time - d2!!.time
//        var diffSeconds = diff / 1000
//        val diffMinutes: Long = diff / (60 * 1000)
//        diffSeconds %= 60
//        return String.format("%02d:%02d", diffMinutes, diffSeconds)
        return diff / (60 * 1000)
    }

    /**
     * return dalam satuan detik/seconds
     */
    fun diffDateStartEndSeconds(startTime: String, endTime: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID"))
        var d1: Date? = null
        var d2: Date? = null
        try {
            d1 = format.parse(startTime)
            d2 = format.parse(endTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val diff = d1!!.time - d2!!.time
//        var diffSeconds = diff / 1000
//        val diffMinutes: Long = diff / (60 * 1000)
//        diffSeconds %= 60
//        return String.format("%02d:%02d", diffMinutes, diffSeconds)
        return diff / 1000
    }

    fun diffDateStartEndVa(startTime: String, endTime: String): Long {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale("id", "ID"))
        var d1: Date? = null
        var d2: Date? = null
        try {
            d1 = format.parse(startTime)
            d2 = format.parse(endTime)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        //        var diffSeconds = diff / 1000
//        val diffMinutes: Long = diff / (60 * 1000)
//        diffSeconds %= 60
//        return String.format("%02d:%02d", diffMinutes, diffSeconds)
        return d2!!.time - d1!!.time
    }

    fun formatFromMilliseconds(milliseconds: Long, pattern: String = "dd-MM-yyyy"): String =
        try {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = milliseconds

            val sdf = SimpleDateFormat(pattern, Locale("id", "ID"))
            val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            sdf.timeZone = timeZone
            sdf.format(calendar.time)
        } catch (e: Exception) {
            Timber.tag("DateTimeFormatter").e("format: error convert date %s", e.message)
            ""
        }

    fun formatMillisToTimer(ms: Long): String {
        var millis = ms

        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        millis -= TimeUnit.HOURS.toMillis(hours)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
        millis -= TimeUnit.MINUTES.toMillis(minutes)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis)
        millis -= TimeUnit.SECONDS.toMillis(seconds)

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
//        return if(includeMillis){
//
//        }else{
//            String.format("%02d:%02d", minutes,seconds)
//        }
    }

    fun formatToCalendar(currentFormat: String = "yyyy-MM-dd", date: String): Calendar? =
        try {
            val newDate: Date? = SimpleDateFormat(currentFormat, Locale("id", "ID")).parse(date)
            val sdf = SimpleDateFormat(currentFormat, Locale("id", "ID"))
            val timeZone = TimeZone.getTimeZone("Asia/Jakarta")
            sdf.timeZone = timeZone
            sdf.format(newDate!!)
            sdf.calendar
        } catch (e: Exception) {
            Log.e("DateTimeFormatter", "formatToCalendar: " + e.message)
            null
        }
}