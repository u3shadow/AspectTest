package com.u3coding.aspecttest

import android.util.Log
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    fun click(){
        Log.e("vm","click")
    }
    fun click1(){
        Log.e("vm","click2")
    }
}