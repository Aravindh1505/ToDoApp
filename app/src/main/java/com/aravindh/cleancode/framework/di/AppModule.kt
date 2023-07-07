package com.aravindh.cleancode.framework.di

import android.content.Context
import androidx.room.Room
import com.aravindh.cleancode.framework.RoomNoteDataSource
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.cleancode.framework.db.NoteDao
import com.aravindh.cleancode.framework.db.NoteDatabase
import com.aravindh.cleancode.util.Constants.DATABASE_NAME
import com.aravindh.core.repository.NoteRepository
import com.aravindh.core.usecase.AddNoteUseCase
import com.aravindh.core.usecase.DeleteNoteUseCase
import com.aravindh.core.usecase.GetNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideNoteRepository(noteDao: NoteDao) =
        NoteRepository(RoomNoteDataSource(noteDao))

    @Singleton
    @Provides
    fun provideUseCase(noteRepository: NoteRepository) = UseCases(
        addNoteUseCase = AddNoteUseCase(noteRepository),
        getNoteUseCase = GetNoteUseCase(noteRepository),
        deleteNoteUseCase = DeleteNoteUseCase(noteRepository)
    )

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(context, NoteDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideCoroutineScope() = CoroutineScope(Dispatchers.IO)
}