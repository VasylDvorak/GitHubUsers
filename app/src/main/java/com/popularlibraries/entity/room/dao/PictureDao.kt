package com.popularlibraries.entity.room.dao

import androidx.room.*
import com.popularlibraries.entity.room.RoomGithubPicture


@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubPicture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubPicture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubPicture>)

    @Update
    fun update(user: RoomGithubPicture)

    @Update
    fun update(vararg users: RoomGithubPicture)

    @Update
    fun update(users: List<RoomGithubPicture>)

    @Delete
    fun delete(user: RoomGithubPicture)

    @Delete
    fun delete(vararg users: RoomGithubPicture)

    @Delete
    fun delete(users: List<RoomGithubPicture>)

    @Query("DELETE FROM RoomGithubPicture")
    fun deleteAll()

    @Query("SELECT * FROM RoomGithubPicture")
    fun getAll(): List<RoomGithubPicture>

    @Query("SELECT * FROM RoomGithubPicture WHERE id = :id")
    fun findForUser(id: String): RoomGithubPicture


}