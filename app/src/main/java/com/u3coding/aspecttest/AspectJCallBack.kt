package com.u3coding.aspecttest

interface AspectJCallBack {
    fun onClick(className: String?, id: String?)
    fun onActivityOpen(className: String?)
    fun onActivityClose(className: String?)
    fun onFragmentOpen(className: String?)
    fun onFragmentClose(className: String?)
}
