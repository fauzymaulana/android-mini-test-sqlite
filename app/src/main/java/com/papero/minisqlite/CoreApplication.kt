package com.papero.minisqlite

import android.app.Activity
import android.app.Application
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.papero.minisqlite.di.BaseInjection

class CoreApplication: Application() ,Application.ActivityLifecycleCallbacks {
    val coreInjection by lazy { BaseInjection() }

    companion object {
        private const val TAG = "LifecycleCallbacks"
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        p0.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.d(TAG, "onActivityCreated at ${p0.localClassName}")
    }

    override fun onActivityStarted(p0: Activity) {
        Log.d(TAG, "onActivityStarted at ${p0.localClassName}")
    }

    override fun onActivityResumed(p0: Activity) {
        p0.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Log.d(TAG, "onActivityResumed at ${p0.localClassName}")
    }

    override fun onActivityPaused(p0: Activity) {
        Log.d(TAG, "onActivityPaused at ${p0.localClassName}")
    }

    override fun onActivityStopped(p0: Activity) {
        Log.d(TAG, "onActivityStopped at ${p0.localClassName}")
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
        Log.d(TAG, "onActivitySaveInstanceState at ${p0.localClassName}")
    }

    override fun onActivityDestroyed(p0: Activity) {
        Log.d(TAG, "onActivityDestroyed at ${p0.localClassName}")
    }
}