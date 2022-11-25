package com.agprastyo.simplenotes.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.agprastyo.simplenotes.data.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM Note ORDER BY noteId DESC")
    fun selectNotes(): Flow<List<Note>>

    @Query("SELECT * FROM Note WHERE noteTittle LIKE '%'||:searchQuery||'%'")
    fun searchInNotesTitle(searchQuery: String): Flow<List<Note>>

    @Query("DELETE FROM Note")
    suspend fun deleteAllNotes()

}