pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pelago Coding Challenge"
include(":app")
include(":libraries:domainLayer")
include(":libraries:dataLayer")
include(":libraries:dataSource")
include(":libraries:utils")
