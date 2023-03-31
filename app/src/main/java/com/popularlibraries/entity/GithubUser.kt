package com.popularlibraries.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser (
   @Expose var id: String,
   @Expose var login: String,
   @Expose var avatar_url: String? = null,
   @Expose var repos_url: String? =null
) : Parcelable
