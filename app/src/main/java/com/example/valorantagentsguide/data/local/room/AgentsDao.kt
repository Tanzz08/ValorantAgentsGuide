package com.example.valorantagentsguide.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.valorantagentsguide.data.local.entity.AgentsEntity

@Dao
interface AgentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertsAgents(agents: List<AgentsEntity>)

    @Query("SELECT * FROM agents")
    suspend fun getAgents(): List<AgentsEntity>
}