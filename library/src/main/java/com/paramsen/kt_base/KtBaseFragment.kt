package com.paramsen.kt_base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Kotlin extension for BaseFragment to Kotlinize impl
 *
 * @author Pär Amsen 10/2017
 */
abstract class KtBaseFragment : Fragment() {
    abstract val layoutRes: Int

    val lifecycle = Lifecycle()
    val autodispose = AutoDispose(lifecycle)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutRes, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.update(LifecycleEvent.CREATE)
    }

    override fun onResume() {
        super.onResume()
        lifecycle.update(LifecycleEvent.RESUME)
    }

    override fun onPause() {
        lifecycle.update(LifecycleEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycle.update(LifecycleEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycle.update(LifecycleEvent.DESTROY)
        super.onDestroy()
    }
}