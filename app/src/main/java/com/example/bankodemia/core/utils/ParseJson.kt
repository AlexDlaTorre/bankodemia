package com.example.bankodemia.core.utils

import android.util.JsonReader
import com.example.bankodemia.data.model.Lada
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
//TODO - Cambiar nombre y hacer generica esta clase
class ParseJson {
    @Throws(IOException::class)
    fun readJsonStream(`in`: InputStream?): List<Lada> {
        val reader = JsonReader(InputStreamReader(`in`, "UTF-8"))
        return try {
            readLadaArray(reader)
        } finally {
            reader.close()
        }
    }

    @Throws(IOException::class)
    fun readLadaArray(reader: JsonReader): List<Lada> {
        val ladas: MutableList<Lada> = ArrayList()
        reader.beginArray()
        while (reader.hasNext()) {
            ladas.add(readReceta(reader))
        }
        reader.endArray()
        return ladas
    }

    @Throws(IOException::class)
    fun readReceta(reader: JsonReader): Lada {
        var nombre: String? = null
        var lada: String? = null
        reader.beginObject()
        while (reader.hasNext()) {
            val name: String = reader.nextName()
            when (name) {
                "name" -> {
                    nombre = reader.nextString()
                }
                "lada" -> {
                    lada = reader.nextString()
                }
                else -> {
                    reader.skipValue()
                }
            }
        }
        reader.endObject()
        return Lada(name = nombre , lada = lada)
    }
}