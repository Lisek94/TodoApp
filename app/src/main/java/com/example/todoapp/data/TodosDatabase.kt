package com.example.todoapp.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodosDatabase : RoomDatabase() {

    abstract fun todoDao() : TodosDao

    companion object{
        private var instance: TodosDatabase? = null

        fun getInstance(application: Application): TodosDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    TodosDatabase::class.java,
                    "todo_table")
                    .build()
            }
            return instance
        }

    }


}