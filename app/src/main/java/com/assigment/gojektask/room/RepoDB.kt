package com.assigment.gojektask.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RepoDBModel::class], version = 1, exportSchema = false)
abstract class RepoDB : RoomDatabase() {
    abstract val repositoryDOA: RepositoryDOA
}