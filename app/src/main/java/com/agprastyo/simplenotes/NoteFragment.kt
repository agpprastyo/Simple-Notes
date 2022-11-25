package com.agprastyo.simplenotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.agprastyo.simplenotes.data.Note
import com.agprastyo.simplenotes.databinding.FragmentNoteBinding
import com.agprastyo.simplenotes.viewmodel.NotesViewModel

class NoteFragment : Fragment(R.layout.fragment_note) {
    private lateinit var binding: FragmentNoteBinding
    private val args by navArgs<NoteFragmentArgs>()
    private lateinit var viewModel: NotesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.note?.let {
            binding.apply {
                edTitle.setText(it.noteText)
                edNote.setText(it.noteText)
            }
            binding.imgDeleteNote.visibility = View.VISIBLE
        }
        binding.apply {
            btnSaveNote.setOnClickListener {
                val id = args.note?.noteId ?: 0
                val noteTitle = edTitle.text.toString()
                val noteText = edNote.text.toString()

                Note(id, noteTitle, noteText).also { note ->
                    if (noteTitle.isEmpty() && noteText.isEmpty()) {
                        Toast.makeText(context, "All Field Must be Filled", Toast.LENGTH_SHORT)
                            .show()
                        return@setOnClickListener
                    }
                    viewModel.upsertNote(note)
                    findNavController().navigateUp()
                }
            }
        }

        binding.apply {
            imgDeleteNote.setOnClickListener {
                val noteid = args.note!!.noteId
                val noteTitle = edTitle.text.toString()
                val noteText = edNote.text.toString()

                Note(noteid, noteTitle, noteText).also {
                    viewModel.deleteNote(it)
                    findNavController().navigateUp()
                }
            }
        }
    }
}