package com.aravindh.cleancode.framework.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aravindh.core.data.Note

@Entity(tableName = "note")
class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    val title: String,

    val content: String,

    @ColumnInfo(name = "created_at")
    val creationTime: Long,

    @ColumnInfo(name = "updated_at")
    val updateTime: Long
) {

    companion object {
        fun fromNote(note: Note) = NoteEntity(
            id = note.id,
            title = note.title,
            content = note.content,
            creationTime = note.creationTime,
            updateTime = note.updateTime
        )
    }

    fun toNote() = Note(
        id = id,
        title = title,
        content = content,
        creationTime = creationTime,
        updateTime = updateTime
    )
}