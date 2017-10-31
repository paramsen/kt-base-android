package com.paramsen.kt_base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.paramsen.kt_base.FragmentLifecycle.FragmentLifecycleEvent.*

/**
 * Kotlin extension for BaseFragment to Kotlinize impl
 *
 * @author Pär Amsen 10/2017
 */
abstract class KtBaseFragment : Fragment() {
    abstract val layoutRes: Int

    public val lifecycle = FragmentLifecycle()
    public val autodispose = AutoDispose(lifecycle)

    public override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(layoutRes, container, false)
    }

    public override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.update(CREATE)
    }

    public override fun onResume() {
        super.onResume()
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