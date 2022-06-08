package id.ac.ubaya.advweek4.view

import android.view.View
import id.ac.ubaya.advweek4.model.Student

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v:View)
}

interface ButtonEditClickListener{
    fun onButtonEditClick(v:View, students:Student)
}

interface ButtonNotifClickListener{
    fun onButtonNotifClick(v:View, students:Student)
}