package com.paramsen.kt_base

import rx.Observable
import rx.subjects.BehaviorSubject
import java.util.concurrent.atomic.AtomicReference

/**
 * LifeCycle as in the Architecture Components, used to make the host Activity/Fragment lifecycle
 * observable and accessible.
 *
 * @author Pär Amsen 10/2017
 */
class Lifecycle {
    private val current = AtomicReference<LifecycleEvent>(LifecycleEvent.NONE)
    private val stream = BehaviorSubject.create<LifecycleEvent>()

    public fun get(): LifecycleEvent {
        return current.get()
    }

    public fun stream(): Observable<LifecycleEvent> {
        return stream.asObservable()
    }

    public fun update(e: LifecycleEvent) {
        current.set(e)
        stream.onNext(e)
        if(e == LifecycleEvent.DESTROY) {
            stream.onCompleted()
        }
    }
}