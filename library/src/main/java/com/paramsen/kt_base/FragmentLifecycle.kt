package com.paramsen.kt_base

import com.paramsen.kt_base.FragmentLifecycle.FragmentLifecycleEvent

/**
 * @author Pär Amsen 10/2017
 */
class FragmentLifecycle : Lifecycle<FragmentLifecycleEvent>(FragmentLifecycleEvent.NONE, FragmentLifecycleEvent.DESTROY) {
    public enum class FragmentLifecycleEvent {
        NONE, CREATE, RESUME, PAUSE, STOP, DESTROY
    }
}