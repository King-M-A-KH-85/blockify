import org.gradle.internal.classpath.Instrumented

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = AppConfig.buildToolsVersion
    ndkVersion = AppConfig.ndkVersion

    defaultConfig {
        applicationId = "ir.blockify"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = "${AppConfig.versionName}-${System.currentTimeMillis()}"

        multiDexEnabled = true

        testInstrumentationRunner = AppConfig.androidTestInstrumentation

        vectorDrawables {
            useSupportLibrary = true
        }

        vectorDrawables.generatedDensities("mdpi", "hdpi", "xhdpi", "xxhdpi")

        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.schemaLocation"] = "$projectDir/schemas"
                arguments["room.incremental"] = "true"
                arguments["room.expandProjection"] = "true"
            }
        }

        externalNativeBuild {
            cmake {
                version = AppConfig.cmakeVersion
                arguments(
                    "-DANDROID_STL=c++_static",
                    "-DANDROID_PLATFORM=android-16",
                    "-j=16"
                )
            }
        }
    }

    buildTypes {
        getByName(AppConfig.BuildTypes.release) {
            isMinifyEnabled = false
//            isShrinkResources = true
            isJniDebuggable = true
            multiDexEnabled = true
            isDebuggable = true
            ndk.debugSymbolLevel = "FULL"
            multiDexKeepProguard = file("multidex-config.pro")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

        }

        getByName(AppConfig.BuildTypes.debug) {
            isMinifyEnabled = false
            isDebuggable = true
            isJniDebuggable = true
            applicationIdSuffix = ".beta"
            isMinifyEnabled = false
            isShrinkResources = false
            multiDexEnabled = true
            ndk.debugSymbolLevel = "FULL"
            versionNameSuffix = "-${AppConfig.BuildTypes.debug}"
//            signingConfig true
        }
    }

    flavorDimensions(AppConfig.dimension)

    productFlavors {
        create(AppConfig.ProductFlavors.staging) {
            setDimension(AppConfig.dimension)
            versionNameSuffix = "-${AppConfig.ProductFlavors.staging}"
        }

        create(AppConfig.ProductFlavors.production) {
            setDimension(AppConfig.dimension)
        }
    }

//    viewBinding {
//        android.buildFeatures.dataBinding = true
//        android.buildFeatures.viewBinding = true
//    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
//        isCoreLibraryDesugaringEnabled = true
    }

//    externalNativeBuild {
//        cmake {
//            path = 'jni/CMakeLists.txt'
//        }
//    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false

        disable.add("VectorPath")
        disable.add("NestedWeights")
        disable.add("ContentDescription")
        disable.add("SmallSp")
        disable.add("MissingTranslation")
        disable.add("ExtraTranslation")
        disable.add("BlockedPrivateApi")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }

        unitTests.all {
            Instrumented.systemProperty("robolectric.enabledSdks", "26")
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }

    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }

    androidResources {
        additionalParameters.add("--warn-manifest-validation")
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
        jniLibs {
            useLegacyPackaging = true
        }
    }

    configurations.all {
        exclude("com.google.firebase", "firebase-core")
        exclude("androidx.recyclerview", "recyclerview")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // implementation libraries
    implementation(AppDependencies.appLibraries)
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    debugImplementation(AppDependencies.debugLibraries)

    // create libraries copy-right
    createCopyright()
}
