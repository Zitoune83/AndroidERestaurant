package fr.isen.bernhard.androiderestaurant.rss

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.*

var FILESHOPPING = "config.txt"

fun writeToFile(data: String, context: Context) {
    try {
        val outputStreamWriter =
            OutputStreamWriter(context.openFileOutput(FILESHOPPING, AppCompatActivity.MODE_APPEND))
        outputStreamWriter.write(data)
        outputStreamWriter.close()
    } catch (e: IOException) {
        Log.e("Exception", "File write failed: $e")
    }
}

fun readFromFile(context: Context): String? {
    var ret = ""
    try {
        val inputStream: InputStream? = context.openFileInput(FILESHOPPING)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = java.lang.StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {
                stringBuilder.append(receiveString)
            }
            inputStream.close()
            ret = stringBuilder.toString()
        }
    } catch (e: FileNotFoundException) {
        Log.e("login activity", "File not found: $e")
    } catch (e: IOException) {
        Log.e("login activity", "Can not read file: $e")
    }
    return ret
}