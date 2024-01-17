package com.hasanshukurov.myquicknote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasanshukurov.myquicknote.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReplaceViewModel @Inject constructor(val repo: NoteRepository) : ViewModel() {

    fun replaceNoteVM(id: Int, title: String, note: String) {
        viewModelScope.launch {
            repo.replaceNoteRP(title, note, id)
        }
    }

}