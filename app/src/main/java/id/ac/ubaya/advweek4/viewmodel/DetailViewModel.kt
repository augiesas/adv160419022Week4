package id.ac.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import id.ac.ubaya.advweek4.model.Student

class DetailViewModel(application: Application) : AndroidViewModel(application){

    val studentLiveData = MutableLiveData<Student>()
//    val studentsLiveData = MutableLiveData<ArrayList<Student>>()
    val studentsLoadErrorLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue:RequestQueue?=null

    fun fetch(studentId: String){
//        studentLiveData.value = Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")

        queue = Volley.newRequestQueue(getApplication())
        val url = "http://adv.jitusolution.com/student.php?id=$studentId"
        Log.d("url",url)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
//                val sType = object : TypeToken<ArrayList<Student>>(){}.type
                val result = Gson().fromJson<Student>(it, Student::class.java)
                studentLiveData.value = result

                loadingLiveData.value = false
                Log.d("showvolley",it)
            },
            {
                studentsLoadErrorLiveData.value = true
                loadingLiveData.value = false
                Log.d("errorvolley",it.toString())

            }
        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }
}