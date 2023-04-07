package com.popularlibraries.entity.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomGithubUser::class,
        parentColumns = ["id"],
        childColumns = ["id"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class RoomGithubPicture(
    @PrimaryKey var id: String,
    var avatar_url: String,
    var local_path: String
)
