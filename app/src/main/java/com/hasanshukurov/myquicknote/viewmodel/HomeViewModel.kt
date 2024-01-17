package com.hasanshukurov.myquicknote.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hasanshukurov.myquicknote.model.NoteModel
import com.hasanshukurov.myquicknote.repo.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val noteRepo: NoteRepository) : ViewModel() {

    var noteList = MutableLiveData<List<NoteModel>>()


    fun getAllNoteVM() {
        viewModelScope.launch {
            noteList.value = noteRepo.getAllNotesRP()
        }
    }

    fun deleteNoteVM(id: Int) {
        viewModelScope.launch {
            noteRepo.deleteNoteRP(id)

            getAllNoteVM()
        }
    }

    fun searchNoteVM(searchText: String) {
        viewModelScope.launch {
            noteList.value = noteRepo.searchNoteRP(searchText)
        }
    }

}