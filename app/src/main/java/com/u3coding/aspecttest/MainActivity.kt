package com.u3coding.aspecttest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.u3coding.aspecttest.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(){
    private lateinit var viewModel: MyViewModel
    private val TAG  = "App Aspectj"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val mainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        tv_id1.setOnClickListener { viewModel.click() }
        mainBinding.viewModel = viewModel
        mainBinding.lifecycleOwner = this
    }
}