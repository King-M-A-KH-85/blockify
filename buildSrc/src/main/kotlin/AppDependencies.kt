object AppDependencies {
    // android ui libraries
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val composeUi = "androidx.compose.ui:ui:${Versions.composeVersion}"
    private const val material3 = "androidx.compose.material3:material3:${Versions.material3}"
    private const val uiToolingPreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}"
    private const val lifecycleRuntimeKtx =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    private const val activityCompose =
        "androidx.activity:activity-compose:${Versions.activityCompose}"
    private const val palette = "androidx.palette:palette:${Versions.paletteVersion}"

    //extra libraries
    private const val multiDex = "androidx.multidex:multidex:${Versions.multiDexVersion}"

    // test libraries
    private const val junit = "junit:junit:${Versions.junitVersion}"

    // android test
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunitVersion}"
    private const val espressoCore =
        "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    private const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"

    // debug
    private const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    private const val uiTestManifest =
        "androidx.compose.ui:ui-test-manifest:${Versions.composeVersion}"


    val appLibraries = arrayListOf<String>().apply {
        add(coreKtx)
        add(appcompat)
        add(material3)
        add(composeUi)
        add(uiToolingPreview)
        add(lifecycleRuntimeKtx)
        add(activityCompose)
        add(multiDex)
        add(palette)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(uiTestJunit4)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val debugLibraries = arrayListOf<String>().apply {
        add(uiTooling)
        add(uiTestManifest)
    }
}
