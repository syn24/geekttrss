# Keep dynamically added DataBinderMapper from this feature module
# https://issuetracker.google.com/issues/135574730
# Using ** wildcard to suppress IDE "Unresolved class name" inspection for generated code
-keep class com.geekorum.ttrss.manage_feeds.**DataBinderMapperImpl { *; }

# Keep Activity referenced by String string literal in Navigation.kt
-keep class com.geekorum.ttrss.manage_feeds.**ManageFeedsActivity { *; }
-keep class com.geekorum.ttrss.manage_feeds.add_feed.**AddFeedActivity { *; }

# Keep kotlinx.serialization classes used in Navigation
# Using ** wildcard for annotations to avoid IDE resolution errors if library path is not indexed in ProGuard editor
-keep @**.Serializable class com.geekorum.ttrss.manage_feeds.** { *; }
-keepclassmembers class com.geekorum.ttrss.manage_feeds.** {
    * serializer(...);
}
