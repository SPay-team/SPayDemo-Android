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

        /**
         * Дополнительные репозитории для работы с SDK
         */
        maven {
            name = "gitverse"
            url = uri("https://gitverse.ru/api/packages/clickstream/maven")
        }
        maven {
            name = "HMS"
            url = uri("https://developer.huawei.com/repo/")
        }
    }
}

rootProject.name = "SPaySdkIntegrationExample"
include(":app")
 