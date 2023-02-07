apply {
    from("$rootDir/base-module-compose.gradle")
}
apply(plugin = "org.jetbrains.kotlin.android")

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))
    "implementation"(project(Modules.core_ui))
}