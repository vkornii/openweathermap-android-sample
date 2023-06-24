buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Plugins.Android.classpath)
        classpath(Plugins.Kotlin.classpath)
        classpath(Plugins.Hilt.classpath)
        classpath(Plugins.SafeArgs.classpath)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
