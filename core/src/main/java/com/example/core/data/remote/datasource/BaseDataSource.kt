package com.example.core.data.remote.datasource

abstract class BaseDataSource {

    val TAG = this::class.java.simpleName

    /*suspend fun checkOrRefreshToken(): String {
        return try {

            val tempToken: String = session.getToken()
            val tempTokenExp: String = session.getTokenTime()

//            val dateTemp = "2022-02-23T14:28:25.1332976+07:00"
            var expToken = 0L
            if (tempTokenExp.isNotEmpty()) {
                val date1 =
                    DateTimeFormatter.formatDate(pattern = "yyyy-MM-dd HH:mm:ss", currentFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ", tempTokenExp)
                val date2 = DateTimeFormatter.getDateTimeNow()
                expToken = DateTimeFormatter.diffDateStartEnd(date1, date2) ?: 0L
            }

            if (tempToken.isEmpty() || tempToken.isEmpty() || expToken <= 15) {
                val token = apiService.getToken()
                session.updateToken(token?.token ?: "", token?.expirationDate ?: "")
            }
            session.getToken()
        } catch (e: Exception) {
            Timber.tag("BaseDataSource").e("Refresh Token Error : %s", e.message)
            session.getToken()
        }
    }*/
}