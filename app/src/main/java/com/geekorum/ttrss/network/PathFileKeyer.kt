package com.geekorum.ttrss.network

import coil.key.Keyer
import coil.request.Options
import java.io.File

/**
 * A [Keyer] that keys [File]s by their path only.
 *
 * This avoids checking the file's modification time on the main thread, which triggers
 * StrictMode violations.
 */
class PathFileKeyer(private val addLastModifiedToFileCacheKey: Boolean) : Keyer<File> {

    override fun key(data: File, options: Options): String {
        if (addLastModifiedToFileCacheKey) {
            return "${data.path}:${data.lastModified()}"
        }
        return data.path
    }
}

