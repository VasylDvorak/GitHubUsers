package com.popularlibraries.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubRepository(
    @Expose var id: String,
    @Expose var name: String? = null,
    @Expose var forksCount: Int? = null
) : Parcelable