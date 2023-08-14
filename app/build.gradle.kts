@file:Suppress("UnstableApiUsage")

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility(JavaVersion.VERSION_17)
        targetCompatibility (JavaVersion.VERSION_17)
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures {
        dataBinding = true
        compose = true
    }

    composeOptions { kotlinCompilerExtensionVersion = "1.5.0" }

}

kapt {
    correctErrorTypes = true
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)

    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.kotlin.coroutines)
    implementation(libs.bundles.navigation.ext)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.compose.ext)

    implementation(libs.kotlin.stdlib)
    implementation(libs.datetime)
    implementation(libs.immutable.collections)
    implementation(libs.datastore)
    implementation(libs.retrofit)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.android.core.ktx)
    implementation(libs.accompanist)
    implementation(libs.coil)
    coreLibraryDesugaring(libs.desugaring.libs)

    testImplementation(libs.junit)
}
