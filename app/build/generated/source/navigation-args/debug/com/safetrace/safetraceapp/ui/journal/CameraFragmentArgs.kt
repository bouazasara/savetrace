package com.safetrace.safetraceapp.ui.journal

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Boolean
import kotlin.jvm.JvmStatic

public data class CameraFragmentArgs(
  public val isPhoto: Boolean,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putBoolean("isPhoto", this.isPhoto)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("isPhoto", this.isPhoto)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): CameraFragmentArgs {
      bundle.setClassLoader(CameraFragmentArgs::class.java.classLoader)
      val __isPhoto : Boolean
      if (bundle.containsKey("isPhoto")) {
        __isPhoto = bundle.getBoolean("isPhoto")
      } else {
        throw IllegalArgumentException("Required argument \"isPhoto\" is missing and does not have an android:defaultValue")
      }
      return CameraFragmentArgs(__isPhoto)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): CameraFragmentArgs {
      val __isPhoto : Boolean?
      if (savedStateHandle.contains("isPhoto")) {
        __isPhoto = savedStateHandle["isPhoto"]
        if (__isPhoto == null) {
          throw IllegalArgumentException("Argument \"isPhoto\" of type boolean does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"isPhoto\" is missing and does not have an android:defaultValue")
      }
      return CameraFragmentArgs(__isPhoto)
    }
  }
}
