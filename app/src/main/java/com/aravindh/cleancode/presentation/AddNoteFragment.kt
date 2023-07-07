package com.aravindh.cleancode.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aravindh.cleancode.R
import com.aravindh.cleancode.databinding.FragmentAddNoteBinding
import com.aravindh.cleancode.framework.viewmodel.AddNoteViewModel
import com.aravindh.cleancode.util.Utils
import com.aravindh.core.data.Note


class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    private lateinit var viewModel: AddNoteViewModel

    private var currentNote = Note(title = "", content = "", creationTime = 0L, updateTime = 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[AddNoteViewModel::class.java]
        observeViewModel()


        arguments?.let {
            val id = AddNoteFragmentArgs.fromBundle(it).noteId
            if (id != 0L) {
                viewModel.getNote(id)
            }
        }

        binding.saveNote.setOnClickListener {
            saveNote()
        }
    }

    private fun saveNote() {
        val title = binding.title.text.toString()
        val content = binding.content.text.toString()
        val time = System.currentTimeMillis()
        var creationTime = time

        if (title.trim().equals("", ignoreCase = true)) {
            Utils.showToast(context, "Title should not be empty!")
            return
        }

        if (content.trim().equals("", ignoreCase = true)) {
            Utils.showToast(context, "Content should not be empty!")
            return
        }

        if (currentNote.id != 0L) {
            creationTime = currentNote.creationTime
        }

        currentNote =
            Note(
                title = title, content = content, creationTime = creationTime, updateTime =
                time, id = currentNote.id
            )
        viewModel.saveNote(currentNote)
    }

    private fun observeViewModel() {
        viewModel.isSaved.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                Utils.showToast(context, "Note saved successfully!")
                Utils.popBackStack(binding.title)
            } else {
                Utils.showToast(context, "Sorry, Something went wrong!")
            }
        }

        viewModel.note.observe(viewLifecycleOwner) { note ->
            note?.let {
                currentNote = it
                binding.title.setText(currentNote.title)
                binding.content.setText(currentNote.content)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteNote) {
            if (currentNote.id != 0L) {
                viewModel.deleteNote(currentNote)
            }
        }
        return true
    }
}