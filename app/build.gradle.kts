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
    //implementation(libs.exoplayer)
    implementation(libs.androidx.ui.graphics.android)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.media3.common.ktx)
    testImplementation(libs.junit)

    // Pour les tests instrumentés (Android)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    implementation(libs.androidx.media3.bom) // Vérifie la dernière version disponible

    // Dépendances Media3
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui) // Si tu utilises des composants UI de Media3
    implementation(libs.androidx.media3.session) // Si tu gères les sessions médias

    // Optionnel : autres modules Media3 selon tes besoins
    implementation(libs.androidx.media3.datasource)
    implementation(libs.androidx.media3.transformer)
}
