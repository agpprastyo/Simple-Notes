package com.agprastyo.simplenotes.repositories

import com.agprastyo.simplenotes.data.Note
import com.agprastyo.simplenotes.data.db.NotesDatabase

class NotesRepo(
    notesDatabase: NotesDatabase
) {

    private val notesDao = notesDatabase.noteDao

    suspend fun upsertNote(note: Note) = notesDao.upsertNote(note)

    suspend fun deleteNote(note: Note) = notesDao.upsertNote(note)

    fun getNotes() = notesDao.selectNotes()

    fun searchNotes(searchQuery: String) = notesDao.searchInNotesTitle(searchQuery)

    suspend fun deleteAllNotes() = notesDao.deleteAllNotes()
}