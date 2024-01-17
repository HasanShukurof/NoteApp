package com.hasanshukurov.myquicknote.repo

import com.hasanshukurov.myquicknote.model.NoteModel
import com.hasanshukurov.myquicknote.room.NoteDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NoteRepository @Inject constructor(val noteDao: NoteDao) {

    suspend fun getAllNotesRP(): List<NoteModel> {
        return noteDao.getAllNotes()
    }

    suspend fun saveNoteRP(title: String, note: String) {
        val newNote = NoteModel(title, note)
        noteDao.saveNote(newNote)
    }

    suspend fun replaceNoteRP(title: String, note: String, id: Int) {
        val replaceNote = NoteModel(title, note, id)
        noteDao.replaceNote(replaceNote)

        getAllNotesRP()
    }

    suspend fun deleteNoteRP(id: Int) {
        val deleteNote = NoteModel("", "", id)
        noteDao.deleteNote(deleteNote)

        getAllNotesRP()
    }

    suspend fun searchNoteRP(searchText: String): List<NoteModel> {
        return noteDao.searchNote(searchText)
    }

}