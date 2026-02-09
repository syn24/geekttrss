package com.geekorum.ttrss.add_feed

import androidx.`annotation`.CheckResult
import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import com.geekorum.ttrss.R

public class StartInstallFragmentDirections private constructor() {
  public companion object {
    @CheckResult
    public fun actionInstallManageFeed(): NavDirections = ActionOnlyNavDirections(R.id.action_install_manage_feed)
  }
}
