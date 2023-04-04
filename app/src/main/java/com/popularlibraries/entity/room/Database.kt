package com.popularlibraries.entity.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.popularlibraries.entity.room.dao.PictureDao
import com.popularlibraries.entity.room.dao.RepositoryDao
import com.popularlibraries.entity.room.dao.UserDao

@androidx.room.Database(
    entities = [RoomGithubUser::class,
        RoomGithubRepository::class, RoomGithubPicture::class], version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
    abstract val pictureDao: PictureDao

    companion object {
        const val DB_NAME = "database.db"

    }


}
