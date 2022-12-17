package com.ali.roomrevision

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Post::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class PostsDB : RoomDatabase() {
    abstract fun postsDao(): PostsDAO

    companion object {
        @Volatile
        private var INSTANCE: PostsDB? = null
        fun getDatabase(context: Context): PostsDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): PostsDB {
            return Room.databaseBuilder(
                context.applicationContext,
                PostsDB::class.java,
                "posts_DB"
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}