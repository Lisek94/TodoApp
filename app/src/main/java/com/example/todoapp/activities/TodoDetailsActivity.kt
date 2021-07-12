package com.example.todoapp.activities

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.example.todoapp.databinding.ActivityTodoDetailsBinding
import com.example.todoapp.viewmodels.TodoViewModel
import java.text.SimpleDateFormat
import java.util.*

class TodoDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTodoDetailsBinding
    private val viewModel: TodoViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameTodoEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onNameTodoChanged(text?.toString() ?:"")
        }

        binding.calendarImageButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                this, { _, year, month, day ->
                    val date = Date(year-1900, month, day, 0, 0, 0)
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val formattedDate  = dateFormat.format(date)

                    binding.deadlineTodoTextView.text = formattedDate
                    viewModel.onDateTodoChanged(formattedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.categoryRadioGroup.setOnCheckedChangeListener {radioGroup,_ ->
            val checkedRadioButton = radioGroup.checkedRadioButtonId
            val category = findViewById<RadioButton>(checkedRadioButton)
            viewModel.onCategoryTodoChanged(category.text.toString())
        }

        binding.cancelButton.setOnClickListener {
            finish()
        }

        binding.saveTodoButton.setOnClickListener {
            val status = viewModel.onSaveButtonClicked()
            showAlertDialog(status)
        }
    }

    private fun showAlertDialog(status: Boolean) {
        if (status) {
            Toast.makeText(applicationContext,"Zadanie zapisane poprawnie", Toast.LENGTH_LONG).show()
            finish()
        } else {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("Informacja")
            alertDialog.setMessage("Nazwa zadania nie może być pusta")
                .setPositiveButton("Sprobuj jeszcze raz") {dialog,_ ->
                    dialog.cancel()
                }
                .setNegativeButton("Anuluj dodawanie zadania") {_,_ ->
                    finish()
                }
            alertDialog.show()
        }
    }
}