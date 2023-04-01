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
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance ?: throw RuntimeException("База данных не создана")
        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!, Database::class.java,
                    DB_NAME
                )
                    .build()
            }
        }
    }


}
