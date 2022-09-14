object AppConfig {
    const val compileSdk = 33
    const val minSdk = 26
    const val targetSdk = 32
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val buildToolsVersion = "33.0.0"
    const val ndkVersion = "25.0.8775105"
    const val cmakeVersion = "3.22.1"

    const val androidTestInstrumentation = "androidx.test.runner.AndroidJUnitRunner"
    const val dimension = "environment"

    object BuildTypes {
        const val release = "release"
        const val debug = "debug"
    }

    object ProductFlavors {
        const val staging = "staging"
        const val production = "production"
    }
}
