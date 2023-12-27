package com.example.core.data.local.session

import android.content.Context
import android.util.Log
import com.example.core.utils.AppPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

private const val PREF_FTP = "PREF_FTP_URL"
private const val PREF_LIVE_CHAT = "PREF_LIVE_CHAT_URL"
private const val PREF_SESSION_CUSTOMER_ID = "PREF_SESSION_CUSTOMER_ID"
private const val PREF_SESSION_CUSTOMER_NAME = "PREF_SESSION_CUSTOMER_NAME"
private const val PREF_SESSION_TOKEN = "PREF_SESSION_TOKEN"
private const val PREF_SESSION_CART = "PREF_SESSION_CART"
private const val PREF_SESSION_KEY_CODE = "PREF_SESSION_KEY_CODE"
private const val PREF_SESSION_USER_CODE = "PREF_SESSION_USER_CODE"

private const val NEW_PREF_SESSION_TOKEN = "NEW_PREF_SESSION_TOKEN"
private const val NEW_PREF_SESSION_TOKEN_DATE = "NEW_PREF_SESSION_TOKEN_DATE"
private const val NEW_PREF_SESSION_USER_TICKET = "NEW_PREF_SESSION_USER_TICKET"


private const val SESSION_PREFERENCES_NAME = "mss_merchant"

/**
 * Harus diingat ketika ada perubahan schema di SharedPreference terutama yang disimpan sebagai json string,
 * jika ada perubahan tipe data, penambahan field dll harus diperhatikan karena tidak auto migrate
 * akan terjadi conflict/force close
 */

class Session @Inject constructor() {

//
//    private val name = "mss_ecommerce_pref"
//    private val mode = Context.MODE_PRIVATE
//
//    private val preferences = context.getSharedPreferences(name, mode)
//
//    private val appPreferences = AppPreferences(preferences)
//
//    //    Splash Screen Session
//    fun saveFtpUrl(urlFtp: String) {
//        appPreferences.setString(PREF_FTP, urlFtp)
//    }
//
//    fun getFtpUrl(): String =
//        appPreferences.getString(PREF_FTP, "")
//
//    fun saveLiveChatUrl(url: String) {
//        appPreferences.setString(PREF_LIVE_CHAT, url)
//    }
//
//    fun getLiveChatUrl(): String = appPreferences.getString(PREF_LIVE_CHAT, "")
//    //    Splash Screen Session
//
//    fun saveSessionCustomerID(customerID: Int) {
//        appPreferences.setInt(PREF_SESSION_CUSTOMER_ID, customerID)
//    }
//
//    fun getSessionCustomerID(): Int = appPreferences.getInt(PREF_SESSION_CUSTOMER_ID)
//
//    fun saveSessionCustomerName(name: String) {
//        appPreferences.setString(PREF_SESSION_CUSTOMER_NAME, name)
//    }
//
//    fun getSessionCustomerName(): String = appPreferences.getString(PREF_SESSION_CUSTOMER_NAME, "")
//
//    fun saveSessionKeyCode(code: String) {
//        appPreferences.setString(PREF_SESSION_KEY_CODE, code)
//
//        Log.d("datakeycode", code)
//    }
//
//    fun getSessionKeyCode(): String = appPreferences.getString(PREF_SESSION_KEY_CODE, "")
//
//    fun saveToken(token: String) = appPreferences.setString(PREF_SESSION_TOKEN, token)
//
//
//    fun getToken(): String = appPreferences.getString(PREF_SESSION_TOKEN, "")
//
//    fun getTokenNew(): String = appPreferences.getString(NEW_PREF_SESSION_TOKEN, "")
//    fun getTokenTimeNew(): String = appPreferences.getString(NEW_PREF_SESSION_TOKEN_DATE, "")
//    fun saveSessionUserCode(userCode: String) {
//        appPreferences.setString(PREF_SESSION_USER_CODE, userCode)
//    }
//
//    fun getUserCode(): String = appPreferences.getString(PREF_SESSION_USER_CODE, "")
//
//    fun updateToken(newToken: String, date: String) {
//        appPreferences.setString(NEW_PREF_SESSION_TOKEN, newToken)
//        appPreferences.setString(NEW_PREF_SESSION_TOKEN_DATE, date)
//    }
//
//    /**
//     * Delete Login Session
//     */
//    fun deleteAllSessionLogin() {
//        appPreferences.delete(PREF_SESSION_CUSTOMER_ID)
//        appPreferences.delete(PREF_SESSION_CUSTOMER_NAME)
//        appPreferences.delete(PREF_SESSION_TOKEN)
//        appPreferences.delete(PREF_SESSION_CART)
//        appPreferences.delete(PREF_SESSION_KEY_CODE)
//        appPreferences.delete(NEW_PREF_SESSION_TOKEN)
//        appPreferences.delete(NEW_PREF_SESSION_TOKEN_DATE)
//    }
//
//    /**
//     * Cart Session
//     */
//    fun saveSelectedCart(cartIdList: List<Long>) {
//        val gsonCart = Gson().toJson(cartIdList)
//
//        appPreferences.setString(PREF_SESSION_CART, gsonCart)
//    }
//
//    fun getSelectedCartList(): List<Long>? {
//        val temp = appPreferences.getString(PREF_SESSION_CART, "")
//
//        return if (temp.isNotEmpty()) {
//            return try {
//                val type = object : TypeToken<List<Long>>() {}.type
//                Gson().fromJson(temp, type)
//            } catch (e: Exception) {
//                listOf()
//            }
//        } else
//            listOf()
//    }
}