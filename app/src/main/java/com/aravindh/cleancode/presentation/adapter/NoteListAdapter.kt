package com.aravindh.cleancode.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aravindh.cleancode.R
import com.aravindh.cleancode.databinding.ItemNoteBinding
import com.aravindh.cleancode.presentation.NoteClickAction
import com.aravindh.cleancode.util.Utils
import com.aravindh.core.data.Note

class NoteListAdapter(private val notes: ArrayList<Note>, private val action: NoteClickAction) :
    RecyclerView.Adapter<NoteListAdapter
    .NoteViewHolder>() {

    fun updateNotes(updatedNotes: List<Note>) {
        notes.clear()
        notes.addAll(updatedNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) = holder.bind(notes[position])


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNoteBinding.bind(view)
        private val titleView = binding.title
        private val contentView = binding.content
        private val dateView = binding.date

        fun bind(note: Note) {
            titleView.text = note.title
            contentView.text = note.content
            dateView.text = Utils.formatDateAndTime(note.updateTime)

            binding.noteLayout.setOnClickListener {
                action.onClick(note.id)
            }
        }
    }
}