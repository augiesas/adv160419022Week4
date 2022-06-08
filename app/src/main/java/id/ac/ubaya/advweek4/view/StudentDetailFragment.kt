package id.ac.ubaya.advweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.advweek4.R
import id.ac.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import id.ac.ubaya.advweek4.model.Student
import id.ac.ubaya.advweek4.util.loadImage
import id.ac.ubaya.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(), ButtonEditClickListener, ButtonNotifClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
    val studentList = arrayListOf<Student>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding.listener = this
        dataBinding.listenerNotif = this
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        val student_id = StudentDetailFragmentArgs.fromBundle(requireArguments()).idStudent

        viewModel.fetch(student_id)

        observeViewModel()
    }

    fun update(studentDetail: Student){
        dataBinding.student = studentDetail
//        val student = studentDetail
//        Log.d("ajax", student.toString())
//        editID.setText(student.id)
//        editName.setText(student.name)
//        editPhone.setText(student.phone)
//        editBirth.setText(student.dob)
//        imageStudent.loadImage(student.photoURL, progressDetailPhoto)
//        buttonNotif.setOnClickListener{
//            Observable.timer(5, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe{
//                    Log.d("mynotif", "Notification delayed after 5 seconds")
//                    MainActivity.showNotification(student.name.toString(), "Notification created", R.drawable.ic_baseline_person_24)
//                }
//        }
    }

    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner, Observer {
            update(it)
        })

//        viewModel.studentsLiveData.observe(viewLifecycleOwner, Observer {
//            update(it)
//        })

    }

    override fun onButtonEditClick(v: View, students: Student) {
        Log.d("Update", "Run Update Here")
    }

    override fun onButtonNotifClick(v: View, students: Student) {
        Log.d("Notif", "Run Notif Here")
        Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    Log.d("mynotif", "Notification delayed after 5 seconds")
                    MainActivity.showNotification(students.name.toString(), "Notification created", R.drawable.ic_baseline_person_24)
                }
    }


}