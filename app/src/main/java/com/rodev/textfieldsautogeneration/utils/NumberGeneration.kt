package com.rodev.textfieldsautogeneration.utils

class NumberGeneration {
    fun generateRandomInt(maxNumber: Int): Int {
        return (1..maxNumber).random()
    }
}