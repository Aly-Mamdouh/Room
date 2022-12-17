package com.ali.roomrevision

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val user: User,
    var title: String,
    var body: String
)
