package com.u3coding.aspecttest
import com.u3coding.log.ZLog

object AspectJManager {

    private val TAG = "ASPECT"
    private var callBack: AspectJCallBack? =
   object :AspectJCallBack{
        override fun onClick(className: String?, id: String?) {
            ZLog.e(TAG, "$className:$id")
        }
        override fun onActivityOpen(className: String?) {
            ZLog.e(TAG, "$className")
        }

        override fun onActivityClose(className: String?) {
            ZLog.e(TAG, "$className")
        }

        override fun onFragmentOpen(className: String?) {
            ZLog.e(TAG, "$className")
        }

        override fun onFragmentClose(className: String?) {
            ZLog.e(TAG, "$className")
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
