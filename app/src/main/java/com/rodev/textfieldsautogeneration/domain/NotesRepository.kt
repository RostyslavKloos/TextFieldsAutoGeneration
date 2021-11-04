package com.rodev.textfieldsautogeneration.domain

import com.rodev.textfieldsautogeneration.data.local.model.Notes

interface NotesRepository {
    suspend fun insert(notes: Notes)

    suspend fun update(notes: Notes)

    fun getSavedNotes(): List<Notes>

    suspend fun deleteNotes(notes: Notes)
}