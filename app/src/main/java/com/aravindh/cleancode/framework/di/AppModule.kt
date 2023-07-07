package com.aravindh.cleancode.framework.di

import android.app.Application
import com.aravindh.cleancode.framework.RoomNoteDataSource
import com.aravindh.cleancode.framework.UseCases
import com.aravindh.core.repository.NoteRepository
import com.aravindh.core.usecase.AddNoteUseCase
import com.aravindh.core.usecase.DeleteNoteUseCase
import com.aravindh.core.usecase.GetNoteUseCase
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun provideApplication() = application

    @Provides
    fun provideNoteRepository(application: Application) =
        NoteRepository(RoomNoteDataSource(application))

    @Provides
    fun provideUseCase(noteRepository: NoteRepository) = UseCases(
        addNoteUseCase = AddNoteUseCase(noteRepository),
        getNoteUseCase = GetNoteUseCase(noteRepository),
        deleteNoteUseCase = DeleteNoteUseCase(noteRepository)
    )
}