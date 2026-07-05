pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "ae-health-mobile"
include(":app")
include(":core:ui")
include(":feature:catalog")
include(":feature:ai")
include(":feature:user")
include(":feature:auth")
include(":network")
include(":network:auth")
include(":network:catalog")
include(":network:ai")
include(":network:user")
include(":core:mapper")
include(":core:secrets")
