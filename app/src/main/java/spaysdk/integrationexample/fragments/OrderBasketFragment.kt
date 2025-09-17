package spaysdk.integrationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import spay.sdk.SPaySdkApp
import spay.sdk.api.MerchantError
import spay.sdk.api.PaymentResult
import spay.sdk.api.SPayMethod
import spay.sdk.api.SdkReadyCheckResult
import spay.sdk.api.model.SPaymentRequest
import spaysdk.integrationexample.BuildConfig
import spaysdk.integrationexample.databinding.FragmentOrderBasketBinding

class OrderBasketFragment : Fragment() {

    private var _binding: FragmentOrderBasketBinding? = null
    private val binding: FragmentOrderBasketBinding get() = _binding!!
    private var isReadyForSPaySdk: Boolean? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBasketBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Проверка готовности работы с SPaySdk. Метод возвращает true при условии, что во время инициализации
         * SPaySdk успела получить конфиг и на устройстве установлен СБОЛ
         */
        SPaySdkApp.getInstance().isReadyForSPaySdk(requireContext(), {
            isReadyForSPaySdk = it is SdkReadyCheckResult.Ready
        })

        binding.apply {

            /**
             * Кнопка для работы с SPaySdk должна быть отображена только в том случае, если метод [isReadyForSPaySdk]
             * вернул true, в противном случае отображать кнопку и взаимодействовать с SPaySdk запрещено. Это может
             * быть чревато нестабильной работой библиотеки, а также потенциальными крашами
             *
             * !!!ВАЖНО!!!
             * Кнопка может быть отрисована вами самостоятельно для поддержания единообразия стиля вашего приложения c
             * сохранением узнаваемой стилистики SPaySdk. После чего ее необходимо в обязательном порядке предоставить
             * на дизайн-ревью команде SPaySdk
             */
            spayPayWithBankInvoiceId.isVisible = isReadyForSPaySdk == true
            spayPayByPartsOnlyBtn.isVisible = isReadyForSPaySdk == true
            spayPayWithoutRefreshBtn.isVisible = isReadyForSPaySdk == true
            spayPayWithBonuses.isVisible = isReadyForSPaySdk == true
            spayNewPayBtn.isVisible = isReadyForSPaySdk == true
            spayPayWithPaymentMethods.isVisible = isReadyForSPaySdk == true
            spayPayWithBinding.isVisible = isReadyForSPaySdk == true
            spayPayWithNmt.isVisible = isReadyForSPaySdk == true


            /**
             * По нажатию на кнопку необходимо вызвать метод для запуска сценария оплаты SPaySdk
             *
             * Номер мобильного телефона [PHONE_NUMBER] является необязательным параметром.
             * Может быть использован во всех сценариях, кроме оплаты по связке
             *
             * Если он был передан -  последним способом авторизации будет НМТ,
             * если нет, то авторизация по НМТ будет выключена
             */
            spayPayWithBankInvoiceId.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithBankInvoiceId,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                        phoneNumber = getPhoneNumber()
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayByPartsOnlyBtn.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithPartPay,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayWithoutRefreshBtn.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithoutRefresh,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayWithBonuses.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithBonuses,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayNewPayBtn.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.Default,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayWithPaymentMethods.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithPaymentAccount,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayWithBinding.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithBinding(getBindingId()),
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }

            spayPayWithNmt.setOnClickListener {
                SPaySdkApp.getInstance().pay(
                    method = SPayMethod.WithPhoneNumber,
                    request = SPaymentRequest(
                        context = requireContext(),
                        apiKey = getApiKey(),
                        merchantLogin = getMerchantLogin(),
                        bankInvoiceId = getBankInvoiceId(),
                        orderNumber = getOrderNumber(),
                        appPackage = APP_PACKAGE,
                        phoneNumber = getPhoneNumber(),
                    ) { paymentResult ->
                        processPaymentResult(paymentResult = paymentResult)
                    }
                )
            }
        }
    }

    /**
     * По завершению работы одной из SDK будет получен коллбек с соответсвующим результатом работы библиотеки.
     */
    private fun processPaymentResult(
        paymentResult: PaymentResult? = null
    ) {
        if (paymentResult != null) processSpaySdkPayResult(paymentResult)
    }

    /** Обработать ответ от SPaySdk */
    private fun processSpaySdkPayResult(paymentResult: PaymentResult) {
        when (paymentResult) {
            is PaymentResult.Cancel -> {
                /** Обработка коллбека при мануальном закрытии шторки SPaySdk пользователем */
                makeSnackbar("SPaySdk закрыта пользователем")
            }

            is PaymentResult.Error -> {
                /** Обработка коллбека ошибки оплаты */
                processPaymentResultError(paymentResult.merchantError)
            }

            is PaymentResult.Processing -> {
                /**
                 * Обработка коллбека краевого кейса, когда результат оплаты неизвестен. При этом необходимо
                 * обратиться к своему беку для уточнения статуса оплаты.
                 */
                makeSnackbar("Статус оплаты заказа неизвестен")
            }

            is PaymentResult.Success -> {
                /** Обработка коллбека успешной оплаты заказа */
                makeSnackbar("Оплата прошла успешно")
            }
        }
    }

    private fun processPaymentResultError(merchantError: MerchantError?) {
        when (merchantError) {
            /** У пользователя плохое соединение с сетью либо вовсе отсутствует интернет */
            is MerchantError.NoInternetConnection -> {
                makeSnackbar(merchantError.description)
            }
            /** Не все, либо некорректные данные были переданы при попытке оплаты */
            is MerchantError.RequiredDataNotSent -> {
                makeSnackbar(merchantError.description)
            }
            /** Ошибка при работе с API банка */
            is MerchantError.SPayApiError -> {
                makeSnackbar(merchantError.description)
            }
            /** Время ожидания ответа от API банка истекло */
            is MerchantError.TimeoutException -> {
                makeSnackbar(merchantError.description)
            }
            /** Непредвиденная ошибка в работе SPaySdk */
            is MerchantError.UnexpectedError -> {
                makeSnackbar(merchantError.description)
            }
            /** Данный коллбек будет убран в ближайшее время. Теперь стоит ориентироваться на [PaymentResult.Cancel] */
            MerchantError.SdkClosedByUser -> {
                /* Nothing to do here */
            }

            /** Ошибка при оплате с бонусами */
            is MerchantError.PayWithBonusesError -> {
                makeSnackbar(merchantError.description)
            }

            /** Оплата по связкам */
            is MerchantError.PayWithBindingError -> {
                makeSnackbar(merchantError.description)
            }

            /** Ошибка при работе внутренних компонентов SDK */
            is MerchantError.InnerSdkComponentsError -> {
                makeSnackbar(merchantError.description)
            }

            /** isReadyForSPay метод не был вызыван */
            is MerchantError.IsReadyCheckHasNotBeenCalled -> {
                makeSnackbar(merchantError.description)
            }

            null -> {}
        }
    }

    private fun makeSnackbar(message: String) =
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

    //
    //
    // -------------------- Данные для заполнения при тестировании --------------------
    //
    //

    /** Подставьте сюда свой apiKey, который вам выдали после регистрации, либо введите его в поле этого экрана вручную */
    private fun getApiKey(): String = binding.etApiKey.text.toString()

    /** Подставьте сюда свой merchantLogin, под которым вас зарегистрировали, либо введите его в поле этого экрана вручную */
    private fun getMerchantLogin(): String = binding.etMerchantLogin.text.toString()

    /**
     * Подставьте сюда номер заказа(bankInvoiceId), полученный со стороны вашего бека после регистрации заказа,
     * либо введите его в поле этого экрана вручную
     */
    private fun getBankInvoiceId(): String = binding.etBankInvoiceId.text.toString()

    /**
     * Подставьте сюда номер заказа, под которым заказ проходит у вас в приложении.
     * Этот параметр нужен для быстрого поиска проблемного заказа и эффективного разбора инцидентов
     */
    private fun getOrderNumber(): String = binding.etOrderNumber.text.toString()

    /**
     * Подставьте сюда идентификатор связки
     */
    private fun getBindingId(): String = binding.etBindingId.text.toString()

    /**
     * Подставьте сюда номер телефона для авторизации по нему
     */
    private fun getPhoneNumber(): String = binding.etPhoneNumber.text.toString()

    companion object {
        /**
         * При интеграции в свое приложение необходимо подставить сюда пакет Вашего приложения,
         * либо [BuildConfig.APPLICATION_ID] Вашего приложения как показано здесь
         */
        private const val APP_PACKAGE = BuildConfig.APPLICATION_ID
    }
}
