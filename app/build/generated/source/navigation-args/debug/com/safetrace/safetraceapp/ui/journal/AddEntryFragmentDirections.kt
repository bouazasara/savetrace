package com.safetrace.safetraceapp.ui.journal

import android.os.Bundle
import androidx.navigation.NavDirections
import com.safetrace.safetraceapp.R
import kotlin.Boolean
import kotlin.Int

public class AddEntryFragmentDirections private constructor() {
  private data class ActionAddEntryFragmentToCameraFragment(
    public val isPhoto: Boolean,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_addEntryFragment_to_cameraFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putBoolean("isPhoto", this.isPhoto)
        return result
      }
  }

  public companion object {
    public fun actionAddEntryFragmentToCameraFragment(isPhoto: Boolean): NavDirections =
        ActionAddEntryFragmentToCameraFragment(isPhoto)
  }
}
