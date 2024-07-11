plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "spaysdk.integrationexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "spaysdk.integrationexample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.7.6")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

// =====================================================================================================================

    /**
     * Интеграция используя maven
     *
     * Имплементация SPaySdk
     */
    implementation("ru.spaymentsplus.libraries:spaysdk:2.2.0")

// =====================================================================================================================

    /**
     * Интеграция используя aar
     *
     * При интеграции с aar, необходимо поместить spaysdk-x.y.z.aar и fingerprint-x.y.z.aar в директорию ../libs
     *
     * Имплементация SPaySdk и fingerprint из aar, а также всех необходимых транзитивных зависимостей
     */
//    implementation(files("../libs/spaysdk-2.2.0.aar"))
//    implementation(files("../libs/fingerprint-1.9.6.aar"))
//
//     Activity
//    implementation("androidx.activity:activity-ktx:1.6.1")
//
//     SberIdSDK
//    implementation("io.github.sberid:SberIdSDK:2.4.3")
//
//     Coroutines
//    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
//
//     Dynatrace
//    implementation("com.dynatrace.agent:agent-android:8.257.1.1007")
//
//     OkHttp
//    implementation("com.squareup.okhttp3:okhttp:4.10.0")
//    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
//
//     Retrofit
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("com.squareup.retrofit2:converter-gson:2.3.0")
//
//     Jsoup
//    implementation("org.jsoup:jsoup:1.13.1")
//
//     Play services
//    implementation("com.google.android.gms:play-services-safetynet:18.0.1")
//    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
//    implementation("com.google.android.gms:play-services-appset:16.0.2")
//    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1")
//
//     Dagger2
//    implementation("com.google.dagger:dagger:2.44")
//    annotationProcessor("com.google.dagger:dagger-compiler:2.44")
//
//     Timber
//    implementation("com.jakewharton.timber:timber:5.0.1")
//
//     Three Ten BP
//    implementation("com.jakewharton.threetenabp:threetenabp:1.2.1")
//    testImplementation("org.threeten:threetenbp:1.2.1") {
//        exclude("com.jakewharton.threetenabp:threetenabp:1.2.0")
//    }
//
//     Coil
//    implementation("io.coil-kt:coil-base:2.4.0")
//    implementation("io.coil-kt:coil-svg:2.4.0")
//
//     Shimmer
//    implementation("com.facebook.shimmer:shimmer:0.5.0")
//
//     Firebase Database
//    implementation("com.google.firebase:firebase-database-ktx:20.2.0")
//
//     Biometric
//    implementation("androidx.biometric:biometric:1.1.0")
//
//     Encrypted Shared Preferences
//    implementation("androidx.security:security-crypto:1.1.0-alpha06")
//
//     Lottie
//    implementation("com.airbnb.android:lottie:6.2.0")
//
// =====================================================================================================================

}