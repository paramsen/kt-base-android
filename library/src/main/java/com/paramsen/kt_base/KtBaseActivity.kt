package com.paramsen.kt_base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Kotlin extension for BaseActivity to Kotlinize impl
 *
 * @author Pär Amsen 10/2017
 */
abstract class KtBaseActivity(val layoutRes: Int) : AppCompatActivity() {
    val lifecycle = Lifecycle()
    val autodispose = AutoDispose(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        lifecycle.update(LifecycleEvent.CREATE)
    }

    public override fun onPostResume() {
        super.onPostResume()
        lifecycle.update(LifecycleEvent.RESUME)
    }

    public override fun onPause() {
        lifecycle.update(LifecycleEvent.PAUSE)
        super.onPause()
    }

    public override fun onStop() {
        lifecycle.update(LifecycleEvent.STOP)
        super.onStop()
    }

    public override fun onDestroy() {
        lifecycle.update(LifecycleEvent.DESTROY)
        super.onDestroy()
    }
}