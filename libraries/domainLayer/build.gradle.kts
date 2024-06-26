plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.kotlinx.serialization)
	//kotlin("kapt")
	//alias(libs.plugins.hilt.android)

}

android {
    namespace = "com.pelagohealth.codingchallenge.domainlayer"
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
	implementation(project(":libraries:utils"))
	implementation(libs.kotlin.coroutines)

	// Hilt
	//implementation(libs.hilt.android)
	//kapt(libs.hilt.android.compiler)

	// Koin
	implementation(libs.koin.core)

	implementation(libs.kotlin.test)
	implementation(libs.kotlin.coroutines.test)
	testImplementation(libs.mockk)
	testImplementation(libs.junit)
}

// Allow references to generated code
/*kapt {
	correctErrorTypes = true
}*/
