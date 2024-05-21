package com.example.todoapps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.todoapps.data.model.Todo
import com.example.todoapps.data.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewmodel(private val repository: TodoRepository) : ViewModel() {

    val todos : LiveData<List<Todo>>
    get() = repository.todos

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            repository.addTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }



}
class ViewmodelFactory(private val repository: TodoRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodoViewmodel(repository) as T
    }
}