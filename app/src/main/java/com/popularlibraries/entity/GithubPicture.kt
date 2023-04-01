package com.popularlibraries.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
class GithubPicture(
    @Expose var id: String,
    @Expose var avatar_url: String,
    @Expose var local_path: String
) : Parcelable