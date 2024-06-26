plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.jetbrains.kotlin.android)
	alias(libs.plugins.compose.compiler)
//	kotlin("kapt")
//	alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.pelagohealth.codingchallenge"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.pelagohealth.codingchallenge"
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
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
	sourceSets.all {
		languageSettings.enableLanguageFeature("ExplicitBackingFields")
	}
}

dependencies {
	implementation(project(":libraries:domainLayer"))
	implementation(project(":libraries:dataLayer"))
	implementation(project(":libraries:dataSource"))
	implementation(project(":libraries:utils"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.ktx)

    implementation(libs.kotlinx.coroutines.android)

	// Compose
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.moshi)

    // Hilt
    //implementation(libs.hilt.android)
	//kapt(libs.hilt.android.compiler)

	// Koin
	implementation(libs.koin.core)

	// Voyager
	implementation(libs.voyager.navigator)
	implementation(libs.voyager.koin)

	// Unit testing
    testImplementation(libs.junit)

    // UI testing
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//// Allow references to generated code
//kapt {
//	correctErrorTypes = true
//}
