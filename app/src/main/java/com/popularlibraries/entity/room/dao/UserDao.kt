package com.popularlibraries.entity.room.dao

import androidx.room.*
import com.popularlibraries.entity.room.RoomGithubUser

/**
 *  Работает только с OnConflictStrategy.IGNORE. Если поставить флаг REPLACE,
 *  то при каждом обновлении с сети списков
 * пользователей в классее RoomGithubRepository,
 * onDelete = ForeignKey.CASCADE каждый раз удаляет таблицу репозиториев пользователей
 * или же надо убрать onDelete = ForeignKey.CASCADE
 */

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg users: RoomGithubUser)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(users: List<RoomGithubUser>)

    @Update
    fun update(user: RoomGithubUser)

    @Update
    fun update(vararg users: RoomGithubUser)

    @Update
    fun update(users: List<RoomGithubUser>)

    @Delete
    fun delete(user: RoomGithubUser)

    @Delete
    fun delete(vararg users: RoomGithubUser)

    @Delete
    fun delete(users: List<RoomGithubUser>)

    @Query("SELECT * FROM RoomGithubUser")
    fun getAll(): List<RoomGithubUser>

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUser


}