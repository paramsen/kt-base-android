package com.paramsen.kt_base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.paramsen.kt_base.ActivityLifecycle.ActivityLifecycleEvent.*

/**
 * Kotlin extension for BaseActivity to Kotlinize impl
 *
 * @author Pär Amsen 10/2017
 */
abstract class KtBaseActivity(val layoutRes: Int) : AppCompatActivity() {
    val lifecycle = ActivityLifecycle()
    val autodispose = AutoDispose(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        lifecycle.update(CREATE)
    }

    public override fun onPostResume() {
        super.onPostResume()
        lifecycle.update(RESUME)
    }

    public override fun onPause() {
        lifecycle.update(PAUSE)
        super.onPause()
    }

    public override fun onStop() {
        lifecycle.update(STOP)
        super.onStop()
    }

    public override fun onDestroy() {
        lifecycle.update(DESTROY)
        super.onDestroy()
    }
}