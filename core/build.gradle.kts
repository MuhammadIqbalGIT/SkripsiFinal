plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")


}

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    flavorDimensions("env")
    productFlavors {
        create("dev") { // development environment
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://localhost:44333/\"")
        }
        create("prod") { // production environment
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://apiv1.1000saudara.com/\"")
        }
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
        dataBinding = true

    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.datatransport:transport-runtime:3.2.0")







    //*Retrofit*\\
    implementation("com.google.code.gson:gson:2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")

//    //*Dager Hilt*\\
//    implementation("com.google.dagger:hilt-android:2.48")
//    kapt("com.google.dagger:hilt-compiler:2.48")
//    kapt("androidx.hilt:hilt-compiler:1.0.0")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    //NEW HILT
    implementation ("com.google.dagger:hilt-android:2.46")
    kapt ("com.google.dagger:hilt-android-compiler:2.46")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")


    //

}