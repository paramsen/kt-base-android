package com.paramsen.kt_base

import android.util.Log
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.Subject

/**
 * AutoDispose allows for binding a subcription/subject to the host Activity/Fragment lifecycle, so
 * that they end on DESTROY event from host.
 *
 * Similar impl as the well known autodispose. Bound to the supplied LifeCycle.
 *
 * @author Pär Amsen 10/2017
 */
class AutoDispose(lifecycle: Lifecycle<*>) {
    private val tag = AutoDispose::class.java.simpleName!!

    private val disposable = CompositeDisposable()
    private val subjects = ArrayList<Subject<*>>()

    init {
        lifecycle.stream().ignoreElements().subscribe(this::dispose)
    }

    private fun dispose() {
        Log.d(tag, "dispose")

        disposable.clear()
        for (s in subjects) s.onComplete()
        subjects.clear()
    }

    /**
     * Added Subscription will be unsubscribed on DESTROY lifecycle event
     */
    public operator fun plusAssign(s: Disposable) {
        disposable.add(s)
    }

    public operator fun plusAssign(subject: Subject<*>) {
        subjects + subject
    }
}