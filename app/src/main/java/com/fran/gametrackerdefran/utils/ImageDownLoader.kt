package com.fran.gametrackerdefran.utils

import android.content.Context
import java.io.File
import java.net.URL

object ImageDownloader {

    fun downloadImage(
        context: Context,
        imageUrl: String,
        fileName: String
    ): String? {

        return try {

            val inputStream = URL(imageUrl).openStream()

            val file = File(
                context.filesDir,
                "$fileName.jpg"
            )

            file.outputStream().use { output ->
                inputStream.copyTo(output)
            }

            file.absolutePath

        } catch (e: Exception) {

            e.printStackTrace()
            null

        }

    }

}