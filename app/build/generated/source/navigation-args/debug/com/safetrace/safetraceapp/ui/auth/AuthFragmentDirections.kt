package com.safetrace.safetraceapp.ui.auth

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.safetrace.safetraceapp.R

public class AuthFragmentDirections private constructor() {
  public companion object {
    public fun actionAuthFragmentToRegistrationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_authFragment_to_registrationFragment)

    public fun actionAuthFragmentToHomeFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_authFragment_to_homeFragment)
  }
}
