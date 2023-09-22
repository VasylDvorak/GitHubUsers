package com.popularlibraries.domain.cache.room

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import com.popularlibraries.entity.GithubUser
import java.io.*
import java.net.HttpURLConnection
import java.net.URL


class SaveURLtoFile {
    var bitmap: Bitmap? = null
    var outputStream: OutputStream? = null

    @SuppressLint("SuspiciousIndentation")
    fun saveInFile(user: GithubUser): String {
        while (user.avatar_url == null) {
        }
        bitmap = getBitmapFromURL(user.avatar_url)
        return saveImage(bitmap, user.login)
    }

    private fun saveImage(bitmap: Bitmap?, login: String): String {


        val dir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_PICTURES + "/GitHubUsers"
        )

        val file = File(dir, "avatar_of_" + login + ".jpg")

        try {
            if (!dir.exists()) {
                dir.mkdir()
            }
            outputStream = FileOutputStream(file)

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }

        try {
            outputStream?.let { bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it) }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }


        try {
            outputStream!!.flush()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        try {
            outputStream!!.close()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return file.path
    }

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}