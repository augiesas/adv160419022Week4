package id.ac.ubaya.advweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.model.Student
import id.ac.ubaya.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>) : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(){
    class StudentViewHolder(var view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        with(holder.view){

            textID.text = student.id
            textName.text = student.name
            buttonDetail.setOnClickListener{
                val id = student.id
                Log.d("test",id.toString())
                val action = id?.let { it1 -> StudentListFragmentDirections.actionStudentDetail(it1) }
                if (action != null) {
                    Navigation.findNavController(it).navigate(action)
                }
            }
            imageStudentPhoto.loadImage(student.photoURL, progressLoadingStudentPhoto)
        }


    }

    override fun getItemCount() = studentList.size

    fun updateStudentList(newStudentList: ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}