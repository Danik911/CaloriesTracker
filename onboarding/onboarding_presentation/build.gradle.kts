apply {
    from("$rootDir/base-module-compose.gradle")
}

dependencies {
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.onboardingDomain))
}