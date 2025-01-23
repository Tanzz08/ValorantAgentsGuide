package com.example.valorantagentsguide.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valorantagentsguide.data.local.entity.AgentsEntity

@Database(entities = [AgentsEntity::class], version = 1, exportSchema = false)
abstract class AgentsDatabase : RoomDatabase() {
    abstract fun agentsDao(): AgentsDao


    companion object {
        @Volatile
        private var instance: AgentsDatabase? = null
        fun getInstance(context: Context): AgentsDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AgentsDatabase::class.java, "Agents.db"
                ).build()
            }
    }
}