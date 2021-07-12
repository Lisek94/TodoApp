package com.example.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(private val listOfTodo: List<Todo>) : RecyclerView.Adapter<TodoAdapter.MyViewHolder>(){

    inner class MyViewHolder(binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.itemNameTodoTextView
        val date = binding.itemDeadlineTodoTextView
        val category = binding.itemCategoryTodoTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = listOfTodo[position].name
        holder.date.text = listOfTodo[position].date
        holder.category.text = listOfTodo[position].category
    }

    override fun getItemCount(): Int {
        return listOfTodo.size
    }
}