plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.networktest"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.networktest"
        minSdk = 24
        targetSdk = 36
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.3.2"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation(platform("com.google.code.gson:gson:2.13.2"))
    implementation("com.google.code.gson:gson")
    implementation(platform("com.squareup.retrofit2:retrofit:2.6.1"))
    implementation("com.squareup.retrofit2:retrofit")
    implementation(platform("com.squareup.retrofit2:converter-gson:2.6.1"))
    implementation("com.squareup.retrofit2:converter-gson")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

}