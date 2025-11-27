package com.safetrace.safetraceapp.ui.journal

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.safetrace.safetraceapp.R

public class JournalFragmentDirections private constructor() {
  public companion object {
    public fun actionJournalFragmentToAddEntryFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_journalFragment_to_addEntryFragment)
  }
}
