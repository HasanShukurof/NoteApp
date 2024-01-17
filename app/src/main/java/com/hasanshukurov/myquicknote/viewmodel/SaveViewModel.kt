package com.hasanshukurov.myquicknote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasanshukurov.myquicknote.model.NoteModel
import com.hasanshukurov.myquicknote.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(val repo: NoteRepository) : ViewModel() {


    fun saveNoteVM(title: String, note: String) {
        viewModelScope.launch {
            repo.saveNoteRP(title, note)
        }
    }

}