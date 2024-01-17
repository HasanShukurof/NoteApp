package com.hasanshukurov.myquicknote.room

import androidx.room.*
import com.hasanshukurov.myquicknote.model.NoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteTable")
    suspend fun getAllNotes(): List<NoteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: NoteModel)

    @Update
    suspend fun replaceNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Query("SELECT * FROM noteTable WHERE title like '%' || :searchText || '%' ")
    suspend fun searchNote(searchText: String): List<NoteModel>

}