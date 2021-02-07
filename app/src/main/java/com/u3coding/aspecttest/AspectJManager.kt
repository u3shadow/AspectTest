package com.u3coding.aspecttest

import android.util.Log

object AspectJManager {

    private val TAG = "ASPECT"
    private var callBack: AspectJCallBack? =
        //todo 使用日志记录框架
   object :AspectJCallBack{
        override fun onClick(className: String?, id: String?) {
            Log.e(TAG, "$className:$id")
        }
        override fun onActivityOpen(className: String?) {
            Log.e(TAG, "$className")
        }

        override fun onActivityClose(className: String?) {
            Log.e(TAG, "$className")
        }

        override fun onFragmentOpen(className: String?) {
            Log.e(TAG, "$className")
        }

        override fun onFragmentClose(className: String?) {
            Log.e(TAG, "$className")
        }

    }
    fun init(trackPointCallBack: AspectJCallBack?) {
        callBack = trackPointCallBack
    }

    fun onClick(className: String?, id: String?) {
        if (callBack != null) {
            callBack!!.onClick(className, id)
        }
    }

    fun onActivityOpen(className: String?) {
        if (callBack != null) {
            callBack!!.onActivityOpen(className)
        }
    }

    fun onActivityClose(className: String?) {
        if (callBack != null) {
            callBack!!.onActivityClose(className)
        }
    }

    fun onFragmentOpen(className: String?) {
        if (callBack != null) {
            callBack!!.onFragmentOpen(className)
        }
    }

    fun onFragmentClose(className: String?) {
        if (callBack != null) {
            callBack!!.onFragmentClose(className)
        }
    }
}
