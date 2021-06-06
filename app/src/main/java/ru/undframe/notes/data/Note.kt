package ru.undframe.notes.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Note(@PrimaryKey val id:String, val name: String, val text: String, val timeCreate: Long) {

    companion object {

        fun createNot(text: String): Note {
            return Note(UUID.randomUUID().toString(),text.substring(0, 22.coerceAtMost(text.length)).plus("..."), text, System.currentTimeMillis())
        }

        fun createNot(name: String, text: String): Note {
            if (name.isEmpty())
                return createNot(text)
            return Note(UUID.randomUUID().toString(),name, text, System.currentTimeMillis())
        }

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}