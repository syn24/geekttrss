package com.geekorum.build.conventions


/**
 * Precompiled [android-application.gradle.kts][com.geekorum.build.conventions.Android_application_gradle] script plugin.
 *
 * @see com.geekorum.build.conventions.Android_application_gradle
 */
public
class AndroidApplicationPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("com.geekorum.build.conventions.Android_application_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
