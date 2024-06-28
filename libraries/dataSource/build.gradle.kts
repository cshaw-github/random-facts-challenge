plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.ksp)
	alias(libs.plugins.hilt)
}

android {
    namespace = "com.pelagohealth.codingchallenge.datasource"
    compileSdk = 34

    defaultConfig {
        minSdk = 23

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
	implementation(project(":libraries:dataLayer"))
    implementation(project(":libraries:utils"))
	implementation(libs.kotlin.coroutines)

	// Retrofit
	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.moshi)
	implementation(libs.retrofit.converter.gson)

	// Moshi
	implementation(libs.moshi)

	// OkHttp3
	implementation(libs.okHttp3)
	implementation(libs.okHttp3.logging.interceptor)

	// Hilt
	ksp(libs.hilt.android.compiler)
	implementation(libs.hilt.android)

	// Koin
	implementation(libs.koin.core)

    testImplementation(libs.junit)
	implementation(libs.kotlin.test)
	implementation(libs.kotlin.coroutines.test)
	testImplementation(libs.mockk)
}
