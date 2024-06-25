plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "com.pelagohealth.codingchallenge.datalayer"
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
	implementation(project(":libraries:domainLayer"))
	implementation(project(":libraries:utils"))
	implementation(libs.kotlin.coroutines)

    testImplementation(libs.junit)
	implementation(libs.kotlin.test)
	implementation(libs.kotlin.coroutines.test)
	testImplementation(libs.mockk)
}