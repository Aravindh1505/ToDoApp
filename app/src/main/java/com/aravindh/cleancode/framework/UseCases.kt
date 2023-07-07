package com.aravindh.cleancode.framework

import com.aravindh.core.usecase.AddNoteUseCase
import com.aravindh.core.usecase.DeleteNoteUseCase
import com.aravindh.core.usecase.GetNoteUseCase

data class UseCases(
    val addNoteUseCase: AddNoteUseCase,
    val getNoteUseCase: GetNoteUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase
)
