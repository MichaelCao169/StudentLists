package com.example.test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {
    private var studentList = listOf<Student>()
    private var filteredList = listOf<Student>()

    inner class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewStudentId: TextView = itemView.findViewById(R.id.textViewStudentId)

        fun bind(student: Student) {
            textViewName.text = student.name
            textViewStudentId.text = student.studentId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    override fun getItemCount() = filteredList.size

    fun setStudents(students: List<Student>) {
        studentList = students
        filteredList = students
        notifyDataSetChanged()
    }

    fun filterStudents(query: String) {
        filteredList = if (query.length <= 2) {
            studentList
        } else {
            studentList.filter { student ->
                student.name.contains(query, ignoreCase = true) ||
                        student.studentId.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}