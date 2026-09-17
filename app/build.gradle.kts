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


    /**
     * Интеграция используя aar
     *
     * При интеграции aar, необходимо поместить spaysdk-x.y.z.aar и fingerprint-x.y.z.aar в директорию ../libs
     *
     * Имплементация SPaySdk и fingerprint из aar, а также всех необходимых транзитивных зависимостей
     */
    implementation(files("../libs/SPaySDK-3.2.3.aar"))
    implementation(files("../libs/fingerprint-1.10.3.aar"))


    // Clickstream
    implementation("platform.mobile.clickstream:clickstreamlib:2.6.2")
    implementation("platform.mobile.clickstream:models:2.6.2")
    implementation("platform.mobile.clickstream:network:2.6.2")
    implementation("platform.mobile.clickstream:utils:2.6.2")

    // Material
    implementation("com.google.android.material:material:1.9.0")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Retofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

    // Jsoup
    implementation("org.jsoup:jsoup:1.21.1")

    // Play services
    implementation("com.google.android.gms:play-services-auth-api-phone:18.0.1")

    // Dagger2
    implementation("com.google.dagger:dagger:2.51")

    // Threeten
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.9")

    // Coil
    implementation("io.coil-kt:coil-base:2.4.0")
    implementation("io.coil-kt:coil-svg:2.4.0")

    // Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // Biometric
    implementation("androidx.biometric:biometric:1.1.0")
    implementation("androidx.security:security-crypto:1.1.0")

    // Lottie
    implementation("com.airbnb.android:lottie:6.2.0")

    // Json-serializer
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

    // Datastore
    implementation("androidx.datastore:datastore:1.1.0")
    implementation("androidx.datastore:datastore-preferences:1.1.0")

    // Tink
    implementation("com.google.crypto.tink:tink-android:1.20.0")
}
