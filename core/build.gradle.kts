apply {
    from("$rootDir/base-module.gradle")
}
apply(plugin = "org.jetbrains.kotlin.android")

dependencies {
    "implementation"(DataStore.dataStore)
}