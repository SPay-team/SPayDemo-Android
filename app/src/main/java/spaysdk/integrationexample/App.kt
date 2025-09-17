package spaysdk.integrationexample

import android.app.Application
import android.util.Log
import spay.sdk.SPaySdkApp
import spay.sdk.api.SPayHelperConfig
import spay.sdk.api.SPaySdkInitConfig
import spay.sdk.api.SPayStage

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * Конфиг для инициализации библиотеки SPaySdk
         */
        val sPaySdkInitConfig = SPaySdkInitConfig(
            enableBnpl = true,
            stage = SPayStage.Prod,
            helperConfig = SPayHelperConfig(
                isHelperEnabled = true,
                disabledHelpers = emptyList()
            ),
            resultViewNeeded = true,
            enableLogging = true,
            spasiboBonuses = true,
            enableOutsideTouchCancelling = true,
        ) { initializationResult ->
            Log.i("Initialization_result_spay", "$initializationResult")
        }

        /**
         * Инициализация SPaySdk
         *
         * Инициализацию необходимо проводить в App классе Вашего приложения, т к в момент инициализации SPaySdk получает
         * конфиг для корректной работы. Если метод вызвать слишком поздно, то конфиг не будет получен и метод
         * isReadyForSPaySdk вернет false
         *
         */
        SPaySdkApp.getInstance().initialize(this, sPaySdkInitConfig)
    }
}
