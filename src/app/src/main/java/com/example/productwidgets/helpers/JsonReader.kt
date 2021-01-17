package com.example.productwidgets.helpers

object JsonReader {
    fun readFromFile(fileName:String): String? {
        val inputStream = App.context.assets.open(fileName)
        val stringBuffer = StringBuffer()
        inputStream.bufferedReader().forEachLine { stringBuffer.append(it) }
        val result = stringBuffer.toString()
        inputStream.close()
        return result
    }
}