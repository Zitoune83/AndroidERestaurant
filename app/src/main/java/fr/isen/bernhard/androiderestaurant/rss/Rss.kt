package fr.isen.bernhard.androiderestaurant.rss

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import fr.isen.bernhard.androiderestaurant.model.DisheDeserialized
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

fun readFromFile(context: Context): Int {
    var current: DisheDeserialized = DisheDeserialized()
    var quantityTotal=0
    try {
        val inputStream: InputStream? = context.openFileInput(FILESHOPPING)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = java.lang.StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {

                //On pourrait afficher chaque ligne
                current = receiveString?.let { lineToDisheDeserialized(it) }!!
                quantityTotal += current.quantity

                stringBuilder.append(receiveString)
            }
            inputStream.close()
        }
    } catch (e: FileNotFoundException) {
        Log.e("login activity", "File not found: $e")
    } catch (e: IOException) {
        Log.e("login activity", "Can not read file: $e")
    }
    return quantityTotal
}


fun readNumberOfLine(context: Context): Int {
    var number=0
    try {
        val inputStream: InputStream? = context.openFileInput(FILESHOPPING)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = java.lang.StringBuilder()
            while (bufferedReader.readLine().also { receiveString = it } != null) {

           number++
            }
            inputStream.close()
        }
    } catch (e: FileNotFoundException) {
        Log.e("login activity", "File not found: $e")
    } catch (e: IOException) {
        Log.e("login activity", "Can not read file: $e")
    }
    return number
}


fun readLineNumber(context: Context, num:Int): String? {

    var i=0
    var string: String? = null

    try {
        val inputStream: InputStream? = context.openFileInput(FILESHOPPING)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = java.lang.StringBuilder()
            while (i != num && bufferedReader.readLine().also { receiveString = it } != null) {

                i++
            }

             string = receiveString?.let { it }
            inputStream.close()


        }

    } catch (e: FileNotFoundException) {
        Log.e("login activity", "File not found: $e")
    } catch (e: IOException) {
        Log.e("login activity", "Can not read file: $e")
    }
    return string
}


fun writeLineNumber(context: Context, num:Int): String? {

    var i=0
    var string: String? = null

    try {
        val inputStream: InputStream? = context.openFileInput(FILESHOPPING)
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var receiveString: String? = ""
            val stringBuilder = java.lang.StringBuilder()
            while (i != num && bufferedReader.readLine().also { receiveString = it } != null) {

                i++
            }
            string = receiveString?.let { it }
            inputStream.close()


        }

    } catch (e: FileNotFoundException) {
        Log.e("login activity", "File not found: $e")
    } catch (e: IOException) {
        Log.e("login activity", "Can not read file: $e")
    }
    return string
}














fun lineToDisheDeserialized(line:String): DisheDeserialized{

    val list = line.split(";")
    var current: DisheDeserialized = DisheDeserialized()

    for (i in list) {
        if (i.contains("NAME=")) {
            current.nameFr = i.substringAfter("=")

        }
        if (i.contains("PRICE=")) {
            current.prices = i.substringAfter("=").toDouble()
        }

        if (i.contains("QUANTITY=")) {
            current.quantity = i.substringAfter("=").toInt()
        }
    }
    return current
}




fun generateLine(dish: DisheDeserialized, num:Int):String{

    var name:String? = dish.nameFr
    var price:String = dish.prices.toString()
    var quantity:String? = num.toString()

    return "NAME=$name;PRICE=$price;QUANTITY=$quantity\n"
}





//Ne marche pas
fun reWriteLine(lineToReplace: String?, newLine:String?) {

    var file = File(FILESHOPPING)
    var foundLine = false
    val tempFile = File.createTempFile("tempfile", null)

    BufferedReader(FileReader(file)).use { reader ->
        BufferedWriter(FileWriter(tempFile)).use { writer ->
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                if (line == lineToReplace) {
                    writer.write(newLine + "\n")
                    foundLine = true
                } else {
                    writer.write(line + "\n")
                }
            }
        }
    }

    if (foundLine) {
        tempFile.renameTo(file)
        println("Line replaced successfully.")
    } else {
        println("Line not found.")
    }
}