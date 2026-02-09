package com.geekorum.build


/**
 * Precompiled [android-tests.gradle.kts][com.geekorum.build.Android_tests_gradle] script plugin.
 *
 * @see com.geekorum.build.Android_tests_gradle
 */
public
class AndroidTestsPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("com.geekorum.build.Android_tests_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
