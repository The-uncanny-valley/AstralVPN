package com.uncannyvalley.core.vpn.engine

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class XrayBinaryManager(private val context: Context) {

    suspend fun prepareBinary(): File = withContext(Dispatchers.IO) {
        val targetFile = File(context.filesDir, "xray")

        if (!targetFile.exists()) {
            context.assets.open("bin/xray").use { input ->
                targetFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            targetFile.setExecutable(true, true)
        }

        targetFile
    }
}