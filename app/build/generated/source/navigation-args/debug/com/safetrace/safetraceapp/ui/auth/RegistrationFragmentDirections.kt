package com.safetrace.safetraceapp.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.safetrace.safetraceapp.R

public class RegistrationFragmentDirections private constructor() {
  public companion object {
    public fun actionRegistrationFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_registrationFragment_to_homeFragment)
  }
}
