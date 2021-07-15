package com.example.todoapp.activities

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.adapters.TodoAdapter
import com.example.todoapp.data.Todo
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.viewmodels.TodoViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listOfTodos: LiveData<List<Todo>>
    private val viewModel: TodoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerViewTodos
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        listOfTodos = viewModel.getAllTodos()

        setupAlertForEmptyListTodos()

        val intent = Intent(this, TodoDetailsActivity::class.java)

        binding.addTodoButton.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun setupAlertForEmptyListTodos() {
        listOfTodos.observe(this, {
            if (it.isNotEmpty()) {
                todoAdapter = TodoAdapter((it))
                recyclerView.adapter = todoAdapter
            } else {
                val alertDialog = AlertDialog.Builder(this)
                alertDialog.setTitle(getString(R.string.alert_dialog_title))
                alertDialog.setMessage(getString(R.string.alert_dialog_empty_list_message))
                    .setNegativeButton(getString(R.string.alert_dialog_empty_list_neggative_button)) { dialog, _ ->
                        dialog.cancel()
                    }
                alertDialog.show()
            }
        })
    }
}