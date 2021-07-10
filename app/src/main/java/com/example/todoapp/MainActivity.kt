package com.example.todoapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAlertForEmptyListTodos()
        val intent = Intent(this,TodoDetailsActivity::class.java)

        binding.addTodoButton.setOnClickListener {
            startActivity(intent)
        }

    }

    private fun setupAlertForEmptyListTodos() {
        val todoListIsEmpty = true //viewmodel.checkData()

        if (todoListIsEmpty){
            val alertDialog = AlertDialog.Builder(this)

            alertDialog.setTitle("Informacja")
            alertDialog.setMessage("Lista zadań jest pusta, kliknij + aby dodać nowe zadanie")
                .setNegativeButton("Zamknij podpowiedź") {dialog, _ ->
                    dialog.cancel()
                }
            alertDialog.show()
        }
    }

}