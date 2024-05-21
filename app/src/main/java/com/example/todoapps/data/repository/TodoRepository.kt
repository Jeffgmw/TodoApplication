package com.example.todoapps.data.repository

import androidx.lifecycle.LiveData
import com.example.todoapps.data.db.TodoDao
import com.example.todoapps.data.model.Todo


class TodoRepository(val dao: TodoDao) {

    private val _todos = dao.getTodos()
    val todos : LiveData<List<Todo>>
        get() = _todos

    suspend fun addTodo(todo: Todo) = dao.insertTodo(todo)
    suspend fun deleteTodo(todo: Todo) = dao.deleteTodo(todo)

}