apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(DataStore.dataStore)
}