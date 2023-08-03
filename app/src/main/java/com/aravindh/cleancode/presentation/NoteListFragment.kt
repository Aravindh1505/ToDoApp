package com.aravindh.cleancode.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.aravindh.cleancode.databinding.FragmentNoteListBinding
import com.aravindh.cleancode.framework.viewmodel.NoteListViewModel
import com.aravindh.cleancode.presentation.adapter.NoteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID


@AndroidEntryPoint
class NoteListFragment : Fragment(), NoteClickAction {

    private lateinit var binding: FragmentNoteListBinding

    private val viewModel: NoteListViewModel by viewModels()

    private val noteListAdapter = NoteListAdapter(arrayListOf(), this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val guid = UUID.randomUUID().toString()
        println("GUID: $guid")



        binding.addNoteListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        observeViewModel()
        binding.addNote.setOnClickListener {
            gotoAddNote()
        }
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner) { list ->
            binding.loadingView.visibility = View.GONE
            binding.noDataView.visibility = View.GONE

            if (list.isEmpty()) {
                binding.noDataView.visibility = View.VISIBLE
            }

            noteListAdapter.updateNotes(list.sortedByDescending { it.updateTime })
        }
    }

    private fun gotoAddNote(id: Long = 0L) {
        val action = NoteListFragmentDirections.actionListFragmentToAddNoteFragment(id)
        Navigation.findNavController(binding.addNoteListView).navigate(action)
    }

    override fun onClick(id: Long) {
        gotoAddNote(id)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }
}