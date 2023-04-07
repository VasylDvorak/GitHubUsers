package com.popularlibraries.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUser(

    @PrimaryKey var id: String,
    var login: String,
    var avatar_url: String? = null,
    var repos_url: String? = null
)