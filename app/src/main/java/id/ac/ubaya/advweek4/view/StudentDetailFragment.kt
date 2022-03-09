package id.ac.ubaya.advweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.model.Student
import id.ac.ubaya.advweek4.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    val studentList = arrayListOf<Student>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    fun update(studentDetail: Student){
        val student = studentDetail
        editID.setText(student.id)
        editName.setText(student.name)
        editPhone.setText(student.phone)
        editBirth.setText(student.dob)
    }

    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner, Observer {
            update(it)
        })

    }

}