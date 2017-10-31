package com.paramsen.kt_base

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.atomic.AtomicReference

/**
 * LifeCycle as in the Architecture Components, used to make the host Activity/Fragment lifecycle
 * observable and accessible.
 *
 * @author Pär Amsen 10/2017
 */
abstract class Lifecycle<EVENT>(startWith: EVENT, private val disposeOn: EVENT) {

    private val current = AtomicReference<EVENT>(startWith)
    private val stream = BehaviorSubject.create<EVENT>()

    public fun get(): EVENT {
        return current.get()
    }

    public fun stream(): Flowable<EVENT> {
        return stream.toFlowable(BackpressureStrategy.DROP)
    }

    public fun update(e: EVENT) {
        current.set(e)
        stream.onNext(e)
        if (e == disposeOn) {
            stream.onComplete()
        }
    }
}