import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.internal.os.OperatingSystem
import org.gradle.jvm.tasks.Jar

plugins {
    application
    kotlin("jvm") version "1.3.41"
}

application {
    mainClassName = "melkorkt.core.MainKt"
}

version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}



val lwjglVersion = "3.2.2"
val jomlVersion = "1.9.17"

val lwjglNatives = when (OperatingSystem.current()) {
    OperatingSystem.LINUX   -> "natives-linux"
    OperatingSystem.MAC_OS  -> "natives-macos"
    OperatingSystem.WINDOWS -> "natives-windows"
    else -> throw Error("Unrecognized or unsupported Operating system. Please set \"lwjglNatives\" manually")
}

repositories {
    mavenCentral()
}

dependencies {

    /*
    Each renderer will need different dependencies.
    Each one needs core.
    OpenGL needs "gl", "glfw"
    Vulkan needs "vk", "glfw"
    JOGL needs "jogl"
    OpenJFX needs "openjfx"

    To get all the dependencies in one sweep, create an array of the strings needed and loop through them like below.
    Any number of renderers can be added to the project like this however, you could all all of them with the array ["gl", "glfw", "core", "vk", "jogl", "openjfx"]
    This example gets the OpenGL needed modules.
     */
    listOf("gl", "glfw", "core").forEach {
        implementation("com.github.kotlin-graphics.imgui:imgui-$it:-SNAPSHOT")
    }
    // Look up which modules and versions of LWJGL are required and add setup the approriate natives.
    configurations.compile.resolvedConfiguration.getResolvedArtifacts().forEach {
        if (it.moduleVersion.id.group == "org.lwjgl") {
            runtime("org.lwjgl:${it.moduleVersion.id.name}:${it.moduleVersion.id.version}:${lwjglNatives}")
        }
    }
    implementation("com.github.kotlin-graphics.uno-sdk:uno-gl:3f32007ffe")
    implementation("com.github.kotlin-graphics.uno-sdk:uno-core:3f32007ffe")

    implementation("com.github.kotlin-graphics.glm:glm:6048c31425ae6110258e4b42165f1e636f8b5603")
//    implementation("com.github.kotlin_graphics", "uno-sdk")
//    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))

    implementation("org.lwjgl", "lwjgl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-assimp", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-bgfx", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-cuda", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-egl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-glfw", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-jawt", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-jemalloc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-libdivide", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-llvm", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-lmdb", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-lz4", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-meow", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nanovg", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nfd", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-nuklear", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-odbc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-openal", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opencl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opengl", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opengles", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-openvr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-opus", lwjglVersion)
//    implementation("org.lwjgl", "lwjgl-ovr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-par", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-remotery", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-rpmalloc", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-sse", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-stb", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tinyexr", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tinyfd", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-tootle", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-vma", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-vulkan", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-xxhash", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-yoga", lwjglVersion)
    implementation("org.lwjgl", "lwjgl-zstd", lwjglVersion)
    runtimeOnly("org.lwjgl", "lwjgl", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-assimp", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-bgfx", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-jemalloc", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-libdivide", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-llvm", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lmdb", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-lz4", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-meow", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nanovg", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nfd", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-nuklear", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openal", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengles", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-openvr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opus", lwjglVersion, classifier = lwjglNatives)
//    runtimeOnly("org.lwjgl", "lwjgl-ovr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-par", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-remotery", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-rpmalloc", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-sse", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyexr", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tinyfd", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-tootle", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-vma", lwjglVersion, classifier = lwjglNatives)
    if (lwjglNatives == "natives-macos") runtimeOnly("org.lwjgl", "lwjgl-vulkan", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-xxhash", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-yoga", lwjglVersion, classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-zstd", lwjglVersion, classifier = lwjglNatives)
}

val fatJar = task("fatJar", type=Jar::class) {
    manifest {
        attributes["Main-Class"] = "melkorkt.core.MainKt"
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get() as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}