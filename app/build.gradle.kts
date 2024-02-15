plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.moviesappsofttek"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviesappsofttek"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug{
            buildConfigField("String", "APPNAME_API_KEY", "\"066f9ce5063555c7d30a8de069ebd519\"")
            buildConfigField("String", "BASE_URL_MOVIE", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "BASE_URL_IMAGE", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "BASE_URL_LOGIN", "\"http://demo9476408.mockable.io/\"")
            buildConfigField("String", "END_POINT_LOGIN", "\"iniciarSesion\"")
        }

        release {
            buildConfigField("String", "APPNAME_API_KEY", "\"066f9ce5063555c7d30a8de069ebd519\"")
            buildConfigField("String", "BASE_URL_MOVIE", "\"https://api.themoviedb.org/\"")
            buildConfigField("String", "BASE_URL_IMAGE", "\"https://image.tmdb.org/t/p/w500\"")
            buildConfigField("String", "BASE_URL_LOGIN", "\"http://demo9476408.mockable.io/\"")
            buildConfigField("String", "END_POINT_LOGIN", "\"iniciarSesion\"")
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
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Compose Navigation
    implementation ("androidx.navigation:navigation-compose:2.5.3")
    implementation("io.coil-kt:coil-compose:2.4.0")
    // Navigation Components
    implementation ("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.3")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    //KTX
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.google.code.gson:gson:2.9.1")
    implementation ("com.squareup.okhttp3:okhttp")
    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    //CardView
    implementation("androidx.cardview:cardview:1.0.0")
    //Room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")
    //Jetpack components
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    implementation ("com.google.accompanist:accompanist-swiperefresh:0.27.0")
    //Glide
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    //Dagger hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
}

kapt {
    correctErrorTypes = true
}
