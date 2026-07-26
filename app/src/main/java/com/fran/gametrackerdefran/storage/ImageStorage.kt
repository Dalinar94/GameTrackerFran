package com.fran.gametrackerdefran.storage

import android.content.Context
import android.net.Uri
import java.io.File
import java.util.UUID

fun copyImageToInternalStorage(
    context: Context,
    uri: Uri
): String {

    val coversDir = File(context.filesDir, "covers")

    if (!coversDir.exists()) {
        coversDir.mkdirs()
    }

    val extension = context.contentResolver
        .getType(uri)
        ?.substringAfter("/")

    val fileName = "${UUID.randomUUID()}.$extension"

    val destination = File(coversDir, fileName)

    context.contentResolver.openInputStream(uri)?.use { input ->

        destination.outputStream().use { output ->
            input.copyTo(output)
        }

    }

    return destination.absolutePath

}