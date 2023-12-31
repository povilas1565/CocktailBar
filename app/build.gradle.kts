plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jlleitschuh.gradle.ktlint") version "11.5.0"
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.cocktailbar"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                arguments += mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        viewBinding = true
    }

    tasks.getByPath("preBuild").dependsOn("ktlintFormat")

    ktlint {
        this.android.set(true)
        this.ignoreFailures.set(false)
        this.disabledRules.add("max-line-length")
        this.reporters {
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            this.reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    namespace = "com.jumrukovski.cocktailbar"
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Bottom Navigation
    implementation("io.ak1:bubbletabbar:1.0.8")

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    // LiveCycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")

    // Retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    // OkHttp3
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Koin Dependency Injection
    implementation("io.insert-koin:koin-android:3.4.0")
    testImplementation("io.insert-koin:koin-test:3.4.0")
    testImplementation("io.insert-koin:koin-test-junit4:3.4.0")

    // Coil Image Loader
    implementation("io.coil-kt:coil:2.2.2")

    // Room
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-coroutines:2.1.0-alpha04")
    kapt("androidx.room:room-compiler:2.5.2")
}
