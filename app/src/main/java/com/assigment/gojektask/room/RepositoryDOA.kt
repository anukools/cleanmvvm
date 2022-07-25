package com.assigment.gojektask.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDOA {
    @Query("select * from repositoryTable")
    fun getAllRepository(): List<RepoDBModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(order: List<RepoDBModel>)

    @Query("DELETE FROM repositoryTable")
    fun deleteAll()
}