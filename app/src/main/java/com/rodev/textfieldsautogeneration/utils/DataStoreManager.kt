package com.rodev.textfieldsautogeneration.utils

import android.content.Context
import android.content.SharedPreferences

class AppPrefManager(context: Context) {
    companion object {
        private const val PREF_NAME = "appPrefs"
        private const val KEY_IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        private const val KEY_RANDOM_FIELDS_NUMBER = "RandomFieldsNumber"
    }

    private val prefs: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val prefsEditor: SharedPreferences.Editor = prefs.edit()


    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        prefsEditor.putBoolean(KEY_IS_FIRST_TIME_LAUNCH, isFirstTime)
        prefsEditor.commit()
    }

    fun isFirstTimeLaunch(): Boolean = prefs.getBoolean(KEY_IS_FIRST_TIME_LAUNCH, true)

    fun setRandomFieldsNumber(number: Int) {
        prefsEditor.putInt(KEY_RANDOM_FIELDS_NUMBER, number)
        prefsEditor.commit()
    }

    fun getRandomFieldsNumber(): Int = prefs.getInt(KEY_RANDOM_FIELDS_NUMBER, 0)
}


