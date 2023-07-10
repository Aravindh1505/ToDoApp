package com.aravindh.cleancode.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aravindh.cleancode.R
import com.aravindh.cleancode.databinding.FragmentAddNoteBinding
import com.aravindh.cleancode.framework.viewmodel.AddNoteViewModel
import com.aravindh.cleancode.util.Utils
import com.aravindh.core.data.Note
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private lateinit var binding: FragmentAddNoteBinding

    private val viewModel: AddNoteViewModel by viewModels()

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
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeViewModel()
        arguments?.let {
            val id = AddNoteFragmentArgs.fromBundle(it).noteId
            if (id != 0L) {
                viewModel.getNote(id)
            }
        }
    }


    private fun observeViewModel() {
        viewModel.isSaved.observe(viewLifecycleOwner) { status ->
            when (status) {
                AddNoteViewModel.STATUS.ADD -> {
                    Utils.showToast(context, "Note added successfully!")
                    Utils.popBackStack(binding.title)
                }

                AddNoteViewModel.STATUS.UPDATE -> {
                    Utils.showToast(context, "Note updated successfully!")
                    Utils.popBackStack(binding.title)
                }

                AddNoteViewModel.STATUS.DELETE -> {
                    Utils.showToast(context, "Note deleted successfully!")
                    Utils.popBackStack(binding.title)
                }

                else -> {
                }
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            it?.let { message ->
                if (message.isNotEmpty()) {
                    Utils.showToast(context, message)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteNote) {
            viewModel.deleteNote()
        }
        return true
    }
}