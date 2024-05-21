package com.example.todoapps.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapps.data.model.Todo
import com.example.todoapps.databinding.TodoItemBinding
import java.text.SimpleDateFormat
import java.util.*


class TodoAdapter(private val onDeleteListener: OnDeleteListener) :
    ListAdapter<Todo, TodoAdapter.ViewHolder>(COMPARATOR) {

    class ViewHolder(val binding: TodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentTodo = getItem(position)
        holder.binding.apply {
            todoDesc.text = currentTodo.desc
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            todoDeadline.text = dateFormat.format(currentTodo.date.time)
            deleteIcon.setOnClickListener {
                onDeleteListener.onDelete(currentTodo)
            }
        }
    }

    interface OnDeleteListener {
        fun onDelete(todo: Todo)
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
