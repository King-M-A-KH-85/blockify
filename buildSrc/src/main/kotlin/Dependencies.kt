import org.gradle.api.artifacts.dsl.DependencyHandler
import java.io.File
import java.io.FileWriter
import java.io.IOException

val dependencies = arrayListOf<String>().apply {
    addAll(AppDependencies.appLibraries)
    addAll(AppDependencies.androidTestLibraries)
    addAll(AppDependencies.debugLibraries)
    addAll(AppDependencies.testLibraries)
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}

fun createCopyright() {
    dependencies.forEach {
        val copyRightFile = File("./CopyRight.txt")
        copyRightFile.createNewFile()
        try {
            FileWriter("./CopyRight.txt", true).use { fileWriter ->
                fileWriter.append("$it\n")
                fileWriter.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        println("@copy-right created successful")
    }
}

data class Dependency(
    val type: String,
    val group: String,
    val depName: String,
    val domain: String
) {
    object Types {
        const val implementation = "implementation"
        const val androidTestImplementation = "androidTestImplementation"
        const val testImplementation = "testImplementation"
        const val debugImplementation = "debugImplementation"
    }
}
