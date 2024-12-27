import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    // Plugin Android
    id("com.android.application")
    // Plugin Kotlin Android
    kotlin("android")
}

android {
    namespace = "com.example.mywebradioapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mywebradioapp"
        minSdk = 29
        targetSdk = 35    // Tu peux laisser 34, mais 35 est conseillé
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
// Active la compilation Compose
    buildFeatures {
        compose = true
    }

    // Dis que tu utilises une version précise du Compose Compiler
    composeOptions {
        // Exemple si tu utilises Compose 1.5.3
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    // Aligner la compilation Java sur la version 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
}

// Aligner la compilation Kotlin sur la version 17
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Bibliothèque de base Android (KTX)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat)

    // Material Design
    implementation(libs.material)

    // ExoPlayer (pour la lecture des flux audio)
    implementation(libs.exoplayer)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    testImplementation("junit:junit:4.13.2")

    // Pour les tests instrumentés (Android)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
