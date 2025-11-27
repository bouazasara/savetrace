package com.safetrace.safetraceapp.ui.journal

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.safetrace.safetraceapp.R

public class CameraFragmentDirections private constructor() {
  public companion object {
    public fun actionCameraFragmentToAddEntryFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_cameraFragment_to_addEntryFragment)
  }
}
