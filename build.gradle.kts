// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.hiltAndroidGradlePlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module base-module.gradle files
    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}