package com.rodev.textfieldsautogeneration.data.local

import androidx.room.*
import com.rodev.textfieldsautogeneration.data.local.model.Notes

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Update()
    suspend fun updateNotes(notes: Notes)

    @Query("SELECT * FROM notes")
    fun getNotes(): List<Notes>

    @Delete
    suspend fun deleteNotes(notes: Notes)
}