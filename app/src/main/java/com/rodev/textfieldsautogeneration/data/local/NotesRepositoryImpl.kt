package com.rodev.textfieldsautogeneration.data.local

import com.rodev.textfieldsautogeneration.domain.NotesRepository
import com.rodev.textfieldsautogeneration.data.local.model.Notes


class NotesRepositoryImpl(private val db: NotesDatabase): NotesRepository {

    override suspend fun insert(notes: Notes) = db.notesDao().insertNotes(notes)

    override suspend fun update(notes: Notes) = db.notesDao().updateNotes(notes)

    override fun getSavedNotes() = db.notesDao().getNotes()

    override suspend fun deleteNotes(notes: Notes) = db.notesDao().deleteNotes(notes)
}