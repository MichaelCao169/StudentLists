package com.example.test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var editTextSearch: TextInputEditText
    private lateinit var recyclerView: RecyclerView
    private val studentAdapter = StudentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        editTextSearch = findViewById(R.id.editTextSearch)
        recyclerView = findViewById(R.id.recyclerView)

        setupRecyclerView()
        setupSearchView()
        loadSampleData()
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }
    }

    private fun setupSearchView() {
        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                studentAdapter.filterStudents(s?.toString() ?: "")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun loadSampleData() {
        // Function to generate random 4-digit number as string with leading zeros
        fun generateRandomId(): String {
            return String.format("%04d", (1000..9999).random())
        }

        val sampleStudents = listOf(
            Student("Alexander Thompson", "2021${generateRandomId()}"),
            Student("Bella Williams", "2021${generateRandomId()}"),
            Student("Christopher Davis", "2021${generateRandomId()}"),
            Student("Diana Wilson", "2021${generateRandomId()}"),
            Student("Ethan Brown", "2021${generateRandomId()}"),
            Student("Fiona Mitchell", "2021${generateRandomId()}"),
            Student("George Anderson", "2021${generateRandomId()}"),
            Student("Hannah Martin", "2021${generateRandomId()}"),
            Student("Isaac Roberts", "2021${generateRandomId()}"),
            Student("Jennifer Taylor", "2021${generateRandomId()}"),
            Student("Kevin Johnson", "2021${generateRandomId()}"),
            Student("Laura Smith", "2021${generateRandomId()}"),
            Student("Michael Cao", "20215537"),  // Specific required student
            Student("Natalie White", "2021${generateRandomId()}"),
            Student("Oliver Harris", "2021${generateRandomId()}"),
            Student("Penelope Green", "2021${generateRandomId()}"),
            Student("Quinn Foster", "2021${generateRandomId()}"),
            Student("Rachel Brooks", "2021${generateRandomId()}"),
            Student("Samuel Cooper", "2021${generateRandomId()}"),
            Student("Tessa Morgan", "2021${generateRandomId()}")
        ).shuffled()  // Shuffle the list to randomize Michael Cao's position

        studentAdapter.setStudents(sampleStudents)
    }
}