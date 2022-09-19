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
    val path = "./app/src/main/assets/libraries-copy-right"

    FileWriter(path, false).use { fileWriter ->
        fileWriter.write("")
        fileWriter.flush()
    }

    dependencies.forEach {
        val copyRightFile = File(path)
        copyRightFile.createNewFile()
        try {
            FileWriter(path, true).use { fileWriter ->
                fileWriter.append("$it\n")
                fileWriter.flush()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    println("copy-right created successful")
}
