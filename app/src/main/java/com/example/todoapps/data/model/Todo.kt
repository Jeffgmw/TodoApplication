package com.example.todoapps.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.todoapps.util.day
import com.example.todoapps.util.month
import java.util.*

@Entity(tableName = "todo", indices = [Index(value = ["desc", "date"], unique = true)])
data class Todo(
    val desc: String,
    var date: Calendar,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString(): String {
        return "$desc\n${date.day()} ${date.get(Calendar.DATE)} ${date.month()}"
    }
}