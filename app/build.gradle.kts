import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

val localProperties = Properties()
localProperties.load(FileInputStream(rootProject.file("local.properties")))

val STRING = "String"
val TIMEOUT: Long = localProperties.getProperty("TIMEOUT")?.toLongOrNull() ?: 0L
val BASE_URL = localProperties.getProperty("BASE_URL")?.let { "\"$it\"" } ?: "\"Define your URL!\""
val API_KEY_GITHUB = localProperties.getProperty("API_KEY_GITHUB")?.let { "\"$it\"" } ?: "\"Define your API KEY Github!\""

android {
    namespace = "com.papero.minisqlite"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.papero.minisqlite"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("Long", "TIMEOUT", TIMEOUT.toString())
        buildConfigField(STRING, "BASE_URL", BASE_URL)
        buildConfigField(STRING, "API_KEY_GITHUB", API_KEY_GITHUB)
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.android.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraint)
    implementation(libs.okhttp.loging.interceptor)
    implementation(libs.rx.java)
    implementation(libs.rx.android)
    implementation(libs.rx.kotlin)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.retrofit.scalars.converter)
    implementation(libs.rx.adapter)
    implementation(libs.okhttp)
    implementation(libs.glide.img)
    kapt(libs.glide.compiler)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}