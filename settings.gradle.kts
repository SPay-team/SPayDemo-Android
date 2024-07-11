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

        /**
         * Интеграция используя maven
         *
         * Поместите сюда креды которые вам были выданы в пригласительном письме для доступа к maven репозиторию
         */
        maven {
            name = "name"
            url = uri("uri")
            credentials {
                username = "username"
                password = "password"
            }
        }
    }
}

rootProject.name = "SPaySdkIntegrationExample"
include(":app")
 