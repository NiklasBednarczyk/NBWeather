package de.niklasbednarczyk.nbweather.core.common.logging

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase

fun nbLogError(throwable: Throwable) {
    Firebase.crashlytics.recordException(throwable)
    //TODO (#5) Better logging
    Log.e("TAGTAGTAG", throwable.message.toString())
}
