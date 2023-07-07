package com.aravindh.cleancode.framework.di

import com.aravindh.cleancode.framework.viewmodel.AddNoteViewModel
import com.aravindh.cleancode.framework.viewmodel.NoteListViewModel
import dagger.Component


@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(noteListViewModel: NoteListViewModel)

    fun inject(addNoteViewModel: AddNoteViewModel)
}