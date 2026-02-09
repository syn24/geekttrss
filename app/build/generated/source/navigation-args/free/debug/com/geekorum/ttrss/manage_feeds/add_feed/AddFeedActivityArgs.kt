package com.geekorum.ttrss.manage_feeds.add_feed

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import kotlin.String
import kotlin.jvm.JvmStatic

public data class AddFeedActivityArgs(
  public val androidIntentExtraTEXT: String? = "",
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("android.intent.extra.TEXT", this.androidIntentExtraTEXT)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("android.intent.extra.TEXT", this.androidIntentExtraTEXT)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): AddFeedActivityArgs {
      bundle.setClassLoader(AddFeedActivityArgs::class.java.classLoader)
      val __androidIntentExtraTEXT : String?
      if (bundle.containsKey("android.intent.extra.TEXT")) {
        __androidIntentExtraTEXT = bundle.getString("android.intent.extra.TEXT")
      } else {
        __androidIntentExtraTEXT = ""
      }
      return AddFeedActivityArgs(__androidIntentExtraTEXT)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): AddFeedActivityArgs {
      val __androidIntentExtraTEXT : String?
      if (savedStateHandle.contains("android.intent.extra.TEXT")) {
        __androidIntentExtraTEXT = savedStateHandle["android.intent.extra.TEXT"]
      } else {
        __androidIntentExtraTEXT = ""
      }
      return AddFeedActivityArgs(__androidIntentExtraTEXT)
    }
  }
}
