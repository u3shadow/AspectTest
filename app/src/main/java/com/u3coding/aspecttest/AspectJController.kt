package com.u3coding.aspecttest

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut


@Aspect
class AspectJController {

    @Pointcut("execution(void onClick(..))")
    fun onClick() {
    }

    @Pointcut("execution(void _internalCallbackOnClick(..))")
    fun onBindingClick() {
    }

    @Around("onBindingClick()")
    @Throws(Throwable::class)
    fun onClickMethodBinding(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        var className = ""
        if (target != null) {
            className = target.javaClass.name
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
            }
            if (className.contains("_ViewBinding")) {
                className = className.split("_ViewBinding").toTypedArray()[0]
            }
        }
        val args = joinPoint.args
        if (args.size >= 1 && args[1] is View) {
            val view = args[1] as View
            var index = -1
            if (view.parent is RecyclerView) {
                index = (view.parent as RecyclerView).getChildPosition(view)
            }
            val id = view.id
            if (id >= 0) {
                if (index >= 0) {
                    className += ":$index"
                }
                val entryName = view.resources.getResourceEntryName(id)
                AspectJManager.onClick(className, entryName)
            }
        }
        joinPoint.proceed() //执行原来的代码
    }

    @Around("onClick()")
    @Throws(Throwable::class)
    fun onClickMethodAround(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        var className = ""
        if (target != null) {
            className = target.javaClass.name
            if (className.contains("$")) {
                className = className.split("\\$").toTypedArray()[0]
            }
            if (className.contains("_ViewBinding")) {
                className = className.split("_ViewBinding").toTypedArray()[0]
            }
        }
        if (!className.contains("OnClickListener")) {
            val args = joinPoint.args
            if (args.isNotEmpty() && args[0] is View) {
                val view = args[0] as View
                var index = -1
                if (view.parent is RecyclerView) {
                    index = (view.parent as RecyclerView).getChildPosition(view)
                }
                val id = view.id
                if (id >= 0) {
                    if (index >= 0) {
                        className += ":$index"
                    }
                    val entryName = view.resources.getResourceEntryName(id)
                    AspectJManager.onClick(className, entryName)
                }
            }
        }
        joinPoint.proceed() //执行原来的代码
    }

    @Around("execution(* onResume()) && within(com.commontech.basemodule.databinding.base.BaseBindingFragment)")
    @Throws(
        Throwable::class
    )
    fun onFragmentResume(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        val className = target.javaClass.name
        AspectJManager.onFragmentOpen(className)
        joinPoint.proceed()
    }

    @Around("execution(* onPause()) && within(com.commontech.basemodule.databinding.base.BaseBindingFragment)")
    @Throws(
        Throwable::class
    )
    fun onFragmentPause(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        val className = target.javaClass.name
        AspectJManager.onFragmentClose(className)
        joinPoint.proceed()
    }

    @Pointcut("execution(* onResume()) && within(com.commontech.basemodule.databinding.base.BaseBindingActivity)")
    fun openActivity() {
    }

    @Pointcut("execution(* onDestroy()) && within(com.commontech.basemodule.databinding.base.BaseBindingActivity)")
    fun closeActivity() {
    }

    @Around("openActivity()")
    @Throws(Throwable::class)
    fun openActivityMethodAround(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        val className = target.javaClass.name
        AspectJManager.onActivityOpen(className)
        joinPoint.proceed()
    }

    @Around("closeActivity()")
    @Throws(Throwable::class)
    fun closeActivityMethodAround(joinPoint: ProceedingJoinPoint) {
        val target = joinPoint.target
        val className = target.javaClass.name
        AspectJManager.onActivityClose(className)
        joinPoint.proceed()
    }
}
