package com.agprastyo.simplenotes.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@kotlinx.parcelize.Parcelize
@Entity
data class Note(
    @PrimaryKey (autoGenerate = true)
    val noteId: Int = 0,
    val noteTittle: String,
    val noteText: String
): Parcelable
