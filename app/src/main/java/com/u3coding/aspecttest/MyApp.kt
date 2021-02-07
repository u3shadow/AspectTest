package com.u3coding.aspecttest

import android.app.Application
import com.u3coding.log.ZLog

class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        ZLog.Init(String.format("%s/log/", cacheDir))
    }

}