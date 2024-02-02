package spaysdk.integrationexample.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import spaysdk.integrationexample.R
import spaysdk.integrationexample.databinding.FragmentStartBinding

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentStartBinding.inflate(layoutInflater)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.acbOrderBasket.setOnClickListener {
            lifecycleScope.launch { navigateToBasket() }
        }
    }

    /**
     * Задержка при переходе между экранами эмулирует время, которое будет пройдено пользователем от инициализации
     * SPaySdk в вашем приложении до корзины с отображаемой кнопкой для оплаты
     */
    private suspend fun navigateToBasket() {
        binding.apply {
            flProgress.visibility = View.VISIBLE
            delay(2000)
            findNavController().navigate(R.id.action_startFragment_to_orderBasketFragment)
            flProgress.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}