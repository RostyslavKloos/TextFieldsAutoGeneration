package com.rodev.textfieldsautogeneration.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodev.textfieldsautogeneration.domain.NotesRepository
import com.rodev.textfieldsautogeneration.data.local.model.Notes
import kotlinx.coroutines.launch

class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    fun insertNotes(note: Notes) = viewModelScope.launch {
        notesRepository.insert(note)
    }

    fun updateNotes(note: Notes) = viewModelScope.launch {
        notesRepository.update(note)
    }

    fun getSavedNotes() = notesRepository.getSavedNotes()

}