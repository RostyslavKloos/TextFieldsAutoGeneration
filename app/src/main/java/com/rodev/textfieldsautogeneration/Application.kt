package com.rodev.textfieldsautogeneration

import android.app.Application
import com.rodev.textfieldsautogeneration.utils.AppPrefManager
import com.rodev.textfieldsautogeneration.utils.NumberGeneration

class Application : Application() {

    private val prefs by lazy {
        AppPrefManager(this)
    }

    override fun onCreate() {
        super.onCreate()

        if (prefs.isFirstTimeLaunch()) {
            generateRandomNumber()
            prefs.setFirstTimeLaunch(false)
        }
    }

    private fun generateRandomNumber(): Int {
        val numberGeneration = NumberGeneration().generateRandomInt(20)
        prefs.setRandomFieldsNumber(numberGeneration)
        return numberGeneration
    }
}
