package com.agprastyo.simplenotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.agprastyo.simplenotes.data.db.NotesDatabase
import com.agprastyo.simplenotes.repositories.NotesRepo
import com.agprastyo.simplenotes.viewmodel.NotesViewModel
import com.agprastyo.simplenotes.viewmodel.providerFactory.NotesViewModelProviderFactory

class MainActivity : AppCompatActivity() {

    val viewModel: NotesViewModel by lazy {
        val database = NotesDatabase.getDatabaseInstance(this)
        val notesRepo = NotesRepo(database)
        val notesProviderFactory = NotesViewModelProviderFactory(notesRepo)
        ViewModelProvider(this, notesProviderFactory)[NotesViewModel::class.java]

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}