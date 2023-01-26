apply {
    from("$rootDir/base-module-compose.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.trackerDomain))
    "implementation"(project(Modules.core_ui))
}