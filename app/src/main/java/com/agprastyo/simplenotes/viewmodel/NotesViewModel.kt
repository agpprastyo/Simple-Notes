package com.agprastyo.simplenotes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agprastyo.simplenotes.data.Note
import com.agprastyo.simplenotes.repositories.NotesRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRepo: NotesRepo
): ViewModel() {

    val notes = notesRepo.getNotes()
    private val _searchNotes = MutableStateFlow<List<Note>>(emptyList())
    val searchNotes: StateFlow<List<Note>> = _searchNotes

    fun upsertNote(note: Note) = viewModelScope.launch {
        notesRepo.upsertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteNote(note)
    }

    fun deleteAllNote(note: Note) = viewModelScope.launch {
        notesRepo.deleteAllNotes()
    }

    fun searchNote(searchQuery: String) = viewModelScope.launch {
        notesRepo.searchNotes(searchQuery).collect { notesList ->
            _searchNotes.emit(notesList)
        }
    }


}