package com.assigment.gojektask.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositoryTable")
data class RepoDBModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "html_url")
    val html_url: String,
    @ColumnInfo(name = "avatar_url")
    val avatar_url: String,
    @ColumnInfo(name = "language")
    val language: String?,
    @ColumnInfo(name = "forks_count")
    val forks_count: Int,
    @ColumnInfo(name = "stargazers_count")
    val stargazers_count: Int
)