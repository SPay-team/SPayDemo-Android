import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
val localProperties = Properties().apply {
    val localFile = File(rootDir, "local.properties")
    if (localFile.exists()) {
        load(localFile.inputStream())
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
            name = localProperties.getProperty("name")
            url = uri(localProperties.getProperty("url"))
            credentials {
                username = localProperties.getProperty("username")
                password = localProperties.getProperty("password")
            }
        }
    }
}

rootProject.name = "SPaySdkIntegrationExample"
include(":app")
 