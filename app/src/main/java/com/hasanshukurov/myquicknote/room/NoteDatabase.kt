package com.hasanshukurov.myquicknote.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hasanshukurov.myquicknote.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}