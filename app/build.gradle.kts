plugins {
    alias(libs.plugins.android.application)
    id("com.google.gms.google-services") // Plugin para Firebase
}

android {
    namespace = "com.example.calmfly"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.calmfly"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // 📌 Usamos Firebase BoM para gestionar versiones automáticamente
    implementation(platform("com.google.firebase:firebase-bom:32.2.0"))  // Usar el Firebase BoM para gestionar las versiones

    // 🔹 Dependencias de Firebase (sin especificar la versión, el BoM manejará todo)
    implementation("com.google.firebase:firebase-auth")  // Autenticación
    implementation("com.google.firebase:firebase-firestore")  // Firestore
    implementation("com.google.firebase:firebase-database")  // Realtime Database
    implementation("com.google.firebase:firebase-storage")  // Almacenamiento
    implementation("com.google.firebase:firebase-messaging")  // Notificaciones push
}

apply(plugin = "com.google.gms.google-services") // Aplicar el plugin de Firebase al final
