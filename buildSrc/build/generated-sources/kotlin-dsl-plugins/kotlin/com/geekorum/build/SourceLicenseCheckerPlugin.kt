package com.geekorum.build


/**
 * Precompiled [source-license-checker.gradle.kts][com.geekorum.build.Source_license_checker_gradle] script plugin.
 *
 * @see com.geekorum.build.Source_license_checker_gradle
 */
public
class SourceLicenseCheckerPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("com.geekorum.build.Source_license_checker_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
