package spaysdk.integrationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import spay.sdk.SPaySdkApp
import spay.sdk.api.MerchantError
import spay.sdk.api.PaymentResult
import spaysdk.integrationexample.BuildConfig
import spaysdk.integrationexample.databinding.FragmentOrderBasketBinding

class OrderBasketFragment : Fragment() {

    private var _binding: FragmentOrderBasketBinding? = null
    private val binding: FragmentOrderBasketBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrderBasketBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /**
         * Проверка готовности работы с SPaySdk. Метод возвращает true при условии, что во время инициализации
         * SPaySdk успела получить конфиг и на устройстве установлен СБОЛ
         */
        val isReadyForSPaySdk = SPaySdkApp.getInstance().isReadyForSPaySdk(requireContext())

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
            if (isReadyForSPaySdk) spbSpaysdk.visibility = View.VISIBLE

            /**
             * По нажатию на кнопку необходимо вызвать метод для запуска сценария оплаты SPaySdk
             */
            spbSpaysdk.setOnClickListener {
                SPaySdkApp.getInstance().payWithBankInvoiceId(
                    context = requireContext(),
                    apiKey = API_KEY,
                    merchantLogin = MERCHANT_LOGIN,
                    bankInvoiceId = BANK_INVOICE_ID,
                    orderNumber = ORDER_NUMBER,
                    appPackage = APP_PACKAGE,
                    language = LANGUAGE
                ) { paymentResult ->
                    processPaymentResult(paymentResult)
                }
            }
        }
    }

    /**
     * По завершению работы SPaySdk будет получен коллбек с соответсвующим результатом работы библиотеки.
     */
    private fun processPaymentResult(paymentResult: PaymentResult) {
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
            /** Данный коллбека будет убран в ближайшее время. Теперь стоит ориентироваться на [PaymentResult.Cancel] */
            MerchantError.SdkClosedByUser -> {
                /* Nothing to do here */
            }
            null -> {}
        }
    }

    private fun makeSnackbar(message: String) = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

    /**
     * Константы для заполнения при тестировании
     */
    companion object {

        /** Подставьте сюда свой apiKey, который вам выдали после регистрации */
        private const val API_KEY = ""

        /** Подставьте сюда свой merchantLogin, под которым вас зарегистрировали  */
        private const val MERCHANT_LOGIN = ""

        /** Подставьте сюда номер заказа(bankInvoiceId), полученный со стороны вашего бека после регистрации заказа */
        private const val BANK_INVOICE_ID = ""

        /**
         * Подставьте сюда номер заказа, под которым заказ проходит у вас в приложении.
         * Этот параметр нужен для быстрого поиска проблемного заказа и эффективного разбора инцидентов
         */
        private const val ORDER_NUMBER = ""

        /**
         * При интеграции в свое приложение необходимо подставить сюда пакет Вашего приложения,
         * либо [BuildConfig.APPLICATION_ID] Вашего приложения как показано здесь
         */
        private const val APP_PACKAGE = BuildConfig.APPLICATION_ID

        /**
         * При интеграции в свое приложение необходимо подставить локаль с которой должна работать SPaySdk
         *
         * !!!ВАЖНО!!! На данный момент библиотека умеет работать только с RU локалью
         */
        private const val LANGUAGE = "RU"
    }
}
