package com.byoutline.secretsauce.rx

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager

fun FragmentActivity.invokeOnStopped(action: () -> Unit) {
    val activity = this
    application.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(a: Activity?, savedInstanceState: Bundle?) {}

        override fun onActivityStarted(a: Activity?) {}

        override fun onActivityResumed(a: Activity?) {}
        override fun onActivityPaused(a: Activity?) {}
        override fun onActivityStopped(a: Activity?) {
            if (a === activity) action()
        }

        override fun onActivitySaveInstanceState(a: Activity?, outState: Bundle?) {}
        override fun onActivityDestroyed(a: Activity?) {
            if (a === activity) application.unregisterActivityLifecycleCallbacks(this)
        }
    })
}

fun Fragment.invokeOnStopped(action: () -> Unit) {
    val fragment = this
    activity!!.supportFragmentManager.registerFragmentLifecycleCallbacks(object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentStopped(fm: FragmentManager?, f: Fragment?) {
            if (f === fragment) action()
        }

        override fun onFragmentDestroyed(fm: FragmentManager, f: Fragment?) {
            if (f === fragment) fm.unregisterFragmentLifecycleCallbacks(this)
        }
    }, false)
}
