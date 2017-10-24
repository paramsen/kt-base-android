package com.paramsen.kt_base

import android.util.Log
import rx.Subscription
import rx.subjects.Subject
import rx.subscriptions.CompositeSubscription

/**
 * AutoDispose allows for binding a subcription/subject to the host Activity/Fragment lifecycle, so
 * that they end on DESTROY event from host.
 *
 * Similar impl as the well known autodispose. Bound to the supplied LifeCycle.
 *
 * @author Pär Amsen 10/2017
 */
class AutoDispose(lifecycle: Lifecycle) {
    private val tag = AutoDispose::class.java.simpleName!!

    public val subscriptions = CompositeSubscription()
    public val subjects = ArrayList<Subject<*, *>>()

    init {
        lifecycle.stream().toCompletable().subscribe(this::dispose)
    }

    private fun dispose() {
        Log.d(tag, "dispose")

        subscriptions.clear()
        for (s in subjects) s.onCompleted()
        subjects.clear()
    }

    /**
     * Added Subscription will be unsubscribed on DESTROY lifecycle event
     */
    public operator fun plus(s: Subscription) {
        subscriptions.add(s)
    }

    public operator fun plus(subject: Subject<*, *>) {
        subjects + subject
    }
}