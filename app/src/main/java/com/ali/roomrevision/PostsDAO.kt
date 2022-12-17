package com.ali.roomrevision

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PostsDAO {

    @Insert
    fun insertPost(post: Post): Completable

    @Query("select * from posts_table")
    fun getPosts(): Single<List<Post>>
}