object Apps {
    const val compileSdk = 33
    const val minSdk = 21
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val applicationId = "com.vkornee.weatherapp"
    const val namespace = "com.vkornee.weatherapp"
}

object Versions {
    const val gradlePlugin = "8.0.2"
    const val kotlin = "1.8.21"
    const val hilt = "2.46.1"
    const val hiltNavigationCompose = "1.0.0"
    const val safeArgs = "2.6.0"
    const val room = "2.5.2"
    const val retrofit = "2.9.0"
    const val okHttpLoggingInterceptor = "4.11.0"
    const val coroutines = "1.6.4"
    const val navigation = "2.6.0"
    const val composeBom = "2023.05.01"
    const val composeTooling = "1.4.3"
    const val composeViewModel = "2.6.1"
    const val activityCompose = "1.7.2"
    const val dataStore = "1.0.0"
    const val coreKtx = "1.10.1"
    const val appcompat = "1.6.1"
    const val material = "1.9.0"
    const val constraintLayout = "2.1.4"
    const val accompanist = "0.30.1"
    const val coil = "2.4.0"
    const val kotlinImmutableCollections = "0.3.5"
    const val kotlinDateTime = "0.4.0"
    const val libdesugaring = "2.0.3"

    /* test */
    const val junit = "4.13.2"
    const val testExt = "1.1.5"
    const val espressoCore = "3.5.1"
}

object Plugins {
    object Android {
        val name = "com.android.application"
        val classpath = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    }
    object Kotlin {
        val androidName = "kotlin-android"
        val kaptName = "kotlin-kapt"
        val classpath = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }
    object Hilt {
        val name = "dagger.hilt.android.plugin"
        val classpath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    }
    object SafeArgs {
        val name = "androidx.navigation.safeargs"
        val classpath = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
    }
}
object BuildScriptDeps {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"
}

object Libs {

    object Compose {
        const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val foundation = "androidx.compose.foundation:foundation"
        const val material3 = "androidx.compose.material3:material3"
        const val navigation = "androidx.navigation:navigation-compose"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModel}"
    }

    object Preferences {
        const val datastore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Kotlin {
        const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val kotlinCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val kotlinCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val immutableCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.kotlinImmutableCollections}"
        const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinDateTime}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    }

    object OkHttp {
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"
    }

    object Android {
        const val androidCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val desugaringLibs = "com.android.tools:desugar_jdk_libs:${Versions.libdesugaring}"
    }

    object Hilt {
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
        const val navigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    }

    object Navigation {
        const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    }

    object Utils {
        const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

}

object TestLibs {
    const val junit = "junit:junit:${Versions.junit}"
    const val testExt = "androidx.test.ext:junit:${Versions.testExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}
