package com.aravindh.cleancode.framework.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.aravindh.core.data.Note
import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NoteDatabaseTest : TestCase() {

    private lateinit var db: NoteDatabase
    private lateinit var noteDao: NoteDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()
        noteDao = db.noteDao()
    }

    @Test
    fun readAndWriteNoteData() {
        GlobalScope.launch {
            val time = System.currentTimeMillis()
            val note = Note(
                title = "Testing", content = "Sample Content", creationTime = time,
                updateTime = time
            )
            noteDao.addNote(NoteEntity.fromNote(note))
            val notes: List<NoteEntity> = noteDao.getAllNotes()

            println("size: ${notes.size}")

            assertThat(notes.contains(NoteEntity.fromNote(note))).isTrue()
        }
    }


    @After
    public override fun tearDown() {
        db.close()
    }

}