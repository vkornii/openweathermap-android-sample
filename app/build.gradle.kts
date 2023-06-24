@file:Suppress("UnstableApiUsage")

plugins {
    id(Plugins.Android.name)
    id(Plugins.Kotlin.androidName)
    id(Plugins.Kotlin.kaptName)
    id(Plugins.Hilt.name)
    id(Plugins.SafeArgs.name)
}

val openWeatherMapApikey: String by project
val openWeatherMapUnits: String by project

android {
    namespace = Apps.namespace
    compileSdk = Apps.compileSdk
    defaultConfig {
        applicationId = Apps.applicationId
        minSdk = Apps.minSdk
        targetSdk = Apps.targetSdk
        versionCode = Apps.versionCode
        versionName = Apps.versionName

        buildConfigField ("String", "OPEN_WEATHER_MAP_API_KEY", "\"${openWeatherMapApikey}\"")
        buildConfigField ("String", "OPEN_WEATHER_MAP_UNIT", "\"${openWeatherMapUnits}\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility (JavaVersion.VERSION_17)
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures {
        dataBinding = true
        compose = true
    }

    composeOptions { kotlinCompilerExtensionVersion = "1.4.7" }

}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Libs.Kotlin.kotlinStdlib)
    implementation(Libs.Kotlin.kotlinCoroutinesCore)
    implementation(Libs.Kotlin.kotlinCoroutinesAndroid)

    kapt(Libs.Hilt.hiltCompiler)
    implementation(Libs.Hilt.hilt)

    implementation(platform(Libs.Compose.composeBom))
    implementation(Libs.Compose.activity)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.lifecycleViewModel)
    implementation(Libs.Compose.uiToolingPreview)
    debugImplementation(Libs.Compose.uiTooling)

    kapt(Libs.Room.compiler)
    implementation(Libs.Room.ktx)
    implementation(Libs.Room.runtime)

    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterGson)

    implementation(Libs.Navigation.navigationUiKtx)
    implementation(Libs.Navigation.navigationFragmentKtx)

    implementation(Libs.Android.androidCoreKtx)
    implementation(Libs.Android.appcompat)
    implementation(Libs.Android.constraintLayout)
    implementation(Libs.Android.material)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.testExt)
    androidTestImplementation(TestLibs.espressoCore)
}
