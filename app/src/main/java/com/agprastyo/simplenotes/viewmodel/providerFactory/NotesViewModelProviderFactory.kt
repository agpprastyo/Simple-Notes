package com.agprastyo.simplenotes.viewmodel.providerFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.agprastyo.simplenotes.repositories.NotesRepo
import com.agprastyo.simplenotes.viewmodel.NotesViewModel

class NotesViewModelProviderFactory(
    private val notesRepo: NotesRepo
): ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(notesRepo) as T
    }
}