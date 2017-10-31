package com.paramsen.kt_base

import com.paramsen.kt_base.ActivityLifecycle.ActivityLifecycleEvent

/**
 * @author Pär Amsen 10/2017
 */
class ActivityLifecycle: Lifecycle<ActivityLifecycleEvent>(ActivityLifecycleEvent.NONE, ActivityLifecycleEvent.DESTROY) {
    public enum class ActivityLifecycleEvent {
        NONE, CREATE, RESUME, PAUSE, STOP, DESTROY
    }
}