package com.geekorum.ttrss.manage_feeds.add_feed

import android.os.Bundle
import androidx.`annotation`.CheckResult
import androidx.navigation.NavDirections
import com.geekorum.ttrss.R
import kotlin.Int
import kotlin.String

public class CompleteInstallFragmentDirections private constructor() {
  private data class ActionAddFeed(
    public val androidIntentExtraTEXT: String? = "",
  ) : NavDirections {
    public override val actionId: Int = R.id.action_add_feed

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("android.intent.extra.TEXT", this.androidIntentExtraTEXT)
        return result
      }
  }

  public companion object {
    @CheckResult
    public fun actionAddFeed(androidIntentExtraTEXT: String? = ""): NavDirections = ActionAddFeed(androidIntentExtraTEXT)
  }
}
